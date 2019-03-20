package cn.shiyujun.thread.hellothread;

/**
 * d
 *
 * @author syj
 * CreateTime 2019/03/19
 * describe:线程组测试demo
 */
public class ThreadGroupDemo {


    static class ThreadDemo extends Thread {
        @Override
        public void run() {
            while (true){
                System.out.println("I am "+Thread.currentThread().getThreadGroup().getName()+"-"+Thread.currentThread().getName());
                try {
                    sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }



    public static void main(String[] args) {
        ThreadGroup threadGroup=new ThreadGroup("groupDemo");
        Thread t1=new Thread(threadGroup,new ThreadDemo(),"t1");
        Thread t2=new Thread(threadGroup,new ThreadDemo(),"t2");
        t1.start();
        t2.start();
    }
}
