package linkedin.interval;

import interval.Interval;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by yingtan on 11/28/17.
 */
public class MergeInterval {

    public List<Interval> merge(List<Interval> intervals) {
        List<Interval> res = new ArrayList<Interval>();
        if (intervals == null || intervals.size() == 0) return res;

        IntervalComparator comparator = new IntervalComparator();
        Collections.sort(intervals, comparator);

        Interval prev = intervals.get(0);
        for (int i = 1; i < intervals.size(); i ++) {
            Interval cur = intervals.get(i);
            if(! overlap(prev, cur)) {
                res.add(prev);
                prev = cur;
            }
            else {
                prev = new Interval(Math.min(prev.start, cur.start), Math.max(prev.end, cur.end));
            }
        }
        res.add(prev);
        return res;
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
