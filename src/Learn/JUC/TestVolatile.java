package Learn.JUC;

import java.util.concurrent.TimeUnit;

import static java.lang.Thread.sleep;

public class TestVolatile {
    private volatile static boolean stop = false;

    public static void main(String[] args) {
        // Thread-A
        new Thread("Thread A") {
            @Override
            public void run() {
                while (!stop) {
                }
                System.out.println(Thread.currentThread() + " 停止");
            }
        }.start();

        // Thread-main
        try {
            sleep(1);
            System.out.println(Thread.currentThread().getName() + " after 1 seconds");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        stop = true;
    }
}