package my;
//daemon线程会随着jvm关闭而关闭:https://app.yinxiang.com/shard/s65/nl/15273355/8248df6d-e635-47ed-954f-59f06c9c37ed/
//daemon线程比user thread的优先级更低。jvm等所有的user thread包括main函数的线程停止，就会停止，即便此时还有daemon线程在执行，也不会care。
//这也就是为什么线程池中的线程都是daemon的。
public class DaemonThread {
    public static void main(String[] args) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < Integer.MAX_VALUE; i++) {//如果不在main中join，则会自动退出，jvm等main退出，就会退出。
                    System.out.println(i);
                    try {
                        Thread.sleep(2);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        thread.setDaemon(true);//如果注释掉这行，thread就是user thread，就不会随着main退出而退出。
        thread.start();

        try {
            thread.sleep(6);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
