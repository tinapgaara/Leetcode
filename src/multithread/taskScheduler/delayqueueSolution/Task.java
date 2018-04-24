package multithread.taskScheduler.delayqueueSolution;

import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * Created by yingtan on 4/15/18.
 */
public class Task implements Delayed {
    private String name;
    private long startTime;
    public Task(String name, long delay) {
        this.name = name;
        this.startTime = System.currentTimeMillis() + delay;
    }
    @Override
    public long getDelay(TimeUnit unit) {
        long diff = System.currentTimeMillis() - startTime;
        return unit.convert(diff, TimeUnit.MILLISECONDS);
    }
    @Override
    public int compareTo(Delayed o) {
        return (int)(this.startTime - ((Task)o).startTime);
    }
    public String toString() {
        return "Task: " + name;
    }
}
