package cn.shiyujun.thread.lock.reentrant;

import java.util.concurrent.locks.ReentrantLock;

/**
 * d
 *
 * @author syj
 * CreateTime 2019/03/21
 * describe: 重入锁响应中断demo
 */
public class ReentrantLockInterruptDemo {
    public static ReentrantLock lock1 = new ReentrantLock();
    public static ReentrantLock lock2 = new ReentrantLock();


    static class ThreadDemo extends Thread {
        int i = 0;

        public ThreadDemo(int i) {
            this.i = i;
        }

        @Override
        public void run() {
            try {
                if (i == 1) {
                    lock1.lockInterruptibly();
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    lock2.lockInterruptibly();
                } else {
                    lock2.lockInterruptibly();
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    lock1.lockInterruptibly();
                }
                System.out.println(Thread.currentThread().getName() + "完成任务");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                if (lock1.isHeldByCurrentThread()) {
                    lock1.unlock();
                }
                if (lock2.isHeldByCurrentThread()) {
                    lock2.unlock();
                }
                System.out.println(Thread.currentThread().getName() + "退出");
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(new ThreadDemo(1),"t1");
        Thread t2 = new Thread(new ThreadDemo(2),"t2");
        t1.start();
        t2.start();
        Thread.sleep(1500);
        t1.interrupt();

    }
}
