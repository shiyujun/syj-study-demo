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
        // 1. 创建一个类实现 Runnable 接口，并重写该接口的 run() 方法
        @Override
        public String call() {
            System.out.println("Hello Thread");
            return "Callable return value";
        }
    }

    public static void main(String[] args) {
        // 2. 创建该 Callable 接口实现类的实例。。
        ThreadDemo threadDemo = new ThreadDemo();
        // 3. 将 Callable 的实现类实例传入 FutureTask(Callable<V> callable) 构造方法中创建 FutureTask 实例。
        FutureTask<String> futureTask = new FutureTask<String>(threadDemo);
        // 4. 将 FutureTask 实例传入 Thread(Runnable r) 构造方法中创建 Thread 实例。
        Thread thread = new Thread(futureTask);
        // 5. 调用该 Thread 线程对象的 start() 方法。
        thread.start();
        // 6. 调用 FutureTask 实例对象的 get() 方法获取返回值。
        try {
            System.out.println(futureTask.get());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
