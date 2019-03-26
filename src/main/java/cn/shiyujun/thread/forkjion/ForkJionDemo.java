package cn.shiyujun.thread.forkjion;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;
import java.util.concurrent.RecursiveTask;

/**
 * @author syj
 * CreateTime 2019/03/27
 * describe:ForkJion框架使用demo
 */
public class ForkJionDemo extends RecursiveTask<Integer> {
    public static final int threshold = 2;
    private int start;
    private int end;

    public ForkJionDemo(int start, int end) {
        this.start = start;
        this.end = end;
    }

    @Override
    protected Integer compute() {
        int sum = 0;
        boolean canCompute = (end - start) <= threshold;
        if (canCompute) {
            for (int i = start; i <= end; i++) {
                sum += i;
            }
        } else {
            int middle = (start + end) / 2;
            ForkJionDemo leftDemo = new ForkJionDemo(start, middle);
            ForkJionDemo rightDemo = new ForkJionDemo(middle + 1, end);
            leftDemo.fork();
            rightDemo.fork();
            int leftResult = leftDemo.join();
            int rightResult = rightDemo.join();
            sum = leftResult + rightResult;
        }
        return sum;
    }

    public static void main(String[] args) {
        ForkJoinPool forkjoinPool = new ForkJoinPool();
        ForkJionDemo forkJionDemo = new ForkJionDemo(1, 100);
        Future<Integer> result = forkjoinPool.submit(forkJionDemo);
        try {
            System.out.println(result.get());
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
