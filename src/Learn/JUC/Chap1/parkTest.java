package Learn.JUC.Chap1;

import java.util.concurrent.locks.LockSupport;

public class parkTest {
    public static void main(String[] args) throws InterruptedException {
        test1();
    }
    public static void test1() throws InterruptedException {
        Thread t = new Thread(()->{
            System.out.println("park...");
            LockSupport.park();
            System.out.println("unpark...");
            System.out.println("打断状态"+Thread.currentThread().isInterrupted());
        },"t1");
        t.start();
        Thread.sleep(1);
        t.interrupt();
    }
}
