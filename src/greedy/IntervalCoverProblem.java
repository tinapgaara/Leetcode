package greedy;

import interval.Interval;

import java.util.Arrays;
import java.util.Comparator;

/**
 * Created by yingtan on 1/17/18.
 */
public class IntervalCoverProblem {

    public int minVisitTimes(Interval[] tasks) {
        // sort based on rightmost points
        Arrays.sort(tasks, new Comparator<Interval>() {
            @Override
            public int compare(Interval o1, Interval o2) {
                return o1.end - o2.end;
            }
        });
        int visTime = 0;
        int lastEnd = -1;
        for (Interval task : tasks) {
            // when to increase visTime, not overlap
            // if overlap, means this task is already checked.
            if (task.start > lastEnd) {
                lastEnd = task.end;
                visTime ++;
            }
        }
        return visTime;
    }
}
