package br.com.justoeu.kafka.constants;

/**
 * Created by justoeu on 10/22/16.
 *
 * for others configuration http://kafka.apache.org/documentation.html
 */
public class KafkaContants {

    public static final String KAFKA_KEY_GROUP_ID = "group.id";
    public static final String KAFKA_KEY_BOOTSTRAP_SERVER = "bootstrap.servers";
    public static final String KAFKA_KEY_AUTO_OFFSET_RESET = "auto.offset.reset";
    public static final String KAFKA_KEY_SERIALIZER_CLASS = "key.serializer";
    public static final String KAFKA_KEY_DESERIALIZER_CLASS = "key.deserializer";
    public static final String KAFKA_VALUE_SERIALIZER_CLASS = "value.serializer";
    public static final String KAFKA_VALUE_DESERIALIZER_CLASS = "value.deserializer";
    public static final String KAFKA_KEY_ACKS = "acks";
    public static final String KAFKA_KEY_MANY_RETRIES = "retries";
    public static final String KAFKA_KEY_TIME_LATENCY_TO_SEND = "linger.ms";

    public static final String KAFKA_EARLIEST_OFFSET_REST = "earliest";
    public static final String KAFKA_SERIALIZER_CLASS = "org.apache.kafka.common.serialization.StringSerializer";
    public static final String KAFKA_DESERIALIZER_CLASS = "org.apache.kafka.common.serialization.StringDeserializer";

    public static final String KAFKA_WITHOUT_ACK = "0";
    public static final String KAFKA_WITH_LOG_LOCAL_ACK = "1";
    public static final String KAFKA_ALL_SERVERS_ACK = "-1";

    public static final String KAFKA_PRODUCE_ASYNC_MODE = "async";
    public static final String KAFKA_PRODUCE_SYNC_MODE = "sync";
}
