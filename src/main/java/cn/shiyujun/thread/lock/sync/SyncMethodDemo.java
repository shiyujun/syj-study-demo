package cn.shiyujun.thread.lock.sync;

/**
 * d
 *
 * @author syj
 * CreateTime 2019/03/21
 * describe: synchronized锁对象测试demo
 */
public class SyncMethodDemo {

    public static int i = 0;

    static class ThreadDemo extends Thread {
        @Override
        public void run() {
            for (int j = 0; j < 10000; j++) {
                 add();
            }
        }
        public synchronized void add(){
            i++;
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ThreadDemo threadDemo=new ThreadDemo();
        Thread t1 = new Thread(threadDemo);
        Thread t2 = new Thread(threadDemo);
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println(i);
    }
}
