package cn.shiyujun.thread.hellothread;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

/**
 * d
 *
 * @author syj
 * CreateTime 2019/03/19
 * describe:通过 Callable和FutureTask创建线程的demo
 */
public class HelloThread3 {
    static class ThreadDemo implements Callable<String> {
        @Override
        public String call() {
            System.out.println("Hello Thread");
            return "Callable return value";
        }
    }
    public static void main(String[] args) {
        ThreadDemo threadDemo = new ThreadDemo();
        FutureTask<String> futureTask = new FutureTask<String>(threadDemo);
        Thread thread = new Thread(futureTask);
        thread.start();
        try {
            System.out.println(futureTask.get());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
