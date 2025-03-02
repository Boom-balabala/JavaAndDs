package Learn.Test;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

import static java.lang.Thread.sleep;

public class JUC_8 {
    class clerk {
        private int minProduct = 0;
        private int maxProduct = 20;
        private int product = 0;

        public synchronized void soldout() throws InterruptedException {
            if (product == minProduct) {
                System.out.println("存储已空");
                wait();
            } else {
                product--;
                System.out.println(Thread.currentThread().getName() + " 出售了一个产品，现在有： " + product);
                notify();
            }
        }

        public synchronized void addProduct() throws InterruptedException {
            if (product == maxProduct) {
                System.out.println("存储已满");
                wait();
            } else {
                product++;
                System.out.println(Thread.currentThread().getName() + " 加入了一个产品，现在有： " + product);
                notify();
            }
        }
    }

    class producer implements Runnable {
        private clerk myclerk;

        public producer(clerk clerk) {
            this.myclerk = clerk;
        }

        @Override
        public void run() {
            while (true) {
                try {
                    Random rand = new Random();
                    myclerk.addProduct();
                    sleep(rand.nextInt(100));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    class consumer implements Runnable {
        private clerk myclerk;

        public consumer(clerk clerk) {
            this.myclerk = clerk;
        }

        @Override
        public void run() {
            while (true) {
                Random rand = new Random();
                try {
                    myclerk.soldout();
                    sleep(rand.nextInt(300));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        JUC_8 juc_8 = new JUC_8();
        clerk clerk = juc_8.new clerk();
        producer producer = juc_8.new producer(clerk);
        consumer consumer = juc_8.new consumer(clerk);
        Thread t1 = new Thread(producer, "生产者1");
        Thread t2 = new Thread(consumer, "消费者1");
        Thread t3 = new Thread(producer, "生产者2");
        Thread t4 = new Thread(consumer, "消费者2");
        Thread t5 = new Thread(producer, "生产者3");
        Thread t6 = new Thread(consumer, "消费者3");
        ExecutorService executorService = Executors.newCachedThreadPool();
        ThreadPoolExecutor threadPoolExecutor = (ThreadPoolExecutor) executorService;
        threadPoolExecutor.execute(t1);
        threadPoolExecutor.execute(t2);
        threadPoolExecutor.execute(t4);
        threadPoolExecutor.execute(t6);
    }
}


