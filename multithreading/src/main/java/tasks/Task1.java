package tasks;

import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

public class Task1 {
    //    Create HashMap<Integer, Integer>.
//    The first thread adds elements into the map, the other go along the given map and sum the values.
//    Threads should work before catching ConcurrentModificationException.
//    Try to fix the problem with ConcurrentHashMap and Collections.synchronizedMap().
//    What has happened after simple Map implementation exchanging? How it can be fixed in code?
//    Try to write your custom ThreadSafeMap with synchronization and without.
//    Run your samples with different versions of Java (6, 8, and 10, 11) and measure the performance.
//    Provide a simple report to your mentor.


    volatile static ConcurrentHashMap<Integer, Integer> map = new ConcurrentHashMap<>();

    public static void main(String[] args){

        Random random = new Random();
        Thread threadAdd = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                System.out.println("Add");
                int n = 0;
                map.put(n++, random.nextInt());
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }

        });
        Thread threadSum = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                System.out.println("Sum = " +
                        map.values()
                                .stream()
                                .mapToInt(value -> value)
                                .sum());

                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        long start = System.currentTimeMillis();
        threadAdd.start();
        threadSum.start();
        try {
            threadAdd.join();
            threadSum.join();

        } catch (Exception e) {
            e.printStackTrace();
        }
        long end = System.currentTimeMillis();

        System.out.println(end - start);
    }
}
//java 11 = 5126 5098
//java 10 = 5092 5159
//java 8 = 5116 5106
//java 6 =