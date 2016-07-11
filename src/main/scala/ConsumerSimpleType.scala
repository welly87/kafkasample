import java.util
import java.util.{Collections, Properties}

import org.apache.kafka.clients.consumer.{ConsumerRebalanceListener, ConsumerRecord, KafkaConsumer}
import org.apache.kafka.common.TopicPartition

import collection.JavaConverters._
import collection.JavaConversions._
/**
  * Created by tambunanw on 7/11/16.
  */
object ConsumerSimpleType {
  def main(args: Array[String]) {
    val props = new Properties()
    props.put("bootstrap.servers", "localhost:9092")
    props.put("group.id", "ConsumerSimpleType")
    props.put("key.deserializer", "org.apache.kafka.common.serialization.IntegerDeserializer")
    props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer")

    val consumer = new KafkaConsumer[Int, String](props)
    consumer.subscribe(Collections.singletonList("SimpleCustomerCountry"), new ConsumerRebalanceListener {
      override def onPartitionsAssigned(collection: util.Collection[TopicPartition]): Unit = {
        println("partitions assigned")

//        for (partition <- collection) {
//          consumer.seek(partition, 0)
//        }

      }

      override def onPartitionsRevoked(collection: util.Collection[TopicPartition]): Unit = {

      }
    })
    consumer.poll(0)
    // consumer.assign(Collections.singletonList(new TopicPartition("SimpleCustomerCountry", 0)))
    //Thread.sleep(5000)
    // consumer.seek(new TopicPartition("SimpleCustomerCountry", 0), 1)

    try {
      while (true) {
        // println(consumer.assignment().size())

        val records = consumer.poll(100)

        for (record <- records) {
          println(record.offset() + " " + record.key() + " " + record.value() + " " + record.partition())
        }

        consumer.commitSync()
        if (records.size > 0)
          throw new Exception
      }
    }
  }
}
