package cn.shiyujun.thread.producerconsumer;
/**
 * @author syj
 * CreateTime 2019/03/27
 * describe: 使用wait和notify的资源示例demo
 */
public class WaitNotifyResouce {
    private int i=0;
    private int size=10;

    public synchronized void add(){
        if(i<size){
            i++;
            System.out.println(Thread.currentThread().getName()+"号线程生产一件资源,当前资源"+i+"个");
            notifyAll();
        }else {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    public synchronized void remove(){
        if(i>0){
            i--;
            System.out.println(Thread.currentThread().getName()+"号线程拿走了一件资源,当前资源"+i+"个");
            notifyAll();
        }else {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
