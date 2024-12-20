package ru.yandex.practicum.service.handler.sensor;

import org.apache.avro.specific.SpecificRecordBase;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.springframework.stereotype.Component;
import ru.yandex.practicum.kafka.telemetry.event.LightSensorAvro;
import ru.yandex.practicum.model.sensor.LightSensorEvent;
import ru.yandex.practicum.model.sensor.SensorEvent;
import ru.yandex.practicum.model.sensor.SensorEventType;

@Component
public class LightSensorEventHandler extends BaseSensorEventHandler<LightSensorAvro> {
    protected LightSensorEventHandler(KafkaProducer<String, SpecificRecordBase> producer) {
        super(producer);
    }

    @Override
    protected LightSensorAvro mapToAvro(SensorEvent event) {
        LightSensorEvent lightSensorEvent = (LightSensorEvent) event;
        return LightSensorAvro.newBuilder()
                .setLinkQuality(lightSensorEvent.getLinkQuality())
                .setLuminosity(lightSensorEvent.getLuminosity())
                .build();
    }

    @Override
    public SensorEventType getMessageType() {
        return SensorEventType.LIGHT_SENSOR_EVENT;
    }
}
