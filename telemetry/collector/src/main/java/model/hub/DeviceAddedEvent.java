package model.hub;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Событие, сигнализирующее о добавлении нового устройства в систему
 */

@Getter
@Setter
@ToString(callSuper = true)
public class DeviceAddedEvent extends HubEvent {
    private String id;              // Идентификатор добавленного устройства
    private DeviceType deviceType; // Тип устройства

    @Override
    public HubEventType getType() {
        return HubEventType.DEVICE_ADDED;
    }
}
