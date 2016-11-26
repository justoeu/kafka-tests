package br.com.justoeu.kafka.consumer;

import br.com.justoeu.kafka.consumer.impl.ConsumerImpl;
import br.com.justoeu.kafka.consumer.impl.ConsumerStreamImpl;

/**
 * Created by justoeu on 10/22/16.
 */
public interface IConsumer<T> {

    /**
     * Create configuration to Conect the Kafka Server
     * @param servers
     * @param groupId
     */
    IConsumer configure(String servers, String groupId);
    IConsumer configure(String servers, String groupId, String topic);

    void start();
    void close();

    T getConsumer();
}