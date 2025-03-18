package Learn.JUC.Chap1;

public class joinTest {
    static int r = 0;

    public static void main(String[] args) {
        test1();
    }

    public static void test1() {
        System.out.println("test1开始");
        Thread t1 = new Thread(() -> {
            System.out.println("开始");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("结束");
            r = 1;
        });
        t1.start();
        try {
            t1.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(r);
        System.out.println("test1结束");
    }
}
