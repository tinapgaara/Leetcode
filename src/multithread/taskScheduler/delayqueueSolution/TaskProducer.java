package multithread.taskScheduler.delayqueueSolution;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.DelayQueue;

/**
 * Created by yingtan on 4/15/18.
 */
public class TaskProducer implements Runnable{
    private DelayQueue<Task> queue;
    boolean isStop = false;
    Random rnd = new Random();
    public TaskProducer(DelayQueue<Task> queue) {
        this.queue = queue;
    }
    public void run() {
        while(! isStop) {
            try {
                int delay = rnd.nextInt(10000);
                Task task = new Task(UUID.randomUUID().toString(), delay);
                queue.offer(task);
                System.out.println("Put:" + task.toString());
                Thread.sleep(30);
            } catch(Exception e) {
                e.printStackTrace();
            }
        }
    }
    public void setStop() {
        isStop = true;
    }
}
