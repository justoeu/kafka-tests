package br.com.justoeu.producer;

import java.util.concurrent.ExecutionException;

public interface IProducer {

    /**
     * create configuration for the producer
     * consult Kafka documentation for exact meaning of each configuration parameter
     */
    void configure(String brokerList, String sync);

    /* start the producer */
    void start();

    /**
     * create record and send to Kafka
     * because the key is null, data will be sent to a random partition.
     * exact behavior will be different depending on producer implementation
     */
    void produce(String s) throws ExecutionException, InterruptedException;

    void close();
}