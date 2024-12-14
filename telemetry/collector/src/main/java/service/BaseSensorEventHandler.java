package service;

import model.sensor.SensorEvent;
import org.apache.avro.specific.SpecificRecordBase;

public abstract class BaseSensorEventHandler<T extends SpecificRecordBase> implements SensorEventHandler {
    protected abstract T mapToAvro(SensorEvent event);

    @Override
    public void handle(SensorEvent event) {
        if (!event.getType().equals(getMessageType())) {
            throw new IllegalArgumentException("Неизвестный тип события: " + event.getType());
        }

        T payload = mapToAvro(event);

    }
}
