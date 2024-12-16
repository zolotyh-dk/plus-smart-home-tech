package service.handler.hub;

import model.hub.HubEvent;
import org.apache.avro.specific.SpecificRecordBase;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import ru.yandex.practicum.kafka.telemetry.event.HubEventAvro;
import configuration.KafkaConfiguration;

public abstract class BaseHubEventHandler<T extends SpecificRecordBase> implements HubEventHandler {
    @Autowired
    protected KafkaProducer<String, SpecificRecordBase> producer;

    @Value("${kafka-topic-hub}")
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

        producer.send(hubEventAvro, event.getHubId(), event.getTimestamp(), topic);
    }
}
