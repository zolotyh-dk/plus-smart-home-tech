package ru.yandex.practicum.service.handler.sensor;

import org.springframework.stereotype.Service;
import ru.yandex.practicum.model.sensor.MotionSensorEvent;
import ru.yandex.practicum.model.sensor.SensorEvent;
import ru.yandex.practicum.model.sensor.SensorEventType;
import ru.yandex.practicum.kafka.telemetry.event.MotionSensorAvro;

@Service
public class MotionSensorEventHandler extends BaseSensorEventHandler<MotionSensorAvro> {
    @Override
    protected MotionSensorAvro mapToAvro(SensorEvent event) {
        MotionSensorEvent motionSensorEvent = (MotionSensorEvent) event;
        return MotionSensorAvro.newBuilder()
                .setLinkQuality(motionSensorEvent.getLinkQuality())
                .setMotion(motionSensorEvent.getMotion())
                .setVoltage(motionSensorEvent.getVoltage())
                .build();
    }

    @Override
    public SensorEventType getMessageType() {
        return SensorEventType.MOTION_SENSOR_EVENT;
    }
}
