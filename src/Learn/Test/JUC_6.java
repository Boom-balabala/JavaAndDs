package Learn.Test;

import java.time.LocalTime;
import java.util.concurrent.locks.ReentrantLock;
public class JUC_6 implements Runnable{
    static int money = 0;
    private static ReentrantLock lock = new ReentrantLock();
    @Override
    public void run() {
        for (int i = 0; i < 3; i++) {
            addmoney();
        }
    }
    private void addmoney(){
        lock.lock();
        money+=1000;
        System.out.println(Thread.currentThread().getName()+" money is "+money);
        lock.unlock();
    }
    public static void main(String[] args) {
        JUC_6 juc_6 = new JUC_6();
        Thread t1 = new Thread(juc_6);
        Thread t2 = new Thread(juc_6);
        t1.start();
        t2.start();
    }
}
