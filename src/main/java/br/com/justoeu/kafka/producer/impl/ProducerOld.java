package br.com.justoeu.kafka.producer.impl;


import br.com.justoeu.kafka.producer.IProducer;
import kafka.javaapi.producer.Producer;
import kafka.producer.KeyedMessage;
import kafka.producer.ProducerConfig;
import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.RecordMetadata;

import java.util.Properties;

// Simple wrapper to the old scala producer, to make the counting code cleaner
public class ProducerOld implements IProducer {
    private Properties kafkaProps = new Properties();
    private Producer<String, String> producer;
    private ProducerConfig config;

    private String topic;

    @Override
    public void start() {
        producer = new Producer<>(config);
    }

    @Override
    public IProducer configure(String brokerList, String topic, String sync) {
        kafkaProps.put("metadata.broker.list", brokerList);
        kafkaProps.put("serializer.class", "kafka.serializer.StringEncoder");
        kafkaProps.put("request.required.acks", "1");
        kafkaProps.put("producer.type", sync);
        kafkaProps.put("send.buffer.bytes","550000");
        kafkaProps.put("receive.buffer.bytes","550000");

        config = new ProducerConfig(kafkaProps);
        this.topic = topic;

        return this;
    }

    @Override
    public void produce(String s) {
        KeyedMessage<String, String> message = new KeyedMessage<String, String>(topic, null, s);
        producer.send(message);
    }

    @Override
    public String getProducerMode() {
        return "";
    }

    @Override
    public void close() {
        producer.close();
    }
}