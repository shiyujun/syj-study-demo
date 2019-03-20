package cn.shiyujun.thread.lock.reentrant;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * d
 *
 * @author syj
 * CreateTime 2019/03/21
 * describe: 重入锁申请限时demo
 */
public class ReentrantLockTimeDemo {
    public static ReentrantLock lock = new ReentrantLock();
    static class ThreadDemo extends Thread {
        @Override
        public void run() {
            try {
                if (lock.tryLock(2, TimeUnit.SECONDS)) {
                    try {
                        System.out.println(Thread.currentThread().getName() + "获取锁成功");
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } else {
                    System.out.println(Thread.currentThread().getName() + "获取锁失败");
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                if (lock.isHeldByCurrentThread()) {
                    lock.unlock();
                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(new ThreadDemo(), "t1");
        Thread t2 = new Thread(new ThreadDemo(), "t2");
        t1.start();
        t2.start();


    }
}
