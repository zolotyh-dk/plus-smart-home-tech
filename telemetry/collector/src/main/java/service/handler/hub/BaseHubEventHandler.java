package service.handler.hub;

import model.hub.HubEvent;
import model.sensor.SensorEvent;
import org.apache.avro.specific.SpecificRecordBase;
import ru.yandex.practicum.kafka.telemetry.event.HubEventAvro;
import ru.yandex.practicum.kafka.telemetry.event.SensorEventAvro;
import service.handler.sensor.SensorEventHandler;

public abstract class BaseHubEventHandler<T extends SpecificRecordBase> implements HubEventHandler {
//    protected KafkaEventProducer producer;

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

//        producer.send(hubEventAvro, event.getHubId(), event.getTimestamp(), HUB_EVENTS);
    }
}
