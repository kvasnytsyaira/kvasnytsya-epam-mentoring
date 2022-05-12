//package tasks;
//
//import java.util.HashMap;
//import java.util.Random;
//
//public class Task1ForJava6 {
//
////    Create HashMap<Integer, Integer>.
////    The first thread adds elements into the map, the other go along the given map and sum the values.
////    Threads should work before catching ConcurrentModificationException.
////    Try to fix the problem with ConcurrentHashMap and Collections.synchronizedMap().
////    What has happened after simple Map implementation exchanging? How it can be fixed in code?
////    Try to write your custom ThreadSafeMap with synchronization and without.
////    Run your samples with different versions of Java (6, 8, and 10, 11) and measure the performance.
////    Provide a simple report to your mentor.
////java 11 = 5126
////java 10 =
////java 8 =
////java 6 =5100 5101
//
//    volatile static HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
//
//    public static void main(String[] args) throws InterruptedException {
//
//        Thread1 threadSum = new Thread1();
//        Thread2 threadAdd = new Thread2();
//
//        long start = System.currentTimeMillis();
//        threadAdd.start();
//        threadSum.start();
//        try {
//            threadAdd.join();
//            threadSum.join();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        long end = System.currentTimeMillis();
//
//        System.out.print(end - start);
//    }
//
//    static class Thread1 extends Thread {
//        @Override
//        public void run() {
//            int sum = 0;
//            for (int i = 0; i < 10; i++) {
//                for (int j = 0; j < map.keySet().size(); j++) {
//                    sum = sum + map.get(j);
//                }
//                System.out.println("Sum = " + sum);
//            }
//            try {
//                Thread.sleep(500);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
//    }
//
//    static class Thread2 extends Thread {
//        @Override
//        public void run() {
//            Random random = new Random();
//            int n = 0;
//            for (int i = 0; i < 10; i++) {
//                System.out.println("Add");
//                map.put(n++, random.nextInt());
//                try {
//                    Thread.sleep(500);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//
//            }
//
//        }
//    }
//}
