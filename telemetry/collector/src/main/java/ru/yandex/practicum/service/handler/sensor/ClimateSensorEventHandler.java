package ru.yandex.practicum.service.handler.sensor;

import org.springframework.stereotype.Service;
import ru.yandex.practicum.model.sensor.ClimateSensorEvent;
import ru.yandex.practicum.model.sensor.SensorEvent;
import ru.yandex.practicum.model.sensor.SensorEventType;
import ru.yandex.practicum.kafka.telemetry.event.ClimatSensorAvro;

@Service
public class ClimateSensorEventHandler extends BaseSensorEventHandler<ClimatSensorAvro> {
    @Override
    protected ClimatSensorAvro mapToAvro(SensorEvent event) {
        ClimateSensorEvent climateSensorEvent = (ClimateSensorEvent) event;
        return ClimatSensorAvro.newBuilder()
                .setTemperatureC(climateSensorEvent.getTemperatureC())
                .setHumidity(climateSensorEvent.getHumidity())
                .setCo2Level(climateSensorEvent.getCo2Level())
                .build();
    }

    @Override
    public SensorEventType getMessageType() {
        return SensorEventType.CLIMATE_SENSOR_EVENT;
    }
}
