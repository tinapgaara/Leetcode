package facebook.heap;

import interval.Interval;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * Created by yingtan on 5/16/17.
 */
public class MinMeetingRooms {

    public int minMeetingRooms(Interval[] intervals) {
        if (intervals == null || intervals.length == 0) return 0;
        IntervalComparator comparator = new IntervalComparator();
        Arrays.sort(intervals, comparator);
        Queue<Integer> queue = new PriorityQueue<Integer>();
        queue.offer(0);
        for (int i = 0 ; i < intervals.length; i ++) {
            Interval cur = intervals[i];
            if (cur.start >= queue.peek()) {
                queue.poll();
            }
            queue.offer(cur.end);
        }

        return queue.size();

    }

    public int maxOverlapCount(Interval[] intervals) {
        if (intervals == null) return 0;
        IntervalComparator comp = new IntervalComparator();
        Arrays.sort(intervals, comp);
        // stores all overlapped intervals
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        int max = 0;
        for (Interval interval : intervals) {
            if(queue.isEmpty()) {
                queue.offer(interval.end);
                max = Math.max(max, 1);
                continue;
            }
            else {
                // pop all interval which ends before this interval, those intervals can not overlap with the current interval
                while(! queue.isEmpty() && queue.peek() <= interval.start) {
                    queue.poll();
                }
                queue.offer(interval.end);
                max = Math.max(max, queue.size());
            }
        }
        return max;
    }

    public class IntervalComparator implements Comparator<Interval> {
        public int compare(Interval i1, Interval i2) {
            if (i1.start != i2.start) {
                return (i1.start - i2.start);
            }
            else {
                return (i1.end - i2.end);
            }
        }
    }
}
