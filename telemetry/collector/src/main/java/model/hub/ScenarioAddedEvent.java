package model.hub;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

/**
 * Событие добавления сценария в систему. Содержит информацию о названии сценария, условиях и действиях
 */

@Getter
@Setter
@ToString(callSuper = true)
public class ScenarioAddedEvent extends HubEvent{
    private String name;                        // Название добавленного сценария. Должно содержать не менее 3 символов
    private List<ScenarioCondition> conditions; // Список условий, которые связаны со сценарием. Не может быть пустым
    private List<DeviceAction> actions;         // Список действий, которые должны быть выполнены в рамках сценария. Не может быть пустым

    @Override
    public HubEventType getType() {
        return HubEventType.SCENARIO_ADDED;
    }
}
