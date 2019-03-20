package cn.shiyujun.thread.hellothread;

/**
 * d
 *
 * @author syj
 * CreateTime 2019/03/19
 * describe:通过实现Runnable接口的方式创建线程的demo
 */
public class HelloThread2 {
    static class ThreadDemo implements Runnable {
        // 1. 创建一个类实现 Runnable 接口，并重写该接口的 run() 方法
        @Override
        public void run() {
            System.out.println("Hello Thread");
        }
    }
    public static void main(String[] args) {
        // 2. 创建 Thread 子类的实例。
        ThreadDemo threadDemo = new ThreadDemo();
        // 3. 将该实例传入 Thread(Runnable r) 构造方法中创建 Thread 实例。
        Thread thread = new Thread(threadDemo);
        // 4. 调用该 Thread 线程对象的 start() 方法。
        thread.start();
    }
}
