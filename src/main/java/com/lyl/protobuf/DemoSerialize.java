package com.lyl.protobuf;

import io.protostuff.LinkBuffer;
import io.protostuff.LinkedBuffer;
import io.protostuff.ProtobufIOUtil;
import io.protostuff.Schema;
import io.protostuff.runtime.RuntimeSchema;
import org.apache.kafka.common.serialization.Serializer;

import java.util.Map;

public class DemoSerialize implements Serializer<Data> {

    public void configure(Map<String, ?> configs, boolean isKey) {

    }

    public byte[] serialize(String topic, Data data) {
        if (data == null){
            return null;
        }
        Schema schema = RuntimeSchema.getSchema(data.getClass());
        LinkedBuffer buffer = LinkedBuffer.allocate(LinkedBuffer.DEFAULT_BUFFER_SIZE);
        byte[] protostuff = null;
        try {
            protostuff = ProtobufIOUtil.toByteArray(data, schema, buffer);
        }catch (Exception e){
            throw new IllegalArgumentException(e.getMessage(), e);
        }finally {
            buffer.clear();
        }
        return protostuff;
    }

    public void close() {

    }

}
