package br.com.justoeu.kafka.consumer;

/**
 * Created by justoeu on 10/22/16.
 */
public interface IConsumer {

    /**
     * Create configuration to Conect the Kafka Server
     * @param servers
     * @param groupId
     */
    void configure(String servers, String groupId);

}