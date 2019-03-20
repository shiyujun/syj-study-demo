package cn.shiyujun.thread.hellothread;

/**
 * d
 *
 * @author syj
 * CreateTime 2019/03/19
 * describe:通过继承Thread类的方式创建线程的demo
 */
public class HelloThread1 {
    static class ThreadDemo extends Thread {
        //新建一个类继承 Thread 类，并重写 Thread 类的 run() 方法。
        @Override
        public void run() {
            System.out.println("Hello Thread");
        }
    }
    public static void main(String[] args) {
        // 2. 创建 Thread 子类的实例。
        ThreadDemo threadDemo = new ThreadDemo();
        // 3. 调用该子类实例的 start() 方法启动该线程。
        threadDemo.start();
    }
}
