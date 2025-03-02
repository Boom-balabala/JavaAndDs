package Learn.JUC;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Myrunnable implements Runnable {
    private static int cnt = 0;

    @Override
    public void run() {
        cnt++;
        System.out.println("Myrunnable running" + cnt);

    }

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < 5; i++) {
            executorService.execute(new Myrunnable());
        }
        executorService.shutdown();
        Thread thread = new Thread(new Myrunnable());
        thread.setDaemon(true);
    }
}
