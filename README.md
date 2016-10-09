# kafka-tests
Project using to tests using Kafka

To Start this application including this attributes localhost:9092 first new async 5 1000

#Command Utils

#Start Zookeeper (Remember: Kafka depends the Zookeeper to sync and keep sessions and connections (Producers and Consummers)
./zookeeper-server-start.sh ../config/zookeeper.properties &

#Start the First and Second node of Kafka 
  ./kafka-server-start.sh ../config/server.properties &
  ./kafka-server-start.sh ../config/server2.properties &

#Create Topics
./kafka-topics.sh --zookeeper localhost:2181 --create --topic first --partitions 2 --replication-factor 2

#List
./kafka-topics.sh --zookeeper localhost:2181 --list

#Describe
./kafka-topics.sh --zookeeper localhost:2181 --describe --topic first

#Producer
./kafka-console-producer.sh --broker-list localhost:9092 --topic first

#Consumer
./kafka-console-consumer.sh --zookeeper localhost:2181 --topic first

#Consumer load all Messages
./kafka-console-consumer.sh --zookeeper localhost:2181 --topic first --from-beginning
