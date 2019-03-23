package cn.shiyujun.thread.threadpool;

import java.util.concurrent.*;
/**
 * d
 *
 * @author syj
 * CreateTime 2019/03/23
 * describe: 扩展ThreadPoolExecutor的demo
 */
public class ThreadPoolExecutorDemo extends ThreadPoolExecutor {

    public ThreadPoolExecutorDemo(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
    }

    public ThreadPoolExecutorDemo(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue, ThreadFactory threadFactory) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, threadFactory);
    }

    public ThreadPoolExecutorDemo(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue, RejectedExecutionHandler handler) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, handler);
    }

    public ThreadPoolExecutorDemo(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue, ThreadFactory threadFactory, RejectedExecutionHandler handler) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, threadFactory, handler);
    }

    @Override
    protected void beforeExecute(Thread t, Runnable r) {
        System.out.println(Thread.currentThread().getId()+"准备执行");
    }

    @Override
    protected void afterExecute(Runnable r, Throwable t) {
        System.out.println(Thread.currentThread().getId()+"执行完成");

    }

    @Override
    protected void terminated() {
        System.out.println("线程池退出");
    }
}
