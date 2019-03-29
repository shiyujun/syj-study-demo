package cn.shiyujun.thread.util;

import java.util.concurrent.Semaphore;

/**
 * d
 *
 * @author syj
 * CreateTime 2019/03/29
 * describe: 信号量测试demo
 */
public class SemaphoreThreadDemo {
    public static Semaphore semaphore = new Semaphore(5);

    static class ThreadDemo extends Thread {
        @Override
        public void run() {
            try {
                semaphore.acquire();
                System.out.println(Thread.currentThread().getId() + "号线程在"+System.currentTimeMillis()+"获取资源");
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                semaphore.release();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 30; i++) {
            new ThreadDemo().start();
        }
    }

}
