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
        @Override
        public void run() {
            System.out.println("Hello Thread");
        }
    }
    public static void main(String[] args) {
        ThreadDemo threadDemo = new ThreadDemo();
        Thread thread = new Thread(threadDemo);
        thread.start();
    }
}
