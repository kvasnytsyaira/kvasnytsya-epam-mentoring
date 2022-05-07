package tasks;

import java.util.concurrent.LinkedBlockingQueue;

public class Task4 {
//    Create simple object pool with support for multithreaded environment.
//    No any extra inheritance, polymorphism or generics needed here, just implementation of simple class:
//    Use any blocking approach you like.

    /**
     * Pool that block when it has not any items or it full
     */
    public class BlockingObjectPool {
        private LinkedBlockingQueue<Object> pool;


        /**
         * Creates filled pool of passed size *
         * * @param size of pool
         */
        public BlockingObjectPool(int size, LinkedBlockingQueue<Object> pool) {
            this.pool = pool;
//            return new LinkedBlockingQueue<Object>(size);
        }

        /**
         * Gets object from pool or blocks if pool is empty *
         * * @return object from pool
         */
//        public Object get() {
//            if (!pool.isEmpty()) {
//                return new Object();
//            } else //block
//        }

        /**
         * Puts object to pool or blocks if pool is full *
         * * @param object to be taken back to pool
         */
//        public void take(Object object) {
//            if (!pool.isFull()) {
//                pool.put(object);
//            } else //block
//        }
    }


}
