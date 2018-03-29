package facebook.intervals;

import interval.Interval;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by yingtan on 5/21/17.
 *
 * 57. Insert Interval Add to List
 DescriptionHintsSubmissionsSolutions
 Total Accepted: 91225
 Total Submissions: 336708
 Difficulty: Hard
 Contributor: LeetCode
 Given a set of non-overlapping intervals, insert a new interval into the intervals (merge if necessary).

 You may assume that the intervals were initially sorted according to their start times.

 Example 1:
 Given intervals [1,3],[6,9], insert and merge [2,5] in as [1,5],[6,9].

 Example 2:
 Given [1,2],[3,5],[6,7],[8,10],[12,16], insert and merge [4,9] in as [1,2],[3,10],[12,16].

 This is because the new interval [4,9] overlaps with [3,5],[6,7],[8,10].
 */
public class InsertInterval {

    public List<Interval> insert(List<Interval> intervals, Interval newInterval) {
        List<Interval> res = new ArrayList<>();
        if (intervals == null ) {
            return res;
        }
        IntervalComparator comp = new IntervalComparator();
        Collections.sort(intervals, comp);
        for (Interval interval : intervals) {
            if (overlap(newInterval, interval)) {
                newInterval = merge(newInterval, interval);
            }
            else {
                // not overlap. [newInterval, Interval]
                if (newInterval.end < interval.start) {
                    res.add(newInterval);
                    newInterval = interval;
                }
                // not overlap. [Interval, newInterval]
                else if (interval.end < newInterval.start) {
                    res.add(interval);
                }
            }
        }
        res.add(newInterval);
        return res;
    }
    public boolean overlap(Interval i1, Interval i2) {
        return (i1.start <= i2.end && i2.start <= i1.end);
    }
    public Interval merge(Interval i1, Interval i2) {
        return new Interval(Math.min(i1.start, i2.start), Math.max(i1.end, i2.end));
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
