package model.hub;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Событие добавления сценария в систему. Содержит информацию о названии сценария, условиях и действиях
 */

@Getter
@Setter
@ToString(callSuper = true)
public class ScenarioAddedEvent extends HubEvent{

    private String name; // Название добавленного сценария. Должно содержать не менее 3 символов

    @Override
    public HubEventType getType() {
        return HubEventType.SCENARIO_ADDED;
    }
}
