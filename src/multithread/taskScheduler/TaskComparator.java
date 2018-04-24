package multithread.taskScheduler;

import java.util.Comparator;

/**
 * Created by yingtan on 4/4/18.
 */
public class TaskComparator implements Comparator<Task> {
    public int compare(Task t1, Task t2) {
        return t1.time - t2.time;
    }
}
