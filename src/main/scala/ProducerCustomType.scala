import java.util.Properties

import org.apache.kafka.clients.producer.{KafkaProducer, ProducerRecord}

object ProducerCustomType {
    def main(args: Array[String]): Unit = {
      val kafkaProps = new Properties()
      kafkaProps.put("bootstrap.servers", "localhost:9092")
      kafkaProps.put("value.serializer", "CustomerSerializer")
      kafkaProps.put("key.serializer", "org.apache.kafka.common.serialization.IntegerSerializer")

      val producer = new KafkaProducer[Int, Customer](kafkaProps)

      for (a <- 1 to 10) {
        val record = new ProducerRecord[Int, Customer]("CustomCustomerCountry", a, Customer(a, s"cust-$a"))
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
