package google.interval;

import interval.Interval;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by yingtan on 11/21/15.
 */
/*
* Given a set of non-overlapping intervals, insert a new interval into the intervals (merge if necessary).

You may assume that the intervals were initially sorted according to their start times.

Example 1:
Given intervals [1,3],[6,9], insert and merge [2,5] in as [1,5],[6,9].

Example 2:
Given [1,2],[3,5],[6,7],[8,10],[12,16], insert and merge [4,9] in as [1,2],[3,10],[12,16].

This is because the new interval [4,9] overlaps with [3,5],[6,7],[8,10].
* */
public class InsertInterval {

    public List<Interval> insert(List<Interval> intervals, Interval newInterval) {
        List<Interval> res = new ArrayList<>();
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
        if ((i1 != null) && (i2 != null)) {
            if (overlap(i1, i2))
                res.add(merge(i1, i2));
            else {
                res.add(i1);
                res.add(i2);
            }
        }

        IntervalComparator comparator = new IntervalComparator();
        Collections.sort(res, comparator);
        return res;
    }

    public boolean overlap(Interval i1, Interval i2) {
        if ((i1.start <= i2.end) && (i1.end >= i2.start))
            return true;
        else
            return false;
    }

    public Interval merge(Interval i1, Interval i2) {
        return new Interval(Math.min(i1.start, i2.start), Math.max(i1.end, i2.end));
    }

    public class IntervalComparator implements Comparator<Interval> {
        @Override
        public int compare(Interval i1, Interval i2) {
            if (i1.start > i2.start) return 1;
            else if (i1.start < i2.start) return -1;
            else return 0;
        }
    }

    public static void main(String[] args) {
        InsertInterval ob = new InsertInterval();
        int[] nums = new int[]{};
    }
}
