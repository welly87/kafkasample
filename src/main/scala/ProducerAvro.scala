import java.util.Properties

import example.avro.User
import org.apache.kafka.clients.producer.{KafkaProducer, ProducerRecord}

object ProducerAvro {
    def main(args: Array[String]): Unit = {
      // need to use confluent for this one
      // run zookeeper, kafka and schema registry from confluent platform
      // schema registry is on 8081


      // http://docs.confluent.io/1.0/schema-registry/docs/serializer-formatter.html
      //

      val kafkaProps = new Properties()
      kafkaProps.put("bootstrap.servers", "localhost:9092")
      kafkaProps.put("value.serializer", "io.confluent.kafka.serializers.KafkaAvroSerializer")
      kafkaProps.put("key.serializer", "org.apache.kafka.common.serialization.IntegerSerializer")
      kafkaProps.put("schema.registry.url", "http://localhost:8081")

      val producer = new KafkaProducer[Int, User](kafkaProps)

      for (a <- 1 to 10) {
        val record = new ProducerRecord[Int, User]("user-avro2", a, new User("welly", a, "929"))
        try {
          println("sending")
          val metadata = producer.send(record).get()

          println(metadata.offset())

        } catch {
          case x: Exception => {
            x.printStackTrace()
          }
          case _ => println("error")
        }
      }


    }
}
