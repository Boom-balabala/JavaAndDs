package Learn.JUC;

import java.util.concurrent.atomic.AtomicInteger;

public class CASDemo {
    static AtomicInteger atomicInteger = new AtomicInteger(0);

    public static void main(String[] args) {

        Thread t1 = new Thread(
                new Thread() {
                    @Override
                    public void run() {
                        while (atomicInteger.get() < 1000) {
                            System.out.println(this.getName() + " " + atomicInteger.getAndIncrement());
                        }
                    }
                }
        );
        Thread t2 = new Thread(
                new Thread() {
                    @Override
                    public void run() {
                        while (atomicInteger.get() < 1000) {
                            System.out.println(this.getName() + " " + atomicInteger.getAndIncrement());
                        }
                    }
                }
        );
        t1.start();
        t2.start();
    }
}
