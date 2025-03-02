package Learn.JUC;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SynchronizedExample {

    public void func1() {
        synchronized (this) {
            for (int i = 0; i < 10; i++) {
                System.out.print(i + " ");
            }
            System.out.println();
        }
    }

    public void func2() {
        synchronized (SynchronizedExample.class) {
            for (int i = 0; i < 10; i++) {
                System.out.print(i + " ");
            }
        }
    }


    public static void main(String[] args) {
        /**
         * 对于以下代码，使用 ExecutorService 执行了两个线程，
         * 由于调用的是同一个对象的同步代码块，因此这两个线程会进行同步，
         * 当一个线程进入同步语句块时，另一个线程就必须等待。
         */
        SynchronizedExample e1 = new SynchronizedExample();
        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.execute(e1::func1);
        executorService.execute(e1::func1);
        executorService.shutdown();
        /**
         * 对于以下代码，两个线程调用了不同对象的同步代码块，因此这两个线程就不需要同步。
         * 从输出结果可以看出，两个线程交叉执行。
         */
        SynchronizedExample e2 = new SynchronizedExample();
        SynchronizedExample e3 = new SynchronizedExample();
        ExecutorService executorService2 = Executors.newCachedThreadPool();
        executorService2.execute(e2::func1);
        executorService2.execute(e3::func1);
        SynchronizedExample e4 = new SynchronizedExample();
        ExecutorService executorService3 = Executors.newCachedThreadPool();
        executorService3.execute(e4::func2);
        executorService3.execute(e4::func2);
        executorService3.shutdown();
    }

}