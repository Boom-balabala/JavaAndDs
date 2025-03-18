package Learn.JUC.Chap1;

import java.sql.Time;
import java.util.concurrent.TimeUnit;

public class HomeWork1 {
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + "洗茶壶");
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println(Thread.currentThread().getName() + "烧水");
            try {
                TimeUnit.SECONDS.sleep(10);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println(Thread.currentThread().getName() + "done");
        },"thread1");
        Thread thread2 = new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + "洗茶杯");
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println(Thread.currentThread().getName() + "拿茶叶");
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println(Thread.currentThread().getName() + "洗茶叶");
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println(Thread.currentThread().getName() + "done");
        },"thread2");


        thread.start();
        thread2.start();
        thread.join();
        thread2.join();
        System.out.println("泡茶喝");
    }
}
