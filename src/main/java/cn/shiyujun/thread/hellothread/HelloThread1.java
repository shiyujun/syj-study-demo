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
        @Override
        public void run() {
            System.out.println("Hello Thread");
        }
    }
    public static void main(String[] args) {
        ThreadDemo threadDemo = new ThreadDemo();
        threadDemo.start();
    }
}
