package Learn.Test;

public class JUC_7 implements Runnable {
    static int printer = 0;

    @Override
    public void run() {
        while (true) {
            synchronized (this) {
                notify();
                if (printer < 100) {
                    printer++;
                    System.out.println(Thread.currentThread().getName() + " printer is " + printer);
                    try {
                        wait();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }  else {
                    break;
                }
            }

        }
    }
    public static void main(String[] args) {
        JUC_7 juc_7 = new JUC_7();
        Thread t1 = new Thread(juc_7);
        Thread t2 = new Thread(juc_7);
        t1.start();
        t2.start();
    }
}
