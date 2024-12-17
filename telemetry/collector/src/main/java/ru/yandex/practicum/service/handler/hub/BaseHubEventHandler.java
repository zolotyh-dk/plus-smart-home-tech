package ru.yandex.practicum.service.handler.hub;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.model.hub.HubEvent;
import org.apache.avro.specific.SpecificRecordBase;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import ru.yandex.practicum.kafka.telemetry.event.HubEventAvro;

@Slf4j
@Service
public abstract class BaseHubEventHandler<T extends SpecificRecordBase> implements HubEventHandler {
    @Autowired
    protected KafkaProducer<String, SpecificRecordBase> producer;

    @Value("${kafka.topic.hub}")
    protected String topic;

    protected abstract T mapToAvro(HubEvent event);

    @Override
    public void handle(HubEvent event) {
        if (!event.getType().equals(getMessageType())) {
            throw new IllegalArgumentException("Неизвестный тип события: " + event.getType());
        }

        T payload = mapToAvro(event);

        HubEventAvro hubEventAvro = HubEventAvro.newBuilder()
                .setHubId(event.getHubId())
                .setTimestamp(event.getTimestamp())
                .setPayload(payload)
                .build();

        ProducerRecord<String, SpecificRecordBase> record = new ProducerRecord<>(
                topic,
                null,
                event.getTimestamp().toEpochMilli(),
                hubEventAvro.getHubId(),
                hubEventAvro);
        producer.send(record);

        log.info("Отправили в Kafka {}", record);
    }
}
