package br.com.justoeu.producer;


import br.com.justoeu.builder.CustomerBuilder;
import br.com.justoeu.commons.JsonUtils;
import br.com.justoeu.producer.impl.ProducerNewJava;
import br.com.justoeu.producer.impl.ProducerOld;

import java.util.concurrent.ExecutionException;

/**
 * Created by valmir.justo on 9/12/16.
 */
public class SimplesCounterProducer {

    private static IProducer producer;

    //localhost:9092 first new async 5 1000

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        if (args.length == 0) {
            System.out.println("SimpleCounter {broker-list} {topic} {type old/new} {type sync/async} {delay (ms)} {count}");
            return;
        }

        /* get arguments */
        String brokerList = args[0];
        String topic = args[1];
        String age = args[2];
        String sync = args[3];
        int delay = Integer.parseInt(args[4]);
        int count = Integer.parseInt(args[5]);

        if (age.equals("old"))
            producer = new ProducerOld(topic);
        else if (age.equals("new"))
            producer = new ProducerNewJava(topic);
        else {
            System.out.println("Third argument should be old or new, got " + age);
            System.exit(-1);
        }

        /* start a producer */
        producer.configure(brokerList, sync);
        producer.start();

        long startTime = System.currentTimeMillis();
        System.out.println("Starting...");
        producer.produce("Starting...");

        CustomerBuilder builder = new CustomerBuilder();

        /* produce the numbers */
        for (int i=0; i < count; i++ ) {
//            producer.produce(Integer.toString(i));
            producer.produce(JsonUtils.toJson(builder.buildFake()));
            Thread.sleep(delay);
        }

        long endTime = System.currentTimeMillis();
        System.out.println("... and we are done. This took " + (endTime - startTime) + " ms.");
        producer.produce("... and we are done. This took " + (endTime - startTime) + " ms.");

        /* close shop and leave */
        producer.close();
        System.exit(0);
    }

}
