package co.edureka.producer;

import org.apache.kafka.clients.producer.*;

import java.util.Properties;
import java.util.concurrent.ExecutionException;


public class AsynchronousProducer
{
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        Properties props = new Properties();
        props.put("bootstrap.servers", "192.168.56.101:9101");
        props.put("acks", "1");  //"0" -No ack, "1" only Leader ,"all" ALL
        props.put("retries", 0);  // "0" doesn't re try ; positive value will retry

        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("buffer.memory", "104857600"); // 100Mb   default is 32MB
        props.put("max.block.ms", "30000"); //    default is 60000
        //props.put("batch.size", 16384);  // batch size in bytes "0" means no batching
        //props.put("linger.ms",20000); //default is 0

        Producer<String, String> producer = new KafkaProducer<>(props);
        for(int i = 0; i < 10; i++)
        {
            ProducerRecord<String, String> record = new ProducerRecord<>("my-first-topic", "my-key" + i, "from async producer" + i);
            producer.send(record, new  ProducerCallBack());
        }
        System.out.println("message published");
        //Thread.sleep(60000);
        producer.close();

    }
}

class ProducerCallBack implements Callback
{
    @Override
    public void onCompletion(RecordMetadata recordMetadata, Exception e)
    {
        if( e!=null)
        {
            e.printStackTrace();
        }
        else
        {
            System.out.println("record published to [partition:" + recordMetadata.partition() + ",offset:" + recordMetadata.offset() + "]");
        }
    }
}