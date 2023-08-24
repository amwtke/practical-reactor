package my;
//daemon线程会随着jvm关闭而关闭:https://app.yinxiang.com/shard/s65/nl/15273355/8248df6d-e635-47ed-954f-59f06c9c37ed/
public class DaemonThread {
    public static void main(String[] args) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < Integer.MAX_VALUE; i++) {
                    System.out.println(i);
                    try {
                        Thread.sleep(2);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        thread.setDaemon(true);
        thread.start();

        try {
            thread.sleep(6);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
