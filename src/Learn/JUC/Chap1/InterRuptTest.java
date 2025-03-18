package Learn.JUC.Chap1;
public class InterRuptTest {
    public static void main(String[] args) {
        Thread thread = new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + " start");
            try{
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "thread1");
        thread.start();
        System.out.println(Thread.currentThread().getName() + " interrupt");
        thread.interrupt();
        System.out.println(thread.isInterrupted());
    }
}
