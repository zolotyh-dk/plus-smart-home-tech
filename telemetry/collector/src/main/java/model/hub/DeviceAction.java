package model.hub;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Представляет действие, которое должно быть выполнено устройством
 */

@Getter
@Setter
@ToString
public class DeviceAction {
    private String sensorId; // Идентификатор датчика, связанного с действием
    private ActionType type; // Перечисление возможных типов действий при срабатывании условия активации сценария
    private int value;       // Необязательное значение, связанное с действием
}
