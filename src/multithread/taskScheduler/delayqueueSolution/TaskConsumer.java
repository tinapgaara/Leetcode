package multithread.taskScheduler.delayqueueSolution;

import java.util.concurrent.DelayQueue;

/**
 * Created by yingtan on 4/15/18.
 */
public class TaskConsumer implements Runnable {
    private DelayQueue<Task> queue;
    boolean isStop = false;
    public TaskConsumer(DelayQueue<Task> queue) {
        this.queue = queue;
    }
    public void run() {
        while(! isStop) {
            try {
                Task t = queue.poll();
                if (t != null) {
                    System.out.println("Take:" + t.toString());
                }
            } catch(Exception e) {
                e.printStackTrace();
            }
        }
    }
    public void setStop() {
        isStop = true;
    }
}
