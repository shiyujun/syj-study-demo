package cn.shiyujun.thread.threadlocal;


import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * d
 *
 * @author syj
 * CreateTime 2019/03/25
 * describe:ThreadLocalPool的demo
 */
public class ThreadLocalPoolDemo {
    private static ThreadLocal<Integer> threadLocal = new ThreadLocal<Integer>() {
        @Override
        public Integer initialValue() {
            return 0;
        }
    };
    static class ThreadDemo implements Runnable {
        @Override
        public void run() {
            for (int i = 0; i < 1000; i++) {
                threadLocal.set(threadLocal.get() + 1);
            }
            System.out.println("thread :" + Thread.currentThread().getId() + " is" + threadLocal.get());
            //threadLocal.remove();
        }
    }
    public static void main(String[] args) {
        ExecutorService executorService= Executors.newFixedThreadPool(5);
        for (int i = 0; i < 10; i++) {
            executorService.submit(new Thread(new ThreadDemo()));
        }
    }
}
