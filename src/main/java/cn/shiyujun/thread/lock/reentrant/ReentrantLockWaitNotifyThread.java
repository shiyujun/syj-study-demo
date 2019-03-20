package cn.shiyujun.thread.lock.reentrant;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * d
 *
 * @author syj
 * CreateTime 2019/03/19
 * describe:WaitNotify测试demo
 */
public class ReentrantLockWaitNotifyThread {

    public static ReentrantLock lock = new ReentrantLock();
    public static Condition condition = lock.newCondition();

    static class WaitThreadDemo extends Thread {
        @Override
        public void run() {
            try {
                System.out.println("WaitThread wait,time=" + System.currentTimeMillis());
                lock.lock();
                condition.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                lock.unlock();
                System.out.println("WaitThread end,time=" + System.currentTimeMillis());
            }

        }
    }

    static class NotifyThreadDemo extends Thread {
        @Override
        public void run() {
                lock.lock();
                System.out.println("NotifyThread notify,time=" + System.currentTimeMillis());
                condition.signal();
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    lock.unlock();
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
