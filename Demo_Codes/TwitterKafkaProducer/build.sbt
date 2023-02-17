name := "TwitterKafkaProducer"

scalaVersion := "2.11.8"

// https://mvnrepository.com/artifact/org.apache.kafka/kafka-clients
libraryDependencies += "org.apache.kafka" % "kafka-clients" % "2.1.0"
// https://mvnrepository.com/artifact/com.twitter/hbc-core
libraryDependencies += "com.twitter" % "hbc-core" % "2.2.0"
// https://mvnrepository.com/artifact/org.slf4j/slf4j-simple
libraryDependencies += "org.slf4j" % "slf4j-simple" % "1.7.30" % Test
resolvers += Resolver.mavenLocal



