# kafka-tests
Project using to tests using Kafka

To Start this application including this attributes localhost:9092 first new async 5 1000

#Command Utils

##Start Zookeeper (Remember: Kafka depends the Zookeeper to sync and keep sessions and connections (Producers and Consummers)
    ./zookeeper-server-start.sh ../config/zookeeper.properties &

##Start the First and Second node of Kafka 
    ./kafka-server-start.sh ../config/server.properties &
    ./kafka-server-start.sh ../config/server2.properties &

##Create Topics
    ./kafka-topics.sh --zookeeper localhost:2181 --create --topic first --partitions 2 --replication-factor 2

##List
    ./kafka-topics.sh --zookeeper localhost:2181 --list

##Describe
    ./kafka-topics.sh --zookeeper localhost:2181 --describe --topic first

##Producer
    ./kafka-console-producer.sh --broker-list localhost:9092 --topic first

##Consumer
    ./kafka-console-consumer.sh --zookeeper localhost:2181 --topic first

##Consumer load all Messages
    ./kafka-console-consumer.sh --zookeeper localhost:2181 --topic first --from-beginning

##Information
- if you use the Producer setting async mode to send the messagens, the producer can't stop/close until all messagens were send. If this happens, propably the messages could be lost cause the Producer use buffer to send and if it not be complete before the producer is closed, the messages will lost. The other way to avoid this is use sync mode but remember, this normaly is slower than async mode. On the other hand, using the new Producer Kafka (KafkaProducer) is possible to create a callback method to check if the producer success to send message to Kafka Server.
