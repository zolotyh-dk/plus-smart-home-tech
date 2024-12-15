package controller;

import lombok.extern.slf4j.Slf4j;
import model.hub.HubEvent;
import model.hub.HubEventType;
import model.sensor.SensorEvent;
import model.sensor.SensorEventType;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import service.handler.hub.HubEventHandler;
import service.handler.sensor.SensorEventHandler;

import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/events", consumes = MediaType.APPLICATION_JSON_VALUE)
@Slf4j
public class EventController {
    private final Map<SensorEventType, SensorEventHandler> sensorEventHandlers;
    private final Map<HubEventType, HubEventHandler> hubEventHandlers;

    public EventController(Set<SensorEventHandler> sensorEventHandlers, Set<HubEventHandler> hubEventHandlers) {
        this.sensorEventHandlers = sensorEventHandlers.stream()
                .collect(Collectors.toMap((SensorEventHandler::getMessageType), Function.identity()));
        this.hubEventHandlers = hubEventHandlers.stream()
                .collect(Collectors.toMap((HubEventHandler::getMessageType), Function.identity()));
    }

    @PostMapping("/sensors")
    public void collectSensorEvents(SensorEvent request) {
        if (sensorEventHandlers.containsKey(request.getType())) {
            sensorEventHandlers.get(request.getType()).handle(request);
        } else {
            throw new IllegalArgumentException("Не найден обработчик для события " + request.getType());
        }
    }

    @PostMapping("/hubs")
    public void collectHubEvents(HubEvent request) {
        if (hubEventHandlers.containsKey(request.getType())) {
            hubEventHandlers.get(request.getType()).handle(request);
        } else {
            throw new IllegalArgumentException("Не найден обработчик для события " + request.getType());
        }
    }
}
