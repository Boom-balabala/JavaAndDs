package Learn.Test;

public class JUC_1 extends Thread{
    @Override
    public void run() {
        System.out.println("run");
        for (int i = 0; i < 100; i++) {
            System.out.println("run" + i);
        }
    }
    public static void main(String []args){
        JUC_1 juc_1 = new JUC_1();
        juc_1.start();
    }
}
