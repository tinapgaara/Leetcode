package linkedin.practice;

import interval.Interval;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Created by yingtan on 11/22/17.
 */
public class InsertInterval {

    public List<Interval> insert(List<Interval> intervals, Interval newInterval) {
        List<Interval> res = new ArrayList<Interval>();
        // boundary case
        if (intervals == null) return res;

        Interval i1 = null;
        Interval i2 = newInterval;
        int i = 0;
        while(i < intervals.size()) {
            i1 = intervals.get(i);
            if (overlap(i1, i2)) {
                i2 = merge(i1, i2);
            }
            else {
                res.add(i2);
                i2 = i1;
            }
            i ++;
        }
        // TODO: better way than sorting at the end ??????
        if (i1 != null && i2 != null) {
            if(overlap(i1, i2)) {
                res.add(merge(i1, i2));
            }
            else {
                res.add(i1);
                res.add(i2);
            }
        }
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
