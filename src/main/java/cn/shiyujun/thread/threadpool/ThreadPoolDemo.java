package cn.shiyujun.thread.threadpool;

import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeUnit;

/**
 * d
 *
 * @author syj
 * CreateTime 2019/03/23
 * describe: 线程池扩展demo
 */
public class ThreadPoolDemo {

    static class ThreadDemo extends Thread {
        @Override
        public void run() {
            System.out.println(System.currentTimeMillis() + ":Thread ID is:" + Thread.currentThread().getId());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ThreadPoolExecutorDemo threadPoolExecutorDemo=  new ThreadPoolExecutorDemo(5,5,0,TimeUnit.SECONDS,new LinkedBlockingDeque<Runnable>());
        ThreadDemo threadDemo = new ThreadDemo();
        for (int i = 0; i < 20; i++) {
            threadPoolExecutorDemo.submit(threadDemo);
        }
        threadPoolExecutorDemo.shutdown();
    }
}
