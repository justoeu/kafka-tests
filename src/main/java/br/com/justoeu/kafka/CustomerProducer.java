package br.com.justoeu.kafka;


import br.com.justoeu.kafka.constants.KafkaContants;
import br.com.justoeu.kafka.producer.IProducer;
import br.com.justoeu.kafka.builder.CustomerBuilder;
import br.com.justoeu.kafka.commons.JsonUtils;
import br.com.justoeu.kafka.producer.impl.ProducerImpl;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutionException;

/**
 * Created by valmir.justo on 9/12/16.
 */
@Slf4j
public class CustomerProducer {

    public static final String BROKER_LIST = "localhost:9092";
    public static final String TOPIC_NAME = "first";
    public static final int DELAY = 5;
    public static final String FINISH_MSG = "... finish. Time Total: ";
    public static final String STARTING_MSG = "Starting Producer using ";

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        if (args.length == 0) {
            System.out.println("CustomerProducer {ManyMessages} ");
            return;
        }

        int count = Integer.parseInt(args[0]);

        IProducer producer = new ProducerImpl().configure(BROKER_LIST, TOPIC_NAME, KafkaContants.KAFKA_PRODUCE_ASYNC_MODE);
        producer.start();

        long startTime = System.currentTimeMillis();
        log.info(STARTING_MSG + producer.getProducerMode() + " Mode");

        generateMessage(count, producer);

        long endTime = System.currentTimeMillis();
        log.info(FINISH_MSG + (endTime - startTime) + " ms.");
        producer.produce(FINISH_MSG + (endTime - startTime) + " ms.");

        producer.close();
        System.exit(0);
    }

    private static void generateMessage(int count, IProducer producer) throws ExecutionException, InterruptedException {
        producer.produce(STARTING_MSG + producer.getProducerMode() + " Mode");
        CustomerBuilder builder = new CustomerBuilder();

        for (int i=0; i < count; i++ ) {
//            producer.produce(Integer.toString(i));
            producer.produce(JsonUtils.toJson(builder.buildFake()));
            Thread.sleep(DELAY);
        }
    }

}
