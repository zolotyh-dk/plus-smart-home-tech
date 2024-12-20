package ru.yandex.practicum.service.handler.hub;

import org.apache.avro.specific.SpecificRecordBase;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.springframework.stereotype.Component;
import ru.yandex.practicum.kafka.telemetry.event.*;
import ru.yandex.practicum.model.hub.*;

@Component
public class ScenarioAddedEventHandler extends BaseHubEventHandler<ScenarioAddedAvro> {
    public ScenarioAddedEventHandler(KafkaProducer<String, SpecificRecordBase> producer) {
        super(producer);
    }

    @Override
    protected ScenarioAddedAvro mapToAvro(HubEvent event) {
        ScenarioAddedEvent scenarioAddedEvent = (ScenarioAddedEvent) event;
        return ScenarioAddedAvro.newBuilder()
                .setName(scenarioAddedEvent.getName())
                .setConditions(scenarioAddedEvent.getConditions().stream()
                        .map(this::mapToConditionAvro)
                        .toList())
                .setActions(scenarioAddedEvent.getActions().stream()
                        .map(this::mapToActionAvro)
                        .toList())
                .build();
    }

    private ScenarioConditionAvro mapToConditionAvro(ScenarioCondition scenarioCondition) {
        ConditionTypeAvro conditionTypeAvro = switch (scenarioCondition.getType()) {
            case MOTION -> ConditionTypeAvro.MOTION;
            case LUMINOSITY -> ConditionTypeAvro.LUMINOSITY;
            case SWITCH -> ConditionTypeAvro.SWITCH;
            case TEMPERATURE -> ConditionTypeAvro.TEMPERATURE;
            case CO2LEVEL -> ConditionTypeAvro.CO2LEVEL;
            case HUMIDITY -> ConditionTypeAvro.HUMIDITY;
        };
        ConditionOperationAvro conditionOperationAvro = switch (scenarioCondition.getOperation()) {
            case EQUALS -> ConditionOperationAvro.EQUALS;
            case LOWER_THAN -> ConditionOperationAvro.LOWER_THAN;
            case GREATER_THAN -> ConditionOperationAvro.GREATER_THAN;
        };
        return ScenarioConditionAvro.newBuilder()
                .setSensorId(scenarioCondition.getSensorId())
                .setType(conditionTypeAvro)
                .setOperation(conditionOperationAvro)
                .build();
    }

    private DeviceActionAvro mapToActionAvro(DeviceAction deviceAction) {
        ActionTypeAvro actionTypeAvro = switch (deviceAction.getType()) {
            case INVERSE -> ActionTypeAvro.INVERSE;
            case ACTIVATE -> ActionTypeAvro.ACTIVATE;
            case DEACTIVATE -> ActionTypeAvro.DEACTIVATE;
            case SET_VALUE -> ActionTypeAvro.SET_VALUE;
        };
        return DeviceActionAvro.newBuilder()
                .setSensorId(deviceAction.getSensorId())
                .setType(actionTypeAvro)
                .setValue(deviceAction.getValue())
                .build();
    }

    @Override
    public HubEventType getMessageType() {
        return HubEventType.SCENARIO_ADDED;
    }
}
