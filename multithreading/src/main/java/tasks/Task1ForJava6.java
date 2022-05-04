//package tasks;
//
//import java.util.HashMap;
//import java.util.Map;
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
//
//    volatile static HashMap<Integer, Integer> map = new HashMap();
//
//    public static void main(String[] args) throws InterruptedException {
//
//        Random random = new Random();
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
//}
////java 11 = 5126
////java 10 =
////java 8 =
////java 6 ={
//
//
//class Thread1 extends Thread {
//
//    public void run(Map<Integer, Integer> map) {
//        for (int i = 0; i < 10; i++) {
//            int sum = 0;
//            for (int j = 0; j < map.keySet().size(); j++) {
//                sum = sum + map.get(j);
//            }
//            System.out.print("Sum = " + sum);
//        }
//        try {
//            Thread.sleep(500);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//    }
//}
//
//class Thread2 extends Thread {
//    public void run(Map<Integer, Integer> map) {
//        Random random = new Random();
//        for (int i = 0; i < 10; i++) {
//            System.out.println("Add");
//            int n = 0;
//            map.put(n++, random.nextInt());
//            try {
//                Thread.sleep(500);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//
//        }
//
//    }
//}
