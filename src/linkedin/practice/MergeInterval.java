package linkedin.practice;

import interval.Interval;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by yingtan on 11/22/17.
 */
public class MergeInterval {

    public List<Interval> merge(List<Interval> intervals) {
        List<Interval> res = new ArrayList<>();
        if ((intervals == null) || (intervals.size() == 0)) return res;
        if (intervals.size() == 1) return intervals;
        IntervalComparator comp = new IntervalComparator();
        Collections.sort(intervals, comp);

        Interval i1 = intervals.get(0);
        int j = 1;
        while(j < intervals.size()) {
            Interval i2 = intervals.get(j);
            if (overlap(i1, i2)) {
                i1 = merge(i1, i2);
            }
            else {
                res.add(i1);
                i1 = i2;
            }
            j ++;
        }
        intervals.add(i1);
        return intervals;
    }

    public class IntervalComparator implements Comparator<Interval> {
        public int compare(Interval i1, Interval i2) {
            if (i1.start != i2.start) {
                return i1.start - i2.start;
            }
            else {
                return i1.end - i2.end;
            }
        }
    }

    public boolean overlap(Interval i1, Interval i2) {
        if (i1.start <= i2.end && i2.start <= i1.end) {
            return true;
        }
        else {
            return false;
        }
    }

    public Interval merge(Interval i1, Interval i2) {
        return new Interval(Math.min(i1.start, i2.start), Math.max(i1.end, i2.end));
    }
}
