import java.util.Properties

import org.apache.kafka.clients.producer.{KafkaProducer, ProducerRecord}

/**
  * Created by tambunanw on 7/5/16.
  */

object ProducerSimpleType {
    def main(args: Array[String]): Unit = {
      val kafkaProps = new Properties()
      kafkaProps.put("bootstrap.servers", "localhost:9092")
      kafkaProps.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer")
      kafkaProps.put("key.serializer", "org.apache.kafka.common.serialization.IntegerSerializer")

      val producer = new KafkaProducer[Int, String](kafkaProps)

      for (a <- 1 to 10) {
        val record = new ProducerRecord[Int, String]("SimpleCustomerCountry", a, s"cust-$a")
        try {
//          val metadata = producer.send(record, new Callback {
//            override def onCompletion(recordMetadata: RecordMetadata, e: Exception): Unit = {
//              println(recordMetadata.offset())
//            }
//          }).get()

          val metadata = producer.send(record).get()

          println(metadata.offset())

        } catch {
          case x: Exception => {
            x.printStackTrace()
          }
        }
      }


    }
}
