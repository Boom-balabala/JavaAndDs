package Learn.Test;

public class JUC_5 {
    public static void main(String[] args) {
        Thread thread = new Thread() {
            @Override
            public void run() {
                for (int i = 2; i < 100; i+=2) {
                    System.out.println("Thread 1:print\t"+i);
                    try {
                        sleep(10);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        };
        thread.start();
        Thread thread2 = new Thread(){
            @Override
            public void run(){
                for (int i = 1; i < 100; i+=2) {
                    System.out.println("Thread 2:print\t"+i);
                    if (i == 5) {
                        Thread.yield();
                        try {
                            thread.join();
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }
            }
        };
        thread2.start();
    }
}

