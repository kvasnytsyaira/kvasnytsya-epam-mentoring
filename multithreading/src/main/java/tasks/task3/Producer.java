package tasks.task3;

import java.util.Random;

public class Producer implements Runnable {
    private final DataQueue dataQueue;
    private volatile boolean toRunOrNotToRun;
    private final Random random = new Random();

    @Override
    public void run() {
        try {
            produce();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public Producer(DataQueue dataQueue) {
        this.dataQueue = dataQueue;
        this.toRunOrNotToRun = true;
    }

    public void produce() throws InterruptedException {
        while (toRunOrNotToRun) {
            while (dataQueue.isFull()) {
                dataQueue.waitOnFull();
            }
            if (!toRunOrNotToRun) break;
            String newMessage = String.valueOf(random.nextInt());
            dataQueue.add(newMessage);
            System.out.println("Added : " + newMessage);
            dataQueue.notifyAllOnEmpty();
        }
    }

    public void stopProducing() {
        toRunOrNotToRun = false;
        dataQueue.notifyAllOnFull();
    }

}
