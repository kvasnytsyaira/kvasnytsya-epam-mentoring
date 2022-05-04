package tasks.task3;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Task3 {
//    1 point.
//    Implement message bus using Producer-Consumer pattern.
//    Implement asynchronous message bus. Do not use queue implementations from java.util.concurrent.
//    Implement producer, which will generate and post randomly messages to the queue.
//    Implement consumer, which will consume messages on specific topic and log to the console message payload.
//            (Optional) Application should create several consumers and producers that run in parallel.

    public static void main(String[] args) throws InterruptedException {
        DataQueue dataQueue = new DataQueue(3);
        int consumerCount = 5;
        int producerCount = 5;
        Producer producer = new Producer(dataQueue);
        Consumer consumer = new Consumer(dataQueue);

        ExecutorService service1 = Executors.newFixedThreadPool(consumerCount);
        ExecutorService service2 = Executors.newFixedThreadPool(producerCount);
        service1.submit(producer);
        service2.submit(consumer);
        TimeUnit.SECONDS.sleep(10);
        producer.stopProducing();
        consumer.stopConsuming();
        dataQueue.isFull();
        service1.shutdown();
        service2.shutdown();

    }

}
