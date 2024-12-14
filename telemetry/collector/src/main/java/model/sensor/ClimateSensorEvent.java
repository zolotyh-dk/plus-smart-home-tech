package model.sensor;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Событие климатического датчика, содержащее информацию о температуре, влажности и уровне CO2.
 */

@Getter
@Setter
@ToString(callSuper = true)
public class ClimateSensorEvent extends SensorEvent {
    private int temperatureC; //Уровень температуры по шкале Цельсия
    private int humidity; //Влажность
    private int co2Level; //Уровень CO2

    @Override
    public SensorEventType getType() {
        return SensorEventType.CLIMATE_SENSOR_EVENT;
    }
}
