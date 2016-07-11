import java.nio.ByteBuffer
import java.util

import org.apache.kafka.common.serialization.Serializer

/**
  * Created by tambunanw on 7/8/16.
  */
class CustomerSerializer extends Serializer[Customer] {

  override def serialize(topic: String, data: Customer): Array[Byte] = {
    val serializeName = data.name.getBytes("UTF-8")
    val stringSize = serializeName.length

    val buffer = ByteBuffer.allocate(4 + 4 + stringSize);
    buffer.putInt(data.id)
    buffer.putInt(stringSize)
    buffer.put(serializeName)

    buffer.array()
  }

  override def close(): Unit = {

  }

  override def configure(map: util.Map[String, _], b: Boolean): Unit = {

  }
}
