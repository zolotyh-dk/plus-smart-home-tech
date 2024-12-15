package service.handler.hub;

import model.hub.HubEvent;
import model.sensor.SensorEvent;
import org.apache.avro.specific.SpecificRecordBase;
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
        SensorEventAvro sensorEventAvro = SensorEventAvro.newBuilder()
                .setHubId(event.getHubId())
                .setId(event.getId())
                .setTimestamp(event.getTimestamp())
                .setPayload(payload)
                .build();

//        producer.send(sensorEventAvro, event.getHubId(), event.getTimestamp(), SENSORS_EVENTS);
    }
}
