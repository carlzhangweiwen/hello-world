import kafka.MyConsumer;

/**
 * @author zhangweiwen
 * @date 2018/1/10
 */
public class HelloKafka {
    public static void main(String[] args) {

        new MyConsumer("test-consumer-group").start();

    }


}

