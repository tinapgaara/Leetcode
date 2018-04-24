package lyft.interval;
import interval.Interval;

import java.util.*;
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
                newInterval = merge(interval, newInterval);
            }
            else {
                // which one I should insert firstly ?
                // check start/end time
                if (newInterval.end < interval.start) {
                    res.add(newInterval);
                    newInterval = interval;
                }
                else {
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
