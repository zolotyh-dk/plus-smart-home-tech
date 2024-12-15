package service.handler.sensor;

import model.sensor.ClimateSensorEvent;
import model.sensor.SensorEvent;
import model.sensor.SensorEventType;
import ru.yandex.practicum.kafka.telemetry.event.ClimatSensorAvro;

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
