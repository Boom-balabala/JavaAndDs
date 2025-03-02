package Learn.JUC.workPro;

/**
 * @author WYL
 */
public class ShopSyn {

    private static final int MAX_DISH = 20;
    private static final int MIN_DISH = 0;
    private static int dish = MIN_DISH;
    private static final Object LOCK = new Object();

    public ShopSyn() {}

    public class producer implements Runnable {

        @Override
        public void run() {
            while (true) {
                synchronized (LOCK) {
                    if (dish >= MAX_DISH) {
                        System.out.println("Dish full");
                        try {
                            LOCK.wait();
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    } else {
                        dish++;
                        System.out.println(
                                Thread.currentThread().getName() + "\tcreate one\t" + dish);
                        LOCK.notifyAll();
                    }
                    try {
                        Thread.sleep(1500);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }
    }

    public class consumer implements Runnable {
        @Override
        public void run() {
            while (true) {
                synchronized (LOCK) {
                    if (dish <= MIN_DISH) {
                        System.out.println("Dish too less");
                        try {
                            LOCK.wait();
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    } else {
                        dish--;
                        System.out.println(
                                Thread.currentThread().getName() + "\t use one \t" + dish);
                        LOCK.notifyAll();
                    }
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        ShopSyn myshop = new ShopSyn();
        Thread t1 = new Thread(myshop.new producer(), "p1");
        Thread t2 = new Thread(myshop.new producer(), "p2");
        Thread t3 = new Thread(myshop.new producer(), "p3");
        Thread t4 = new Thread(myshop.new consumer(), "c1");
        Thread t5 = new Thread(myshop.new consumer(), "c2");
        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();
    }
}
