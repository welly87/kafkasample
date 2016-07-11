name := "untitled1"

version := "1.0"

scalaVersion := "2.10.6"

libraryDependencies += "org.apache.kafka" % "kafka-clients" % "0.10.0.0"

libraryDependencies += "org.apache.avro" % "avro" % "1.8.1"

libraryDependencies += "io.confluent" % "kafka-avro-serializer" % "3.0.0"
//http://docs.confluent.io/3.0.0/installation.html#maven-repository-for-jars
resolvers +=
  "Confluent IO" at "http://packages.confluent.io/maven"