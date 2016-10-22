package br.com.justoeu.kafka.producer.impl;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.RecordMetadata;

@Slf4j
public class ProducerCallback implements Callback {

        @Override
        public void onCompletion(RecordMetadata recordMetadata, Exception e){
            if (e != null) {
                log.error("Error producing to topic " + recordMetadata.topic());
                throw new RuntimeException(e);
            } else{
                log.info("OK - " + recordMetadata.topic());
                //System.out.println("OK - " + recordMetadata.topic());
            }
        }
    }
