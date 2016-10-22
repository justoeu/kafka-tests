package br.com.justoeu.kafka.producer;

import java.util.concurrent.ExecutionException;

public interface IProducer {

    /**
     * create configuration for the producer
     */
    IProducer configure(String brokerList, String topic, String sync);

    /**
     * create record and send to Kafka
     * because the key is null, data will be sent to a random partition.
     * exact behavior will be different depending on producer implementation
     */
    void produce(String s) throws ExecutionException, InterruptedException;

    /**
     * return what produce Mode was set
     * @return
     */
    String getProducerMode();

    void start();
    void close();
}