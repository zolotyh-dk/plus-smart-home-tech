package model.hub;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Условие сценария, которое содержит информацию о датчике, типе условия, операции и значении
 */

@Getter
@Setter
@ToString
public class ScenarioCondition {
    private String sensorId;              // Идентификатор датчика, связанного с условием
    private ConditionType type;           // Типы условий, которые могут использоваться в сценариях
    private ConditionOperation operation; // Операции, которые могут быть использованы в условиях
    private int value;                    // Значение, используемое в условии
}
