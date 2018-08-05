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


CREATE KEYSPACE MarketData
  WITH REPLICATION = { 'class' : 'SimpleStrategy', 'replication_factor' : 3 };
  
USE MarketData;

CREATE TABLE quote (
	quote_id text,
    price decimal,
    ts timestamp,
    PRIMARY KEY (quote_id,ts)
) WITH CLUSTERING ORDER BY (ts DESC);


INSERT INTO quote (quote_id,price, ts) 
VALUES ('1',88.5, toTimestamp(now()));

SELECT * FROM quote LIMIT 2;


Reference:
https://dzone.com/articles/running-apache-kafka-on-windows-os
https://medium.com/@cheron.antoine/tuto-building-a-reactive-restful-api-with-spring-webflux-java-258fd4dbae41

https://stackoverflow.com/questions/26168859/cassandra-cql-how-to-select-the-last-n-rows-from-a-table
https://docs.datastax.com/en/cql/3.3/cql/cql_reference/cql_data_types_c.html