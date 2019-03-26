package cn.shiyujun.thread.producerconsumer;

/**
 * @author syj
 * CreateTime 2019/03/27
 * describe: Lock和Condition的生产者消费者demo
 */
public class LockConditionProducerConsumerDemo {
    static LockConditionResouce lockConditionResouce = new LockConditionResouce();

    static class ProducerThreadDemo extends Thread {
        @Override
        public void run() {
            while (true) {
                try {
                    sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                lockConditionResouce.add();

            }
        }
    }

    static class ConsumerThreadDemo extends Thread {
        @Override
        public void run() {
            while (true) {
                try {
                    sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                lockConditionResouce.remove();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread p1 = new Thread(new ProducerThreadDemo(), "生产者p1");
        Thread p2 = new Thread(new ProducerThreadDemo(), "生产者p2");
        Thread p3 = new Thread(new ProducerThreadDemo(), "生产者p3");
        p1.start();
        p2.start();
        p3.start();

        Thread c1 = new Thread(new ConsumerThreadDemo(), "消费者c1");
        Thread c2 = new Thread(new ConsumerThreadDemo(), "消费者c2");
        c1.start();
        c2.start();
    }
}
