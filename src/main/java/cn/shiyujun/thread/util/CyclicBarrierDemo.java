package cn.shiyujun.thread.util;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * d
 *
 * @author syj
 * CreateTime 2019/03/21
 * describe: CountDownLatch测试demo
 */
public class CyclicBarrierDemo {

    public static CyclicBarrier cyclicBarrier = new CyclicBarrier(5,new FinallyThreadDemo());

    static class ThreadDemo extends Thread {
        @Override
        public void run() {
            try {
                Thread.sleep(1000);
                System.out.println(Thread.currentThread().getId() + "完成任务");
                cyclicBarrier.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
            System.out.println("到达屏障点每个线程都会瞬时继续执行");
        }
    }

    static class FinallyThreadDemo extends Thread {
        @Override
        public void run() {
            System.out.println("所有任务已经完成之后单独执行的任务！");
        }
    }

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 10; i++) {
            new ThreadDemo().start();
        }
    }

}
