package br.com.justoeu.kafka.consumer.impl;

import br.com.justoeu.kafka.constants.KafkaConstants;
import br.com.justoeu.kafka.consumer.IConsumer;
import kafka.consumer.*;
import kafka.javaapi.consumer.ConsumerConnector;
import kafka.serializer.StringDecoder;
import kafka.utils.VerifiableProperties;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * Created by valmir.justo on 01/11/16.
 */
@Slf4j
public class ConsumerStreamImpl implements IConsumer<ConsumerConnector> {

    private Properties kafkaProps = new Properties();
    private ConsumerConnector consumer;
    private ConsumerConfig config;
    private KafkaStream<String, String> stream;
    private String waitTime;
    private String topic;
    private int NUMBER_OF_THREADS = 10;
    private String TIMEOUT = "1000";


    @Override
    public IConsumer configure(String servers, String groupId) {
        throw new UnsupportedOperationException("Method Not Implemented");
    }

    @Override
    public ConsumerStreamImpl configure(String servers, String groupId, String topic) {

        kafkaProps.put(KafkaConstants.KAFKA_ZOOKEEPER_CONNECT, servers);
        kafkaProps.put(KafkaConstants.KAFKA_KEY_GROUP_ID, groupId);
        kafkaProps.put(KafkaConstants.KAFKA_KEY_AUTO_COMMIT_INTERNAL_MS, TIMEOUT);
        kafkaProps.put(KafkaConstants.KAFKA_KEY_AUTO_OFFSET_RESET, KafkaConstants.KAFKA_LARGEST_OFFSET_REST);
        this.topic = topic;

        // Commit manually
        //kafkaProps.put(KafkaConstants.KAFKA_KEY_AUTO_COMMIT_ENABLE,"false");
        // un-comment this if you don't want to wait for data indefinitely
        // kafkaProps.put(KafkaConstants.KAFKA_KEY_CONSUMER_TIMEOUT_MS, waitTime);

        return this;
    }

    @Override
    public void start() {
        config = new ConsumerConfig(kafkaProps);
        consumer = Consumer.createJavaConsumerConnector(config);

        /* We tell Kafka how many threads will read each topic. We have one topic and one thread */
        Map<String, Integer> topicCountMap = new HashMap<>();
        topicCountMap.put(topic, new Integer(NUMBER_OF_THREADS));

        /* We will use a decoder to get Kafka to convert messages to Strings
        * valid property will be deserializer.encoding with the charset to use.
        * default is UTF8 which works for us */
        StringDecoder decoder = new StringDecoder(new VerifiableProperties());

        /* Kafka will give us a list of streams of messages for each topic.
        In this case, its just one topic with a list of a single stream */
        stream = consumer.createMessageStreams(topicCountMap, decoder, decoder).get(topic).get(0);
    }

    @Override
    public void close() {
        consumer.shutdown();
    }

    public String getNextMessage() {
        ConsumerIterator<String, String> it = stream.iterator();

        try {
            return it.next().message();
        } catch (ConsumerTimeoutException e) {
            log.error("waited " + waitTime + " and no messages arrived.");
            return null;
        }
    }

    @Override
    public ConsumerConnector getConsumer() {
        return consumer;
    }
}
