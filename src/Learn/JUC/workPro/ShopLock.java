package Learn.JUC.workPro;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class ShopLock {
    private static ReentrantLock lock = new ReentrantLock();
    private static final Condition full = lock.newCondition();
    private static final Condition empty = lock.newCondition();

    private static final int MAX_DISH = 20;
    private static final int MIN_DISH = 0;
    private static int dish = MIN_DISH;

    public ShopLock() {}

    public class producer implements Runnable {

        @Override
        public void run() {
            while (true) {
                lock.lock();
                if (dish == MAX_DISH) {
                    System.out.println(Thread.currentThread().getName() + "\t full");
                    try {
                        full.await();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                } else {
                    dish++;
                    System.out.println(
                            Thread.currentThread().getName() + "\t create one \t" + dish);
                    empty.signal();
                }
                lock.unlock();
                try {
                    Thread.sleep(1500);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    public class consumer implements Runnable {
        @Override
        public void run() {
            while (true) {
                lock.lock();
                if (dish == MIN_DISH) {
                    System.out.println(Thread.currentThread().getName() + "\t empty");
                    try {
                        empty.await();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                } else {
                    dish--;
                    System.out.println(Thread.currentThread().getName() + "\t use one \t" + dish);
                    full.signal();
                }
                lock.unlock();
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    public static void main(String[] args) {
        ShopLock myshop = new ShopLock();
        Thread t1 = new Thread(myshop.new producer(), "p1");
        Thread t2 = new Thread(myshop.new producer(), "p2");
        Thread t3 = new Thread(myshop.new producer(), "p3");
        Thread t4 = new Thread(myshop.new consumer(), "c1");
        Thread t5 = new Thread(myshop.new consumer(), "c2");
        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();
    }
}
