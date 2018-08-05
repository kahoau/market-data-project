# market-data-project
market-data-project


start zookeeper
cd C:\dev\softwares\zookeeper-3.4.13
zkserver

start kafka
cd C:\dev\softwares\kafka_2.12-2.0.0
.\bin\windows\kafka-server-start.bat .\config\server.properties

create topic 
cd C:\dev\softwares\kafka_2.12-2.0.0\bin\windows
kafka-topics.bat --create --zookeeper localhost:2181 --replication-factor 1 --partitions 1 --topic test


Reference:
https://dzone.com/articles/running-apache-kafka-on-windows-os
https://medium.com/@cheron.antoine/tuto-building-a-reactive-restful-api-with-spring-webflux-java-258fd4dbae41