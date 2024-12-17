package ru.yandex.practicum.service.handler.hub;

import org.springframework.stereotype.Service;
import ru.yandex.practicum.model.hub.DeviceRemovedEvent;
import ru.yandex.practicum.model.hub.HubEvent;
import ru.yandex.practicum.model.hub.HubEventType;
import ru.yandex.practicum.kafka.telemetry.event.DeviceRemovedAvro;

@Service
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
