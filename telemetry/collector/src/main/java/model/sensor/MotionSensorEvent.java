package model.sensor;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Событие датчика движения
 */

@Getter
@Setter
@ToString(callSuper = true)
public class MotionSensorEvent extends SensorEvent {
    private int linkQuality; // Качество связи
    private boolean motion; // Наличие/отсутствие движения
    private int voltage; // Напряжение

    @Override
    public SensorEventType getType() {
        return SensorEventType.MOTION_SENSOR_EVENT;
    }
}
