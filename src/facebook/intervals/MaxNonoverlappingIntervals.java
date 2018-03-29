package facebook.intervals;

import interval.Interval;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

/**
 * Created by yingtan on 3/18/18.
 *
 * 435. Non-overlapping Intervals
 DescriptionHintsSubmissionsDiscussSolution
 Pick One
 Given a collection of intervals, find the minimum number of intervals you need to remove to make the rest of the intervals non-overlapping.

 Note:
 You may assume the interval's end point is always bigger than its start point.
 Intervals like [1,2] and [2,3] have borders "touching" but they don't overlap each other.
 Example 1:
 Input: [ [1,2], [2,3], [3,4], [1,3] ]

 Output: 1

 Explanation: [1,3] can be removed and the rest of intervals are non-overlapping.
 Example 2:
 Input: [ [1,2], [1,2], [1,2] ]

 Output: 2

 Explanation: You need to remove two [1,2] to make the rest of intervals non-overlapping.
 Example 3:
 Input: [ [1,2], [2,3] ]

 Output: 0

 Explanation: You don't need to remove any of the intervals since they're already non-overlapping.
 */
public class MaxNonoverlappingIntervals {
    // same as: find maximum numbers of intervals that non-overlap
    // because we sort intervals by start time, so to make sure not overlap, just make sure i.start > latest.end
    public int eraseOverlapIntervals(Interval[] intervals) {
        if (intervals == null || intervals.length == 0) {
            return 0;
        }
        // starting from eariest end time, and calculate non-overlap intervals.

        IntervalComparator comp = new IntervalComparator();
        Arrays.sort(intervals, comp);
        int end = intervals[0].end;
        int nonoverlap = 1;
        for (int i = 1; i < intervals.length; i ++) {
            Interval iter = intervals[i];
            if (iter.start >=end) {
                // not overlap
                // when the current interval not overlaps with previous end time, since we loop intervals based on end time(increasing), this end time is still small value, so it is safe to change end=iter.end
                end = iter.end;
                nonoverlap ++;
            }
            else {
                // overlap, so keep using the earest end time.
                // because we want to maximum non-overlap counts. so minimize end time
            }
        }
        return intervals.length - nonoverlap;
    }
    public class IntervalComparator implements Comparator<Interval> {
        public int compare(Interval i1, Interval i2) {
            return i1.end - i2.end;
        }
    }
}
