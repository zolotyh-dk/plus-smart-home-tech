package kafka.serializer;

import org.apache.avro.io.BinaryEncoder;
import org.apache.avro.io.EncoderFactory;
import org.apache.avro.specific.SpecificRecordBase;
import org.apache.kafka.common.serialization.Serializer;

public class GeneralAvroSerializer implements Serializer<SpecificRecordBase> {
    private final EncoderFactory encoderFactory;
    private BinaryEncoder encoder;

    public GeneralAvroSerializer(EncoderFactory encoderFactory) {
        this.encoderFactory = encoderFactory;
    }

    public GeneralAvroSerializer() {
        this(EncoderFactory.get());
    }

    @Override
    public byte[] serialize(String s, SpecificRecordBase specificRecordBase) {
        return new byte[0];
    }
}
