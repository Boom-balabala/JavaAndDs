package Learn.JUC;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MyThread extends Thread {
    // public class Thread implements Runnable
    @Override
    public void run() {
        System.out.println("MyThread running");
    }

    public static void main(String[] args) {

    }

}
