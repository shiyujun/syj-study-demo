package cn.shiyujun.thread.lock.reentrant;

import java.util.concurrent.locks.ReentrantLock;

/**
 *
 *
 * @author syj
 * CreateTime 2019/03/21
 * describe: 重入锁测试demo
 */
public class ReentrantLockDemo {
    public static ReentrantLock lock = new ReentrantLock();
    public static int i = 0;

    static class ThreadDemo extends Thread {
        @Override
        public void run() {
            for (int j = 0; j < 10000; j++) {
                lock.lock();
                 try {
                     i++;
                 }finally {
                     lock.unlock();
                 }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ThreadDemo t1 = new ThreadDemo();
        ThreadDemo t2 = new ThreadDemo();
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println(i);
    }
}
