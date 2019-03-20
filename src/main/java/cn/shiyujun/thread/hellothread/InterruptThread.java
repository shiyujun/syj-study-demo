package cn.shiyujun.thread.hellothread;

/**
 * d
 *
 * @author syj
 * CreateTime 2019/03/19
 * describe:线程中断的测试demo
 */
public class InterruptThread {
    static class ThreadDemo extends Thread {
        @Override
        public void run() {
            while (true) {
                if (Thread.currentThread().isInterrupted()) {
                    System.out.println("程序结束");
                    break;
                }
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    public static void main(String[] args) {
        ThreadDemo threadDemo = new ThreadDemo();
        threadDemo.start();
        threadDemo.interrupt();
    }
}
