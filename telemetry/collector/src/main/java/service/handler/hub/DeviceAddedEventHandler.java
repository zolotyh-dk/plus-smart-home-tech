package service.handler.hub;

import model.hub.DeviceAddedEvent;
import model.hub.HubEvent;
import model.hub.HubEventType;
import ru.yandex.practicum.kafka.telemetry.event.DeviceAddedAvro;
import ru.yandex.practicum.kafka.telemetry.event.DeviceTypeAvro;

public class DeviceAddedEventHandler extends BaseHubEventHandler<DeviceAddedAvro> {
    @Override
    protected DeviceAddedAvro mapToAvro(HubEvent event) {
        DeviceAddedEvent deviceAddedEvent = (DeviceAddedEvent) event;
        DeviceTypeAvro deviceTypeAvro = switch (deviceAddedEvent.getDeviceType()) {
            case CLIMATE_SENSOR -> DeviceTypeAvro.CLIMATE_SENSOR;
            case LIGHT_SENSOR -> DeviceTypeAvro.LIGHT_SENSOR;
            case MOTION_SENSOR -> DeviceTypeAvro.MOTION_SENSOR;
            case SWITCH_SENSOR -> DeviceTypeAvro.SWITCH_SENSOR;
            case TEMPERATURE_SENSOR -> DeviceTypeAvro.TEMPERATURE_SENSOR;
        };
        return DeviceAddedAvro.newBuilder()
                .setId(deviceAddedEvent.getId())
                .setDeviceType(deviceTypeAvro)
                .build();
    }

    @Override
    public HubEventType getMessageType() {
        return HubEventType.DEVICE_ADDED;
    }
}
