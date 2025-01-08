package ru.yandex.practicum.service.handler.sensor;

import org.apache.avro.specific.SpecificRecordBase;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.springframework.stereotype.Component;
import ru.yandex.practicum.grpc.telemetry.event.ClimateSensorEvent;
import ru.yandex.practicum.grpc.telemetry.event.SensorEventProto;
import ru.yandex.practicum.kafka.telemetry.event.ClimatSensorAvro;

@Component
public class ClimateSensorEventHandler extends BaseSensorEventHandler<ClimatSensorAvro> {
    protected ClimateSensorEventHandler(KafkaProducer<String, SpecificRecordBase> producer) {
        super(producer);
    }

    @Override
    protected ClimatSensorAvro mapToAvro(SensorEventProto event) {
        ClimateSensorEvent climateSensorEvent = event.getClimateSensor();
        return ClimatSensorAvro.newBuilder()
                .setTemperatureC(climateSensorEvent.getTemperatureC())
                .setHumidity(climateSensorEvent.getHumidity())
                .setCo2Level(climateSensorEvent.getCo2Level())
                .build();
    }

    @Override
    public SensorEventProto.PayloadCase getMessageType() {
        return SensorEventProto.PayloadCase.CLIMATE_SENSOR;
    }
}
