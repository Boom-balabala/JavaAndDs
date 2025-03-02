package Learn.JUC;

import org.junit.Test;

public class JoinExample {

    private class A extends Thread {
        @Override
        public void run() {
            System.out.println("A");
        }
    }

    private class B extends Thread {

        private A a;

        B(A a) {
            this.a = a;
        }

        @Override
        public void run() {
            try {
                System.out.println("a join");
                // a.join() 的作用是让当前线程（也就是线程 B）等待线程 A 执行完成后，才继续执行后面的代码。
                // 因此，在 a.join() 被调用后，线程 B 会暂停，直到线程 A 结束。
                a.join();
                System.out.println("deal");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("B");
        }
    }
    @Test
    public void test() {
        /**
         * 在 test() 方法中，首先调用 b.start()，所以线程 B 启动并输出 "b->a"（这条输出是在主线程执行的）。
         * 线程 B 输出 "a join" 后调用 a.join()。此时，线程 B 暂停，等待线程 A 结束。
         * 接下来，主线程调用 a.start()，启动线程 A。
         * 线程 A 开始执行，输出 "A"，然后线程 A 结束。
         * 线程 A 结束后，a.join() 返回，线程 B 继续执行并输出 "deal" 和 "B"。
         */
        A a = new A();
        B b = new B(a);
        // 当调用 b.start() 时，线程 B 开始运行。
        // 在 B 的 run() 方法中，首先打印 "a join"，然后调用 a.join()。
        b.start();
        System.out.println("b->a");
        a.start();
    }
}