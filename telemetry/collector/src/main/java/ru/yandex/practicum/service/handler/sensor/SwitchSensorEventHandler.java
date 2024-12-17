package ru.yandex.practicum.service.handler.sensor;

import org.springframework.stereotype.Service;
import ru.yandex.practicum.model.sensor.SensorEvent;
import ru.yandex.practicum.model.sensor.SensorEventType;
import ru.yandex.practicum.model.sensor.SwitchSensorEvent;
import ru.yandex.practicum.kafka.telemetry.event.SwitchSensorAvro;

@Service
public class SwitchSensorEventHandler extends BaseSensorEventHandler<SwitchSensorAvro> {
    @Override
    protected SwitchSensorAvro mapToAvro(SensorEvent event) {
        SwitchSensorEvent switchSensorEvent = (SwitchSensorEvent) event;
        return SwitchSensorAvro.newBuilder()
                .setState(switchSensorEvent.getState())
                .build();
    }

    @Override
    public SensorEventType getMessageType() {
        return SensorEventType.SWITCH_SENSOR_EVENT;
    }
}
