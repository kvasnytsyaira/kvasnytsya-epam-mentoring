package tasks;

import java.util.concurrent.LinkedBlockingQueue;

public class Task4 {
    BlockingObjectPool blockingObjectPool;

    public static void main(String[] args) throws InterruptedException {
        BlockingObjectPool blockingObjectPool = new BlockingObjectPool(5);

        Thread threadAdd = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    blockingObjectPool.take(new Object());
                    System.out.println("Add" + i);
                } catch (InterruptedException e) {

                }
            }
        });
        Thread threadPut = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    blockingObjectPool.get();
                    System.out.println("Get" + i);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        threadAdd.start();
        threadPut.start();
    }

    /**
     * Pool that block when it has not any items or it full
     */
    public static class BlockingObjectPool {
        private final LinkedBlockingQueue<Object> pool;
        private int size;

        /**
         * Creates filled pool of passed size *
         * * @param size of pool
         */
        public BlockingObjectPool(int size) {
            this.pool = new LinkedBlockingQueue<>(size);
            this.size = size;
        }

        /**
         * Gets object from pool or blocks if pool is empty *
         * * @return object from pool
         */
        public Object get() throws InterruptedException {
            return pool.take();
        }

        /**
         * Puts object to pool or blocks if pool is full *
         * * @param object to be taken back to pool
         */
        public void take(Object object) throws InterruptedException {
            pool.put(object);
        }
    }
}
