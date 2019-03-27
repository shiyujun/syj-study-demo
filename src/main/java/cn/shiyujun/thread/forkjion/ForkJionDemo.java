package cn.shiyujun.thread.forkjion;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;
import java.util.concurrent.RecursiveTask;

/**
 * @author syj
 * CreateTime 2019/03/27
 * describe:ForkJion框架使用demo
 */
public class ForkJionDemo extends RecursiveTask<Integer> {
    //任务拆分阈值
    public static final int threshold = 100;
    //计算起始值
    private int start;
    //计算结束值
    private int end;

    public ForkJionDemo(int start, int end) {
        this.start = start;
        this.end = end;
    }

    @Override
    protected Integer compute() {
        int sum = 0;
        //计算任务是否需要拆分
        boolean canCompute = (end - start) <= threshold;
        if (canCompute) {
            //无需拆分则执行任务累计
            for (int i = start; i <= end; i++) {
                sum += i;
            }
        } else {
            List<ForkJionDemo> forkJionDemoList=new ArrayList<>();
            //将任务拆分成100份
            for(int i=0;i<100;i++){
                //计算这个一份任务要计算的起点和终点，如果终点大于最大值则使用最大值为终点
                int last=(start+threshold)>end?end:(start+threshold);
                ForkJionDemo forkJionDemo = new ForkJionDemo(start, last);
                //下一份任务的起点要更新
                start+=threshold+1;
                forkJionDemoList.add(forkJionDemo);
                //提交子任务
                forkJionDemo.fork();
            }
            //汇总拆分完毕后的任务结果
            for(ForkJionDemo forkJionDemo:forkJionDemoList){
                sum  += forkJionDemo.join();
            }
        }
        return sum;
    }

    public static void main(String[] args) {
        //构造一个任务线程​池
        ForkJoinPool forkjoinPool = new ForkJoinPool();
        //创建一个计算1到10000之间所有数的和的任务
        ForkJionDemo forkJionDemo = new ForkJionDemo(1, 10000);
        //将任务提交到任务线程池
        Future<Integer> result = forkjoinPool.submit(forkJionDemo);
        try {
            //打印最后计算结果
            System.out.println(result.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
