package br.com.justoeu.kafka.consumer.impl;

import br.com.justoeu.kafka.constants.KafkaContants;
import br.com.justoeu.kafka.consumer.IConsumer;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.util.Properties;

/**
 * Created by justoeu on 10/22/16.
 */
public class ConsumerImpl implements IConsumer {

    private KafkaConsumer<String, String> consumer;

    private String waitTime;


    @Override
    public void configure(String servers, String groupId) {

        Properties kafkaProps = new Properties();

        kafkaProps.put(KafkaContants.KAFKA_KEY_GROUP_ID, groupId);
        kafkaProps.put(KafkaContants.KAFKA_KEY_BOOTSTRAP_SERVER, servers);
        kafkaProps.put(KafkaContants.KAFKA_KEY_AUTO_OFFSET_RESET,    KafkaContants.KAFKA_EARLIEST_OFFSET_REST);
        kafkaProps.put(KafkaContants.KAFKA_KEY_DESERIALIZER_CLASS,   KafkaContants.KAFKA_DESERIALIZER_CLASS);
        kafkaProps.put(KafkaContants.KAFKA_VALUE_DESERIALIZER_CLASS, KafkaContants.KAFKA_DESERIALIZER_CLASS);
        consumer = new KafkaConsumer<>(kafkaProps);
    }
}

