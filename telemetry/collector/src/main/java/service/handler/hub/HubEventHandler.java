package service.handler.hub;

import model.hub.HubEvent;
import model.hub.HubEventType;
import model.sensor.SensorEvent;
import model.sensor.SensorEventType;

public interface HubEventHandler {
    HubEventType getMessageType();

    void handle(HubEvent event);
}
