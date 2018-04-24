package multithread.taskScheduler;
import java.util.*;
/**
 * Created by yingtan on 4/3/18.
 */
public class TaskGenerator {
    PriorityQueue<Task> queue;
    public TaskGenerator(PriorityQueue<Task> queue) {
        this.queue = queue;
    }
    public void generate(Task task) {
        synchronized (queue) {
            queue.offer(task);
            System.out.println("Offer task :" + task.thread.id);
            queue.notify();
        }
    }

}
