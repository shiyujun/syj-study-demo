package cn.shiyujun.thread.hellothread;

/**
 * d
 *
 * @author syj
 * CreateTime 2019/03/19
 * describe:线程停止的测试demo
 */
public class StopThread {
    static class ThreadDemo extends Thread {
        volatile boolean stopMe = false;
        public void stopMe(){
            this.stopMe=true;
        }
        @Override
        public void run() {
            while (true) {
                if (stopMe) {
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
        threadDemo.stopMe();
    }
}
