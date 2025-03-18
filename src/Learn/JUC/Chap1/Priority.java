package Learn.JUC.Chap1;

public class Priority {
    public static void main(String[] args) {
        Runnable runnable = () -> {
            int count = 0;
            while (true) {
                count++;
                if (count > 10000) {
                    break;
                }
                System.out.println(Thread.currentThread().getName() + "--->" + count);
            }
        };

        Runnable runnable2 = () -> {
            int count = 0;
            while (true) {
                count++;
                if (count > 10000) {
                    break;
                }
                System.out.println("\t"+Thread.currentThread().getName() + "--->" + count);
            }
        };
        Thread thread1 = new Thread(runnable, "线程1");
        Thread thread2 = new Thread(runnable2, "线程2");
        thread1.setPriority(Thread.MIN_PRIORITY);
        thread2.setPriority(Thread.MAX_PRIORITY);
        thread1.start();
        thread2.start();
    }
}
