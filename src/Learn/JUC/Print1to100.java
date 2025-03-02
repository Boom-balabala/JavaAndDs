package Learn.JUC;

/**
 * @author WYL
 */
public class Print1to100 implements Runnable {
    private static final Object lock = new Object();
    private static int number = 0;
    private static int threadNumbers = 0;
    private final int id;

    public Print1to100() {
        synchronized (lock) {
            this.id = threadNumbers;
            threadNumbers++;
        }
    }

    @Override
    public void run() {
        while (true){
            synchronized (lock){
                if (number==100){
                    break;
                }
                else if ((number%5)==id){
                    number++;
                    System.out.println(number+"\t Thread:\t"+id);
                    lock.notifyAll();
                }else{
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        Thread t1 = new Thread(new Print1to100());
        Thread t2 = new Thread(new Print1to100());
        Thread t3 = new Thread(new Print1to100());
        Thread t4 = new Thread(new Print1to100());
        Thread t5 = new Thread(new Print1to100());
        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();
    }
}
