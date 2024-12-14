package model.hub;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Событие, сигнализирующее об удалении устройства из системы
 */

@Getter
@Setter
@ToString(callSuper = true)
public class DeviceRemovedEvent extends HubEvent {
    private String id; // Идентификатор удаленного устройства

    @Override
    public HubEventType getType() {
        return HubEventType.DEVICE_REMOVED;
    }
}
