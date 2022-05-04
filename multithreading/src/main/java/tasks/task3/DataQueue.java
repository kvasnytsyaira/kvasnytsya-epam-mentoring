package tasks.task3;

import java.util.LinkedList;
import java.util.Queue;

public class DataQueue {
    private final Queue<String> queue = new LinkedList<>();
    private final int max_size;
    private final Object full = new Object();
    private final Object empty = new Object();

    public DataQueue(int max_size) {
        this.max_size = max_size;
    }

    public String remove() {
        synchronized (queue) {
            return queue.poll();
        }
    }

    public void add(String message) {
        synchronized (queue) {
            queue.add(message);
        }
    }

    public boolean isFull() {
        synchronized (queue) {
            return queue.size() == max_size;
        }
    }

    public boolean isEmpty() {
        synchronized (queue) {
            return queue.size() == 0;
        }
    }
    public void waitOnFull() throws InterruptedException {
        synchronized (full) {
            full.wait();
        }
    }

    public void notifyAllOnFull() {
        synchronized (full) {
            full.notifyAll();
        }
    }

    public void waitOnEmpty() throws InterruptedException {
        synchronized (empty) {
            empty.wait();
        }
    }

    public void notifyAllOnEmpty() {
        synchronized (empty) {
            empty.notify();
        }
    }

}
