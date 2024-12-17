package ru.yandex.practicum.service.handler.hub;

import org.springframework.stereotype.Service;
import ru.yandex.practicum.model.hub.HubEvent;
import ru.yandex.practicum.model.hub.HubEventType;
import ru.yandex.practicum.model.hub.ScenarioRemovedEvent;
import ru.yandex.practicum.kafka.telemetry.event.ScenarioRemovedAvro;

@Service
public class ScenarioRemovedEventHandler extends BaseHubEventHandler<ScenarioRemovedAvro> {
    @Override
    protected ScenarioRemovedAvro mapToAvro(HubEvent event) {
        ScenarioRemovedEvent scenarioRemovedEvent = (ScenarioRemovedEvent) event;
        return ScenarioRemovedAvro.newBuilder()
                .setName(scenarioRemovedEvent.getName())
                .build();
    }

    @Override
    public HubEventType getMessageType() {
        return HubEventType.SCENARIO_REMOVED;
    }
}
