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
* Given a collection of intervals, merge all overlapping intervals.

For example,
Given [1,3],[2,6],[8,10],[15,18],
return [1,6],[8,10],[15,18].
* */
public class MergeInterval {

    public List<Interval> merge(List<Interval> intervals) {
        List<Interval> res = new ArrayList<>();
        if ((intervals == null) || (intervals.size() == 0)) return res;
        if (intervals.size() == 1) return intervals;

        IntervalComparator comparator = new IntervalComparator();
        Collections.sort(intervals, comparator);

        Interval i1 = intervals.get(0);
        for (int i = 1; i < intervals.size(); i ++) {
            Interval i2 = intervals.get(i);

            if (overlap(i1, i2)) {
                i1 = merge(i1, i2);
            }
            else {
                res.add(i1);
                i1 = i2;
            }
        }
        if (i1 != null) {
            res.add(i1);
        }
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
        public int compare(Interval i1, Interval i2) { // default : i2, i1
            if (i1.start > i2.start) return 1;
            else if (i1.start < i2.start) return -1;
            else return 0;
        }
    }
}
