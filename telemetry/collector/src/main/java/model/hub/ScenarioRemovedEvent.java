package model.hub;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 *
 * Событие удаления сценария из системы. Содержит информацию о названии удаленного сценария
 */

@Getter
@Setter
@ToString(callSuper = true)
public class ScenarioRemovedEvent extends HubEvent {
    private String name; // Название удаленного сценария. Должно содержать не менее 3 символов.

    @Override
    public HubEventType getType() {
        return HubEventType.SCENARIO_REMOVED;
    }
}
