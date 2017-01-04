package br.com.justoeu.kafka;

import br.com.justoeu.kafka.consumer.impl.ConsumerImpl;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.common.TopicPartition;
import org.apache.kafka.common.errors.WakeupException;

import java.util.Collections;

@Slf4j
public class CustomerConsumer {

    public static final String BROKER_LIST = "localhost:9091,localhost:9092";
    public static final String GROUP_ID = "kafkaClient";
    public static final long TIMEOUT = 100;

    public static void main(String[] args) {

        if (args.length == 0) {
            System.out.println("CustomerProducer {nameOfTopic} ");
            return;
        }

        String topic = String.valueOf(args[0]);

        ConsumerImpl consumer = new ConsumerImpl().configure(BROKER_LIST, GROUP_ID);
        consumer.start();

        try {

            consumer.getConsumer().subscribe(Collections.singletonList(topic));

            while (true) {

                ConsumerRecords<String, String> records = consumer.getConsumer().poll(TIMEOUT);

                log.info(System.currentTimeMillis() + "  --  waiting for data...");

                for (ConsumerRecord<String, String> record : records) {
                    log.info("record.value() = " + record.value());
                }

                for (Object tp : consumer.getConsumer().assignment())
                    log.info("Committing offset at position:" + consumer.getConsumer().position((TopicPartition) tp));

                consumer.getConsumer().commitSync();
            }
        } catch (WakeupException e){
            consumer.getConsumer().wakeup();
            log.error(e.getMessage(),e);
        } finally {
            consumer.getConsumer().close();
            log.info("Closed consumer and we are done");
        }
    }



}
