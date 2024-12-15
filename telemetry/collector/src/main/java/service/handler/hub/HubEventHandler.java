package service.handler.hub;

import model.hub.HubEvent;
import model.sensor.SensorEvent;
import model.sensor.SensorEventType;

public interface HubEventHandler {
    SensorEventType getMessageType();

    void handle(HubEvent event);
}
