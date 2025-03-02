package Learn.Test;

public class JUC_4 implements Runnable{

    @Override
    public void run() {
        System.out.println();
    }
    public static void main(String []args){
        JUC_4 juc_4 = new JUC_4();
        Thread thread = new Thread(juc_4);
        System.out.println(thread.getPriority());
    }
}
