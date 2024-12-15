package service.handler.hub;

import model.hub.HubEvent;
import model.hub.HubEventType;
import model.hub.ScenarioRemovedEvent;
import ru.yandex.practicum.kafka.telemetry.event.ScenarioRemovedAvro;

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
