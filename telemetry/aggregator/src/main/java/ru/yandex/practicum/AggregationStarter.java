package ru.yandex.practicum;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.errors.WakeupException;
import org.springframework.stereotype.Component;
import ru.yandex.practicum.kafka.telemetry.event.SensorEventAvro;
import ru.yandex.practicum.kafka.telemetry.event.SensorsSnapshotAvro;

import java.time.Duration;
import java.util.List;
import java.util.Optional;

@Slf4j
@Component
@RequiredArgsConstructor
public class AggregationStarter {
    private final KafkaConsumer<String, SensorEventAvro> consumer;
    private final KafkaProducer<String, SensorsSnapshotAvro> producer;
    private final SnapshotStorage snapshotStorage;
    private final String inputTopic;
    private final String outputTopic;

    public void start() {
        try {
            consumer.subscribe(List.of(inputTopic));
            log.info("Подписка на топик {}", inputTopic);

            while (true) {
                ConsumerRecords<String, SensorEventAvro> records = consumer.poll(Duration.ofMillis(1000));
                for (ConsumerRecord<String, SensorEventAvro> record : records) {
                    try {
                        Optional<SensorsSnapshotAvro> snapshotOpt = snapshotStorage.updateState(record.value());

                        if (snapshotOpt.isPresent()) {
                            SensorsSnapshotAvro snapshot = snapshotOpt.get();
                            ProducerRecord<String, SensorsSnapshotAvro> producerRecord =
                                    new ProducerRecord<>(outputTopic, snapshot.getHubId(), snapshot);

                            producer.send(producerRecord, (metadata, exception) -> {
                                if (exception != null) {
                                    log.error("Ошибка при отправке сообщения в Kafka: {}", exception.getMessage(), exception);
                                } else {
                                    log.info("Сообщение={} отправлено в Kafka: топик={}, смещение={}",
                                            producerRecord, metadata.topic(), metadata.offset());
                                }
                            });
                        }
                    } catch (Exception e) {
                        log.error("Ошибка при обработке записи: ключ={}, значение={}", record.key(), record.value(), e);
                    }
                }
                consumer.commitSync();
            }
        } catch (WakeupException ignored) {
            // игнорируем - закрываем консьюмер и продюсер в блоке finally
        } catch (Exception e) {
            log.error("Ошибка во время обработки событий от датчиков", e);
        } finally {
            try {
                producer.flush();
                log.info("Все данные отправлены в Kafka");
                consumer.commitSync();
                log.info("Все смещения зафиксированы");
            } finally {
                log.info("Закрываем консьюмер");
                consumer.close();
                log.info("Закрываем продюсер");
                producer.close();
            }
        }
    }
}
