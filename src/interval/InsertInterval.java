package interval;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by yingtan on 10/5/15.
 */
public class InsertInterval {

    public List<Interval> insert(List<Interval> intervals, Interval newInterval) {
        List<Interval> res = new ArrayList<>();
        if  (intervals == null) return res;
        else if (intervals.size() == 0) {
            res.add(newInterval);
            return res;
        }
        if (newInterval == null) return intervals;

        int i = 0;
        Interval interval1 = intervals.get(0);
        Interval interval2 = newInterval;
        i ++;
        while (i < intervals.size()) {
            if (isOverLap(interval1, interval2)) {
                interval1 = mergeInterval(interval1, interval2);
                interval2 = intervals.get(i);
            }
            else {
                res.add(interval1);
                interval1 = intervals.get(i);
            }
            i ++;
        }
        if ( (interval1 != null) && (interval2 != null) ) {
            if (isOverLap(interval1, interval2)) {
                res.add(mergeInterval(interval1, interval2));
            }
            else {
                res.add(interval1);
                res.add(interval2);
            }
        }

        IntervalComparator comparator = new IntervalComparator();
        Collections.sort(res, comparator);
        return res;
    }

    public boolean isOverLap(Interval i1, Interval i2) {
        int low1 = i1.start;
        int high1 = i1.end;
        int low2 = i2.start;
        int high2 = i2.end;
        if ( (low2 <= high1) && (high2 >= low1) ) {
            return true;
        }
        else return false;
    }

    public Interval mergeInterval(Interval i1, Interval i2) {
        return new Interval(Math.min(i1.start, i2.start), Math.max(i1.end, i2.end));
    }

    public class IntervalComparator implements Comparator<Interval> {
        @Override
        public int compare(Interval i1, Interval i2) { // default: (i2 , i1)  i2 is first, return 1
            if (i1.start > i2.start) return 1; // IMPORTANT !!!
            else if (i1.start < i2.start) return -1;
            else return 0;
        }
    }
}
