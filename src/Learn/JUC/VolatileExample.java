package Learn.JUC;

//假设线程A执行writer方法，线程B执行reader方法
public class VolatileExample {
    int a = 0;
    volatile boolean flag = false;
    // 对一个 volatile 域的写，happens-before 于任意后续对这个 volatile 域的读。
    public void writer() {
        // 1 线程A修改共享变量
        a = 1;
        // 2 线程A写volatile变量
        flag = true;
    }

    public void reader() {
        if (flag) {
            // 3 线程B读同一个volatile变量
            int i = a;
            // 4 线程B读共享变量
            System.out.println(i);
        }
    }
    public static void main(String[] args) {
        VolatileExample volatileExample = new VolatileExample();
        Thread t1 = new Thread(() -> {
            volatileExample.writer();
        });
        Thread t2 = new Thread(() -> {
            volatileExample.reader();
        });
        t1.start();
        t2.start();
    }
}
