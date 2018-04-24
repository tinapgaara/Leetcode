package multithread.taskScheduler.delayqueueSolution;

import java.util.concurrent.DelayQueue;

/**
 * Created by yingtan on 4/15/18.
 */
public class TaskScheduler {
    public static void main(String[] args) {
        DelayQueue<Task> queue = new DelayQueue<>();
        new Thread(new TaskProducer(queue), "Producer thread").start();
        new Thread(new TaskConsumer(queue), "Consumer thread").start();
    }

}
