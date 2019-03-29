package cn.shiyujun.thread.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * d
 *
 * @author syj
 * CreateTime 2019/03/21
 * describe: 读写锁测试demo
 */
public class ReadWriteLockDemo {
    public static ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    public static Lock readLock = readWriteLock.readLock();
    public static Lock writeLock = readWriteLock.writeLock();

    public static void read(Lock lock) {
        lock.lock();
        try {
            System.out.println("readTime：" + System.currentTimeMillis());
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public static void write(Lock lock) {
        lock.lock();
        try {
            System.err.println("writeTime：" + System.currentTimeMillis());
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    static class ReadThread extends Thread {
        @Override
        public void run() {
            read(readLock);
        }
    }

    static class WriteThread extends Thread {
        @Override
        public void run() {
            write(writeLock);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 10; i++) {
            new ReadThread().start();
        }
        new WriteThread().start();
        new WriteThread().start();
        new WriteThread().start();
    }
}
