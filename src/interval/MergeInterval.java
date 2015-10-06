package interval;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by yingtan on 10/5/15.
 */
public class MergeInterval {

    public List<Interval> merge(List<Interval> intervals) {
        List<Interval> res = new ArrayList<>();
        if ( (intervals == null) || (intervals.size() == 0) ) return res;
        if (intervals.size() == 1) return intervals;

        IntervalComparator comparator = new IntervalComparator();
        Collections.sort(intervals, comparator);

        Interval i1 = intervals.get(0);
        int j = 1;
        while (j < intervals.size()) {
            Interval i2 = intervals.get(j);
            if (overLap(i1, i2)) {
                i1 = mergeInterval(i1, i2);
            }
            else {
                res.add(i1);
                i1 = i2;
            }
            j ++;
        }
        if (i1 != null) {
            res.add(i1);
        }
        return res;
    }

    public boolean overLap(Interval i1, Interval i2) {
        if ( (i2.start <= i1.end) && (i2.end >= i1.start) ) return true;
        else return false;
    }

    public Interval mergeInterval(Interval i1, Interval i2) {
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
