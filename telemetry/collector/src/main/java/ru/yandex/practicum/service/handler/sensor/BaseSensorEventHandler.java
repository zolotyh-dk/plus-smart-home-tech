package ru.yandex.practicum.service.handler.sensor;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.model.sensor.SensorEvent;
import org.apache.avro.specific.SpecificRecordBase;
import ru.yandex.practicum.kafka.telemetry.event.SensorEventAvro;

@Slf4j
@Service
public abstract class BaseSensorEventHandler<T extends SpecificRecordBase> implements SensorEventHandler {
    @Autowired
    protected KafkaProducer<String, SpecificRecordBase> producer;

    @Value("${kafka.topic.sensor}")
    protected String topic;

    protected abstract T mapToAvro(SensorEvent event);

    @Override
    public void handle(SensorEvent event) {
        if (!event.getType().equals(getMessageType())) {
            throw new IllegalArgumentException("Неизвестный тип события: " + event.getType());
        }

        T payload = mapToAvro(event);

        SensorEventAvro sensorEventAvro = SensorEventAvro.newBuilder()
                .setHubId(event.getHubId())
                .setId(event.getId())
                .setTimestamp(event.getTimestamp())
                .setPayload(payload)
                .build();

        ProducerRecord<String, SpecificRecordBase> record = new ProducerRecord<>(
                topic,
                null,
                event.getTimestamp().toEpochMilli(),
                sensorEventAvro.getHubId(),
                sensorEventAvro);
        producer.send(record);

        log.info("Отправили в Kafka: {}", record);
    }
}
