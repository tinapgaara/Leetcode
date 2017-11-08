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
        List<Interval> res = new ArrayList<Interval>();
        // boundary case
        if (intervals == null) return res;
        else if (intervals.size() == 0) {
            res.add(newInterval);
            return res;
        }

        if (newInterval == null) return intervals;

        Interval i1 = intervals.get(0);
        Interval i2 = newInterval;
        for (int i = 1; i < intervals.size(); i ++) {
            if (overlap(i1, i2)) {
                i1 = merge(i1, i2);
                i2 = intervals.get(i);
            }
            else {
                res.add(i1);
                i1 = intervals.get(i);
            }
        }

        // important !!!
        if ( (i1 != null) && (i2 != null) ) {
            if(overlap(i1, i2)) {
                res.add(merge(i1, i2));
            }
            else {
                res.add(i1);
                res.add(i2);
            }
        }

        // Important !!! sort happens at the end
        IntervalComparator comparator = new IntervalComparator();
        Collections.sort(res, comparator);
        return res;

    }

    public Interval merge(Interval cur, Interval newInterval) {
        return new Interval(Math.min(cur.start, newInterval.start), Math.max(cur.end, newInterval.end));
    }

    public boolean overlap(Interval prev, Interval cur) {
        if ( (prev.start <= cur.end) && (cur.start <= prev.end) )
            return true;
        else
            return false;
    }

    public class IntervalComparator implements Comparator<Interval> {
        @Override
        public int compare(Interval i1, Interval i2) {
            if (i1.start != i2.start) {
                return i1.start - i2.start;
            }
            else if (i1.end != i2.end) {
                return i1.end - i2.end;
            }
            return 0;
        }
    }
}
