package cn.shiyujun.thread.util;

import java.util.concurrent.CountDownLatch;

/**
 * d
 *
 * @author syj
 * CreateTime 2019/03/21
 * describe: CountDownLatch测试demo
 */
public class CountDownLatchDemo {

    public static CountDownLatch countDownLatch = new CountDownLatch(5);

    static class ThreadDemo extends Thread {
        @Override
        public void run() {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getId() + "完成任务");
            countDownLatch.countDown();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 5; i++) {
            new ThreadDemo().start();
        }
        countDownLatch.await();
        System.out.println("全部完成任务");
    }
}
