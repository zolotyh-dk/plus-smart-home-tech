package model.sensor;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Событие датчика температуры, содержащее информацию о температуре в градусах Цельсия и Фаренгейта
 */

@Getter
@Setter
@ToString(callSuper = true)
public class TemperatureSensorEvent extends SensorEvent {
    private int temperatureC; // Температура в градусах Цельсия
    private int temperatureF; // Температура в градусах Фаренгейта

    @Override
    public SensorEventType getType() {
        return SensorEventType.TEMPERATURE_SENSOR_EVENT;
    }
}
