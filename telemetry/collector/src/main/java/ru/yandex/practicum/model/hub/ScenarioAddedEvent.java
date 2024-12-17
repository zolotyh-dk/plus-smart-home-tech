package ru.yandex.practicum.model.hub;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
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
    @Size(min = 3)
    @NotBlank
    private String name;

    @NotEmpty
    private List<ScenarioCondition> conditions;

    @NotEmpty
    private List<DeviceAction> actions;

    @Override
    public HubEventType getType() {
        return HubEventType.SCENARIO_ADDED;
    }
}
