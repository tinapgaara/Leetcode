package facebook.intervals;

import interval.Interval;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * Created by yingtan on 3/21/18.
 */
public class MaxOveralpIntervalsCount {

    // cal maxoverlaps, sorted intervals by start time.
    // for each overlap, calculate the how many intervals before it intersect with it, -> max
    // or, if you want to make sure
    public int maxOverlap(Interval[] intervals) {
        // min meeting room -> maximum overlaps so need at least these rooms
        if (intervals == null || intervals.length == 0) return 0;
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        queue.offer(0);
        IntervalComparator comp = new IntervalComparator();
        Arrays.sort(intervals, comp);
        int max = 0;
        //Solution 2: using maximum overlap ideas. If want to find all intevals in maximum overlap, need to use this.
        for (Interval i : intervals) {
            while (! queue.isEmpty() && i.start >= queue.peek()) {
                queue.poll(); // get out all old events which are finished before this task starts
            }
            queue.offer(i.end);
            // if you keep polling all finished tasks, then the time you add the new event, is may not be the maximum count of overlap,
            // so we need to record max each time when we add event to queue.: slower
            // Another approach: don't pop all finished ones,
            max = Math.max(max, queue.size());
            // each time, queue stores all intervals which overlap with the latest coming interval
        }
        return max;
    }
    public class IntervalComparator implements Comparator<Interval> {
        public int compare(Interval i1, Interval i2) {
            if (i1.start == i2.start) {
                return i1.end - i2.end;
            }
            else {
                return i1.start - i2.start;
            }
        }
    }
}
