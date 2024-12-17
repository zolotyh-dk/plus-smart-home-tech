package ru.yandex.practicum.model.sensor;

import jakarta.validation.constraints.NotNull;
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
    @NotNull
    private Integer linkQuality;

    @NotNull
    private int luminosity;

    @Override
    public SensorEventType getType() {
        return SensorEventType.LIGHT_SENSOR_EVENT;
    }
} 