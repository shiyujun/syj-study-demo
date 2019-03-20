package cn.shiyujun.thread.lock.sync;

import cn.shiyujun.thread.hellothread.WaitNotifyThread;

/**
 * d
 *
 * @author syj
 * CreateTime 2019/03/21
 * describe: 没有锁的线程安全问题测试demo
 */
public class NotSyncDemo {
    public static int i=0;

    static class ThreadDemo extends Thread {
        @Override
        public void run() {
           for (int j=0;j<10000;j++){
               i++;
           }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ThreadDemo t1=new ThreadDemo();
        ThreadDemo t2=new ThreadDemo();
        t1.start();t2.start();
        t1.join();
        t2.join();
        System.out.println(i);
    }
}
