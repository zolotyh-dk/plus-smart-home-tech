package model.hub;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import model.sensor.*;

import java.time.Instant;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.EXISTING_PROPERTY,
        property = "type",
        defaultImpl = HubEvent.class
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = ClimateSensorEvent.class, name = "CLIMATE_SENSOR_EVENT"),
})
@Getter
@Setter
@ToString
public abstract class HubEvent {
    private String hubId; // Идентификатор хаба, связанный с событием
    private Instant timestamp = Instant.now(); // Временная метка события. По умолчанию устанавливается текущее время.

    public abstract HubEventType getType();
}
