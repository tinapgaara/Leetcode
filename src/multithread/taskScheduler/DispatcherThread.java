package multithread.taskScheduler;

/**
 * Created by yingtan on 4/3/18.
 */
import java.util.*;
public class DispatcherThread implements Runnable {

    PriorityQueue<Task> queue;
    int time;

    public DispatcherThread(PriorityQueue<Task> queue) {
        this.queue = queue;
        this.time = 0;
    }

    public void run() {
        while(true) {
            synchronized (queue) {
                while(queue.isEmpty()) {
                    try {
                        queue.wait();
                    } catch (Exception e) {

                    }
                }
                checkTimeAndSleep();
            }
        }
    }

    private synchronized void checkTimeAndSleep() {
        int toptime = queue.peek().time;
        if (toptime == time) {
            // execute the task
            queue.poll().thread.run();
            time ++;
        }
        if (! queue.isEmpty()) {
            int nexttime = queue.peek().time;
            try {
                Thread.sleep(nexttime - time);
            } catch (Exception e) {

            }
        }
        else {
            try {
                queue.wait();
            }
            catch (Exception e) {

            }
        }
    }


    public static void main(String[] args) {
        Task s1 = new Task(0, 1);
        Task s2 = new Task(1, 2);
        TaskComparator comp = new TaskComparator();
        PriorityQueue<Task> queue = new PriorityQueue<>(comp);
        Thread thread = new Thread(new DispatcherThread(queue));
        thread.start();
        TaskGenerator generator = new TaskGenerator(queue);
        generator.generate(s1);
        generator.generate(s2);
    }

}
