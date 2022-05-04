package tasks.task3;

public class Consumer implements Runnable {
    private final DataQueue dataQueue;
    private volatile boolean toRunOrNotToRun;

    public Consumer(DataQueue dataQueue) {
        this.dataQueue = dataQueue;
        this.toRunOrNotToRun = true;
    }

    @Override
    public void run() {
        try {
            consume();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void consume() throws InterruptedException {
        while (toRunOrNotToRun) {
            if (dataQueue.isEmpty()) {
                dataQueue.waitOnEmpty();
            }
            if (!toRunOrNotToRun) {
                break;
            }
            String remove = dataQueue.remove();
            System.out.println("Removed : " + remove);
            dataQueue.notifyAllOnFull();
        }
    }

    public void stopConsuming() {
        toRunOrNotToRun = false;
        dataQueue.notifyAllOnEmpty();
    }
}
