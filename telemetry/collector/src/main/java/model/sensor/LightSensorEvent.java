package model.sensor;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Событие датчика освещенности, содержащее информацию о качестве связи и уровне освещенности.
 */

@Getter
@Setter
@ToString(callSuper = true)
public class LightSensorEvent extends SensorEvent {
    private int linkQuality; //Качество связи
    private int luminosity;  //Уровень освещенности

    @Override
    public SensorEventType getType() {
        return SensorEventType.LIGHT_SENSOR_EVENT;
    }
} 