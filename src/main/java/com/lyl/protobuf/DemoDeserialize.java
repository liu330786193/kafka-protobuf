package com.lyl.protobuf;

import io.protostuff.ProtobufIOUtil;
import io.protostuff.Schema;
import io.protostuff.runtime.RuntimeSchema;
import org.apache.kafka.common.serialization.Deserializer;

import java.util.Map;

public class DemoDeserialize implements Deserializer<Data> {
    public void configure(Map<String, ?> configs, boolean isKey) {

    }

    public Data deserialize(String topic, byte[] data) {
        if (data == null){
            return null;
        }
        Schema schema = RuntimeSchema.getSchema(Data.class);
        Data dataProto = new Data();
        ProtobufIOUtil.mergeFrom(data, dataProto, schema);
        return dataProto;
    }

    public void close() {

    }
}
