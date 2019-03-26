package cn.shiyujun.thread.producerconsumer;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author syj
 * CreateTime 2019/03/27
 * describe: 使用Lock和Condition的资源示例demo
 */
public class LockConditionResouce {
    private int i = 0;
    private int size = 10;
    private Lock lock = new ReentrantLock();
    Condition condition = lock.newCondition();

    public void add() {
        lock.lock();
        try {
            if (i < size) {
                i++;
                System.out.println(Thread.currentThread().getName() + "号线程生产一件资源,当前资源" + i + "个");
                condition.signalAll();
            } else {
                try {
                    condition.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        } finally {
            lock.unlock();
        }

    }

    public void remove() {
        lock.lock();
        try {
            if (i > 0) {
                i--;
                System.out.println(Thread.currentThread().getName() + "号线程拿走了一件资源,当前资源" + i + "个");
                condition.signalAll();
            } else {
                try {
                    condition.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        } finally {
            lock.unlock();
        }

    }
}
