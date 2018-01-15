package kafka;


import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.PartitionInfo;
import org.apache.kafka.common.TopicPartition;

import java.util.*;

/**
 * @author zhangweiwen
 * @date 2018/1/10
 */
public class MyConsumer extends Thread {
    private String groupid;
    private String topic = "test";

    public MyConsumer(String groupid){
        this.groupid = groupid;
    }
    public void run() {
        System.out.println("groupid:" + groupid);
        Properties props = new Properties();

        props.put("bootstrap.servers", "localhost:9091,localhost:9092,localhost:9093");
        props.put("group.id", groupid);
        props.put("enable.auto.commit", "true");
        props.put("auto.commit.interval.ms", "1000");
        props.put("session.timeout.ms", "30000");
        props.put("partition.assignment.strategy","range");
        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");

        KafkaConsumer<String, String> consumer = new KafkaConsumer<String, String>(props);

        consumer.subscribe(topic);
//        consumer.seekToBeginning(new ArrayList<TopicPartition>());

//        Map<String, List<PartitionInfo>> listTopics = consumer.listTopics();
//        Set<Map.Entry<String, List<PartitionInfo>>> entries = listTopics.entrySet();
//
//        for (Map.Entry<String, List<PartitionInfo>> entry:
//             entries) {
//            System.out.println("topic:" + entry.getKey());
//
//        }

        consumer.seek(new HashMap<TopicPartition, Long>());

        while(true) {
            Map<String, ConsumerRecords<String, String>> records = consumer.poll(1000);

            if(records != null) {
                Set<Map.Entry<String, ConsumerRecords<String, String>>> entries = records.entrySet();
                for (Map.Entry<String, ConsumerRecords<String, String>> entry : entries) {
                    String key = entry.getKey();
                    ConsumerRecords<String, String> value = entry.getValue();
                    System.out.println(key + ":" + value.topic() + ":" + value.records());
                    for (ConsumerRecord<String, String> rec :
                            value.records()) {
                        try {
                            System.out.println(key + ":" + value.topic() + ":" + rec.offset() + "," + rec.partition() + "," + rec.value());
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
//            ConsumerRecords<String, String> records = consumer.poll(1000);
//            for(ConsumerRecord<String, String> record : records) {
//                System.out.println("fetched from partition " + record.partition() + ", offset: " + record.offset() + ", message: " + record.value());
//            }
            //按分区读取数据
//              for (TopicPartition partition : records.partitions()) {
//                  List<ConsumerRecord<String, String>> partitionRecords = records.records(partition);
//                  for (ConsumerRecord<String, String> record : partitionRecords) {
//                      System.out.println("fetched from partition " + partition.partition() + ", "+ record.offset() + ": " + record.value());
//                  }
//              }

        }

    }


}

