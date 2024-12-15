package service.handler.hub;

import model.hub.DeviceRemovedEvent;
import model.hub.HubEvent;
import model.hub.HubEventType;
import ru.yandex.practicum.kafka.telemetry.event.DeviceRemovedAvro;

public class DeviceRemovedEventHandler extends BaseHubEventHandler<DeviceRemovedAvro> {
    @Override
    protected DeviceRemovedAvro mapToAvro(HubEvent event) {
        DeviceRemovedEvent deviceRemovedEvent = (DeviceRemovedEvent) event;
        return DeviceRemovedAvro.newBuilder()
                .setId(deviceRemovedEvent.getId())
                .build();
    }

    @Override
    public HubEventType getMessageType() {
        return HubEventType.DEVICE_REMOVED;
    }
}
