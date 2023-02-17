package co.edureka.producer;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

import java.util.Properties;
import java.util.concurrent.ExecutionException;


public class SynchronousProducer
{
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        Properties props = new Properties();
        props.put("bootstrap.servers", "192.168.56.101:9101");
        props.put("acks", "1");  //"0" -No ack, "1" only Leader ,"all" ALL
        props.put("retries", 0);  // "0" doesn't re try ; positive value will retry
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("max.in.flight.req.conn","20");
        //props.put("batch.size", 16384);  // batch size in bytes "0" means no batching
        //props.put("linger.ms",10000); //default is 0


        Producer<String, String> producer = new KafkaProducer<>(props);
        for(int i = 0; i < 10; i++)
        {
            try
            {
                ProducerRecord<String, String> record = new ProducerRecord<>("my-first-topic", "my-key" + i, "from sync producer" + i);
                RecordMetadata recordMetadata = producer.send(record).get();
                System.out.println("record published to [partition:"+recordMetadata.partition() + ",offset:" + recordMetadata.offset()+"]");
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
        System.out.println("message published");
        producer.close();
    }
}