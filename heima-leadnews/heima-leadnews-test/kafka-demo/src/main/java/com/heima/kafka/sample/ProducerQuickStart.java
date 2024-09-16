package com.heima.kafka.sample;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.*;

import java.util.Properties;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * 生产者
 */
@Slf4j
public class ProducerQuickStart {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        //1.kafka的配置信息
        Properties properties = new Properties();
        //kafka的连接地址
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,"123.249.120.19:9092");

        //acks确认机制-默认1，表示只要leader副本收到消息，就返回确认信息
        properties.put(ProducerConfig.ACKS_CONFIG,"all");
        //发送失败，失败的重试次数
        properties.put(ProducerConfig.RETRIES_CONFIG,5);
        //消息压缩
        properties.put(ProducerConfig.COMPRESSION_TYPE_CONFIG,"snappy");

        //消息key的序列化器
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,"org.apache.kafka.common.serialization.StringSerializer");
        //消息value的序列化器
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,"org.apache.kafka.common.serialization.StringSerializer");

        //2.生产者对象
        KafkaProducer<String,String> producer = new KafkaProducer<String, String>(properties);
        //封装发送的消息
        ProducerRecord<String, String> record = new ProducerRecord<>("topic-first",0,"10001","hello kafka");
        //3.发送消息

        //同步发送：
        /*Future<RecordMetadata> recordMetadata = producer.send(record);
        System.out.println(recordMetadata.get().offset());*/

        //异步发送：
        producer.send(record,new Callback() {
            @Override
            public void onCompletion(RecordMetadata recordMetadata, Exception e) {
                if(e != null){
                    System.out.println("异步发送kafka失败"+e);
                }
                System.out.println("异步发送kafka成功"+recordMetadata.offset()+" "+recordMetadata);
            }
        });

        //4.关闭消息通道，必须关闭，否则消息发送不成功
        producer.close();
    }

}
