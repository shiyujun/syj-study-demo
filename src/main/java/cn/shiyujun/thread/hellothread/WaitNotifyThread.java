package cn.shiyujun.thread.hellothread;

/**
 * d
 *
 * @author syj
 * CreateTime 2019/03/19
 * describe:WaitNotify测试demo
 */
public class WaitNotifyThread {
    public static Object obj = new Object();

    static class WaitThreadDemo extends Thread {
        @Override
        public void run() {
            synchronized (obj) {
                try {
                    System.out.println("WaitThread wait,time=" + System.currentTimeMillis());
                    obj.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("WaitThread end,time=" + System.currentTimeMillis());
            }
        }
    }

    static class NotifyThreadDemo extends Thread {
        @Override
        public void run() {
            synchronized (obj) {
                System.out.println("NotifyThread notify,time=" + System.currentTimeMillis());
                obj.notify();
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("NotifyThread end,time=" + System.currentTimeMillis());
            }
        }
    }

    public static void main(String[] args) {
        WaitThreadDemo waitThreadDemo = new WaitThreadDemo();
        NotifyThreadDemo notifyThreadDemo = new NotifyThreadDemo();
        waitThreadDemo.start();
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        notifyThreadDemo.start();
    }
}
