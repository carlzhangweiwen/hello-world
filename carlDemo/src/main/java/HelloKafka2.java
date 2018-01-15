//import org.apache.kafka.clients.admin.AdminClient;
//import org.apache.kafka.clients.admin.AdminClientConfig;
//import org.apache.kafka.clients.admin.ListTopicsResult;
//import org.apache.kafka.clients.admin.TopicListing;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * @author zhangweiwen
 * @date 2018/1/10
 */
public class HelloKafka2 {
    public static void main(String[] args) throws Exception{
        String kafkaConnect = "localhost:9091,localhost:9092,localhost:9093";
        Properties config = new Properties();
//        config.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaConnect);
//
//        AdminClient admin = AdminClient.create(config);
//
//        Map<String, String> configs = new HashMap<String,String>();
//        int partitions = 1;
//        short replication = 1;
//
////        CreateTopicsResult result = admin.createTopics(asList(new NewTopic("mynewtopic", partitions, replication).configs(configs)));
//
//        ListTopicsResult result = admin.listTopics();
//        for (Map.Entry<String, TopicListing> entry : result.namesToListings().get().entrySet()) {
//
//            System.out.println("topic {} created:"+ entry.getValue().name());
//        }
    }




}

