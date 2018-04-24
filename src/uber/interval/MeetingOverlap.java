package uber.interval;

import interval.Interval;

import java.util.List;
/*
*
* Input: appointments[] = { {1, 5} {3, 7}, {2, 6}, {10, 15}, {5, 6}, {4, 100}}
Output: Following are conflicting intervals
[3,7] Conflicts with [1,5]
[2,6] Conflicts with [1,5]
[5,6] Conflicts with [3,7]
[4,100] Conflicts with [1,5]
*
* */
import java.util.*;
public class MeetingOverlap {
    public boolean isOverlap(List<Interval> meetings) {
        // assume they are sorted by start time
        IntervalComparator comp = new IntervalComparator();
        Collections.sort(meetings, comp);
        // because only check if has overlap, don't need to find out all overlapped pairs, we can sort endtime by larger->smaller
        PriorityQueue<Integer> endTime = new PriorityQueue<>(Collections.reverseOrder());
        for (Interval inter : meetings) {
            if (endTime.isEmpty()) {
                endTime.offer(inter.end);
            }
            else {
                int lastEnd = endTime.peek();
                if (inter.start < lastEnd) {
                    return true;
                }
                endTime.offer(inter.end);
            }
        }
        return false;
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
