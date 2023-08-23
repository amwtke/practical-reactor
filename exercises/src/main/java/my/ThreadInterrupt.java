package my;

//对于sleep的线程，interrupt方法可以唤醒它，并且抛出异常。对于正常运行的线程，调用它的interrupt方法不会停止，但是是个线程通信的方式，可以被捕获，处理由程序员定义。
public class ThreadInterrupt {
    public static void main(String[] args) {
        Thread t = new Thread(() -> {
            for (int i = 0; i < 1_000_00; i++) {
                if (Thread.currentThread().isInterrupted()) {
                    System.out.println("ok done!");
                    return;
                }
                System.out.println(i);
//                try {
//                    Thread.sleep(10);
//                } catch (InterruptedException e) {
//                    throw new RuntimeException(e);
//                }
            }
        });
        t.start();
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        t.interrupt();
    }
}
