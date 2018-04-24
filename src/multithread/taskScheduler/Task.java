package multithread.taskScheduler;

import java.util.Comparator;

/**
 * Created by yingtan on 4/3/18.
 */
public class Task {
    public int time;
    public TaskThread thread;

    public Task(int time, int id) {
        this.time = time;
        this.thread = new TaskThread(id);

    }
    public class TaskThread implements Runnable {
        public int id;
        public TaskThread(int id) {
            this.id = id;
        }
        public void run() {
            System.out.println("run task at time:" + time);
        }
    }

}
