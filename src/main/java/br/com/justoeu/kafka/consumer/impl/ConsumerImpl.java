package br.com.justoeu.kafka.consumer.impl;

import br.com.justoeu.kafka.constants.KafkaConstants;
import br.com.justoeu.kafka.consumer.IConsumer;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.util.Properties;

/**
 * Created by justoeu on 10/22/16.
 */
public class ConsumerImpl implements IConsumer<KafkaConsumer> {

    private KafkaConsumer<String, String> consumer;
    Properties kafkaProps = new Properties();

    @Override
    public ConsumerImpl configure(String servers, String groupId) {

            kafkaProps.put(KafkaConstants.KAFKA_KEY_GROUP_ID, groupId);
            kafkaProps.put(KafkaConstants.KAFKA_KEY_BOOTSTRAP_SERVER, servers);
            kafkaProps.put(KafkaConstants.KAFKA_KEY_AUTO_OFFSET_RESET,    KafkaConstants.KAFKA_EARLIEST_OFFSET_REST);
            kafkaProps.put(KafkaConstants.KAFKA_KEY_DESERIALIZER_CLASS,   KafkaConstants.KAFKA_DESERIALIZER_CLASS);
            kafkaProps.put(KafkaConstants.KAFKA_VALUE_DESERIALIZER_CLASS, KafkaConstants.KAFKA_DESERIALIZER_CLASS);

            return this;
    }

    @Override
    public IConsumer configure(String servers, String groupId, String topic) {
        throw new UnsupportedOperationException("Method Not Implemented");
    }

    @Override
    public void start() { consumer = new KafkaConsumer<>(kafkaProps); }

    @Override
    public void close() { consumer.close(); }

    @Override
    public KafkaConsumer getConsumer() { return this.consumer;}
}

