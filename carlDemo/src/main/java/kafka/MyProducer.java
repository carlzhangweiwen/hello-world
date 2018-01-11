package kafka;


import org.apache.kafka.clients.producer.*;

import java.util.Properties;


public class MyProducer extends Thread{

    public void run() {

        try {

            Properties props = new Properties();
            props.put("bootstrap.servers", "localhost:9091,localhost:9092,localhost:9093");
//            props.put("acks", "0");
//            props.put("retries", 0);
            props.put("batch.size", 16384);
            props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
            props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

            Producer<String, String> producer = new KafkaProducer<String, String>(props);
            int i = 0;
            while(true) {
                ProducerRecord<String, String> record = new ProducerRecord<String, String>("test", String.valueOf(i), "this is message"+i);
                producer.send(record, new Callback() {
                    public void onCompletion(RecordMetadata metadata, Exception e) {
                        if (e != null)
                            e.printStackTrace();
                        System.out.println("message send to partition " + metadata.partition() + ", offset: " + metadata.offset());
                    }
                });
                i++;
                Thread.sleep(1000);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}