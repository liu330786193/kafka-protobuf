package com.lyl.protobuf;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.util.JsonFormat;
import org.apache.kafka.clients.producer.ProducerRecord;
import sun.security.pkcs11.Secmod;

public class TestAccountProtobuf {

    public static void main(String[] args) throws InvalidProtocolBufferException {
        long st = System.currentTimeMillis();
        for (int i = 0; i< 10000000; i++){
            test1();
        }
        System.out.println("时间：" + (System.currentTimeMillis() - st));
        long st1 = System.currentTimeMillis();
        for (int i = 0; i< 10000000; i++){
            test2();
        }
        System.out.println("时间：" + (System.currentTimeMillis() - st1));
    }

    private static void test1() throws InvalidProtocolBufferException {
        DataProto.data.Builder gps_builder = DataProto.data.newBuilder();
        gps_builder.setAltitude(1);
        gps_builder.setDataTime("2017-12-17 16:21:44");
        gps_builder.setGpsStatus(1);
        gps_builder.setLat(39.123);
        gps_builder.setLon(120.112);
        gps_builder.setDirection(30.2F);
        gps_builder.setId(100L);
        DataProto.data data = gps_builder.build();
//        DataProto.data dp = DataProto.data.parseFrom(data.toByteArray());
        KafkaSender.getInstance().send(new ProducerRecord<String, byte[]>(Config.Kafka.TOPIC, data.toByteArray()));
    }

    private static void test2(){
        Data d = new Data();
        d.setAltitude(1);
        d.setDataTime("2017-12-17 16:21:44");
        d.setGpsStatus(1);
        d.setLat(39.123);
        d.setLon(120.112);
        d.setDirection(30.2F);
        d.setId(100L);
        String s = JSON.toJSONString(d);
        Data data = JSON.parseObject(s, Data.class);
    }

}
