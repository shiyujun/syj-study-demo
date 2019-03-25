package cn.shiyujun.thread.threadlocal;


/**
 * d
 *
 * @author syj
 * CreateTime 2019/03/25
 * describe: InheritableThreadLocal的demo
 */
public class InheritableThreadLocalDemo {
    private static  InheritableThreadLocal<Integer> inheritableThreadLocal = new  InheritableThreadLocal<Integer>();
    static class ThreadDemo implements Runnable {
        @Override
        public void run() {
            for (int i = 0; i < 1000; i++) {
                inheritableThreadLocal.set(inheritableThreadLocal.get() + 1);
            }
            System.out.println("thread :" + Thread.currentThread().getId() + " is" + inheritableThreadLocal.get());
        }
    }
    public static void main(String[] args) {
        inheritableThreadLocal.set(24);
        for (int i = 0; i < 10; i++) {
            new Thread(new ThreadDemo()).start();
        }
    }
}
