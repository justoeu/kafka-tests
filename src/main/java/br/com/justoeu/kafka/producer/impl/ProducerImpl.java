package br.com.justoeu.kafka.producer.impl;

import br.com.justoeu.kafka.constants.KafkaConstants;
import br.com.justoeu.kafka.producer.IProducer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;
import java.util.concurrent.ExecutionException;

public class ProducerImpl implements IProducer {

    String topic;
    String sync;
    private Properties kafkaProps = new Properties();
    private KafkaProducer<String, String> producer;

    @Override
    public ProducerImpl configure(String brokerList, String topic, String sync) {
        kafkaProps.put(KafkaConstants.KAFKA_KEY_BOOTSTRAP_SERVER, brokerList);
        kafkaProps.put(KafkaConstants.KAFKA_KEY_SERIALIZER_CLASS, KafkaConstants.KAFKA_SERIALIZER_CLASS);
        kafkaProps.put(KafkaConstants.KAFKA_VALUE_SERIALIZER_CLASS, KafkaConstants.KAFKA_SERIALIZER_CLASS);
        kafkaProps.put(KafkaConstants.KAFKA_KEY_ACKS, KafkaConstants.KAFKA_WITH_LOG_LOCAL_ACK);

        kafkaProps.put(KafkaConstants.KAFKA_KEY_MANY_RETRIES, "3");
        kafkaProps.put(KafkaConstants.KAFKA_KEY_TIME_LATENCY_TO_SEND, 5);

        this.sync = sync;
        this.topic = topic;

        return this;
    }

    @Override
    public void start() {
        producer = new KafkaProducer<>(kafkaProps);
    }

    @Override
    public void close() {
        producer.close();
    }

    @Override
    public void produce(String value) throws ExecutionException, InterruptedException {

        ProducerRecord<String, String> record = new ProducerRecord<String, String>(topic, value);

        if (sync.equals(KafkaConstants.KAFKA_PRODUCE_SYNC_MODE)) {
            producer.send(record).get();
        } else {
            producer.send(record, new ProducerCallback());
        }

    }

    @Override
    public String getProducerMode() { return this.sync; }

}