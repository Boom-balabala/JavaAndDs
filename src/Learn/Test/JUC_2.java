package Learn.Test;

public class JUC_2 implements Runnable {
    private static int thread_count = 0;
    private int thread_id = 0;

    public JUC_2() {
        thread_id = thread_count++;
    }

    @Override
    public void run() {
        if (thread_id % 2 == 0) {
            for (int i = 0; i < 100; i+=2) {
                System.out.println("run:" + thread_id + "\t" + i);
            }
        }else {
            for (int i = 1; i < 100; i+=2) {
                System.out.println("run:" + thread_id + "\t" + i);
            }
        }

    }

    public static void main(String[] args) {
        JUC_2 juc2 = new JUC_2();
        JUC_2 juc3 = new JUC_2();
        Thread thread = new Thread(juc2);
        thread.start();
        Thread thread1 = new Thread(juc3);
        thread1.start();
    }
}
