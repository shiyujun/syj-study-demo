package cn.shiyujun.thread.threadlocal;


/**
 * d
 *
 * @author syj
 * CreateTime 2019/03/25
 * describe:ThreadLocal的demo
 */
public class ThreadLocalDemo {
    private static ThreadLocal<Integer> threadLocal = new ThreadLocal<Integer>() {
        @Override
        public Integer initialValue() {
            return 0;
        }
    };
    static class ThreadDemo implements Runnable {
        @Override
        public void run() {
            for (int i = 0; i < 1000; i++) {
                threadLocal.set(threadLocal.get() + 1);
            }
            System.out.println("thread :" + Thread.currentThread().getId() + " is" + threadLocal.get());
        }
    }
    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            new Thread(new ThreadDemo()).start();
        }
    }
}
