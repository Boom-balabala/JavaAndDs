package Learn.JUC.Chap1;

public class TwoPLInterrupt {
    public static void main(String[] args) {
        ThwPhaseTermination tpt = new ThwPhaseTermination();
        tpt.start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        tpt.stop();
    }

}

class ThwPhaseTermination {
    private Thread monitor;

    public void start() {
        monitor = new Thread(() -> {
            while (true) {
                if (Thread.currentThread().isInterrupted()) {
                    System.out.println("料理后事");
                    break;
                }
                try {
                    // 在此处被打断会进catch
                    // sleep抛出异常后会清除打断标记，则永远无法被打断
                    Thread.sleep(100);
                    System.out.println("执行监控");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    // 重新设置打断标记
                    Thread.currentThread().interrupt();
                }
            }
        });
        monitor.start();
    }
    public void stop() {
        monitor.interrupt();
    }
}
