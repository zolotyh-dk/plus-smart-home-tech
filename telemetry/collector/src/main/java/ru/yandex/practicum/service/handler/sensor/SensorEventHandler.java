package ru.yandex.practicum.service.handler.sensor;

import ru.yandex.practicum.model.sensor.SensorEvent;
import ru.yandex.practicum.model.sensor.SensorEventType;

public interface SensorEventHandler {
    SensorEventType getMessageType();

    void handle(SensorEvent event);
}
