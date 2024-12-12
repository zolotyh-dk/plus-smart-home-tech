package model;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.Instant;

@JsonTypeInfo( // указывает, каким образом в данных сохраняется информация о типах
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.EXISTING_PROPERTY, //информация о типе хранится в поле, которое описано внутри класса
        property = "type", // поле, которое хранит информацию о типе
        defaultImpl = SensorEventType.class // какому типу данных соответствует это поле
)
@JsonSubTypes({ //перечислили подтипы, которые нужно учитывать
        @JsonSubTypes.Type(value = LightSensorEvent.class, name = "LIGHT_SENSOR_EVENT"),
        // .... другие подтипы SensorEvent ...
})
@Getter
@Setter
@ToString
public abstract class SensorEvent {
    private String id;
    private String hubId;
    private Instant timestamp = Instant.now();

    public abstract SensorEventType getType();
}
