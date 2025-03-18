package Learn.JUC.Chap1;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

public class ThreadCreate {
    public static void main(String[] args) {
        // 1. 继承Thread类
        Thread thread = new Thread("T1") {
            @Override
            public void run() {
                System.out.println("Hello World");
            }
        };
        // 等价于thread.setName("T1");
        thread.start();

        // 2. 实现Runnable接口
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                System.out.println("Hello World");
            }
        };
        new Thread(runnable).start();

        // 3. Thread 使用Lambda
        Thread thread1 = new Thread(() -> System.out.println("Hello World"));
        // 等价于
        Runnable runnable1 = () -> System.out.println("Hello World");
        new Thread(runnable1).start();


        // Callable+FutureTask
        FutureTask<String> ft = new FutureTask<>(new Callable<String>() {
            @Override
            public String call() throws Exception {
                return "Running";
            }
        });
        new Thread(ft).start();
        // 获取结果
        try {
            System.out.println(ft.get());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
