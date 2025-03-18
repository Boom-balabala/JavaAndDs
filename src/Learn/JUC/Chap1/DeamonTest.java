package Learn.JUC.Chap1;

public class DeamonTest {
    // 这段代码最后只会打印Main End，因为thread被强制终止
    public static void main(String[] args) {
        Thread thread = new Thread(() -> {
            while (true) {
                if (Thread.currentThread().isInterrupted()){
                    System.out.println("thread is interrupted");
                    break;
                }
            }
            System.out.println("End");
        });
        thread.setDaemon(true);
        thread.start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Main End");
    }
}
