package tasks;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Task2 {
    //    1 point.
//    Create three threads:
//1st thread is infinitely writing random number to the collection;
//2nd thread is printing sum of the numbers in the collection;
//3rd is printing square root of sum of squares of all numbers in the collection.
//Make these calculations thread-safe using synchronization block. Fix the possible deadlock.
    static final List<Integer> list = new ArrayList<>();
    static Random random = new Random();

    public static void main(String[] args) {

        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                System.out.println("Thread1 :writing random number");
                list.add(random.nextInt());
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        Thread thread2 = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                System.out.println("Thread2 :sum = " + list.stream().reduce(0, Integer::sum));
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        Thread thread3 = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                System.out.println("Thread3 :square root of sum of squares =" +
                        Math.sqrt(list.stream().reduce(0, (a, b) -> a * a + b * b)));
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        synchronized (list) {
            thread1.start();
            thread2.start();
            thread3.start();
        }
        try {
            thread1.join();
            thread2.join();
            thread3.join();
        } catch (Exception e) {
            System.out.println("Interrupted");
        }
    }
}