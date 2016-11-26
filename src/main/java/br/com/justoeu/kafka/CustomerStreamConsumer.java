package br.com.justoeu.kafka;

import br.com.justoeu.kafka.consumer.impl.ConsumerStreamImpl;
import lombok.extern.slf4j.Slf4j;

/**
 * Created by valmir.justo on 01/11/16.
 */
@Slf4j
public class CustomerStreamConsumer {

    public static final String BROKER_LIST = "localhost:9092";
    public static final String GROUP_ID = "kafkaClient";
    public static final long TIMEOUT = 100;

    public static void main(String[] args) {

        if (args.length == 0) {
            System.out.println("CustomerProducer {nameOfTopic} ");
            return;
        }

        ConsumerStreamImpl consumer = null;

        try{

            String topic = String.valueOf(args[0]);

            consumer = new ConsumerStreamImpl().configure(BROKER_LIST,GROUP_ID,topic);
            consumer.start();

            String message;

            while ((message = consumer.getNextMessage()) != null) {
                log.info(message);
            }

            // uncomment if you wish to commit offsets on every message
            // consumer.getConsumer().commitOffsets();

        } catch (Exception e){
            log.error(e.getMessage(),e);
        } finally {
            //consumer.close();
        }


    }

}
