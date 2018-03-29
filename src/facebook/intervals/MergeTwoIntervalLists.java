package facebook.intervals;
import interval.Interval;

import java.util.*;
public class MergeTwoIntervalLists {
    public List<Interval> mergeTwoIntervalLists(List<Interval> i1, List<Interval> i2) { // union
        if (i1 == null) return i2;
        if (i2 == null) return i1;

        // similar to merge sort, but need to consider overlapped interval
        IntervalComparator comp = new IntervalComparator();
        Collections.sort(i1, comp);
        Collections.sort(i2, comp);
        // make sure i1/i2 does not have overlapped intervals
        i1 = merge(i1);
        i2 = merge(i2);
        // then, do merge sort
        List<Interval> res = new ArrayList<>();
        int i = 0;
        int j = 0;
        while(i < i1.size() && j < i2.size()) {
            Interval interval1 = i1.get(i);
            Interval interval2 = i2.get(j);
            Interval prev = null;
            Interval newInterval = null;
            // each time, only add one interval
            if (res.size() > 0) {
                prev = res.get(res.size() - 1);
                if (overlap(prev, interval1) || overlap(prev, interval2)) {
                    if (overlap(prev, interval1)) {
                        newInterval = mergeTwo(prev, interval1);
                        // keep interval2 to the next round
                        i ++;
                    }
                    else {
                        newInterval = mergeTwo(prev, interval2);
                        // keep interval1 to the next round
                        j ++;
                    }
                    res.remove(res.size() - 1);
                    res.add(newInterval);
                }
            }
            // prev does not overlap with i1 and i2
            if (overlap(interval1, interval2)) {
                newInterval = mergeTwo(interval1, interval2);
                res.add(newInterval);
                i ++;
                j ++;
            }
            else {
                // does not overlap
                if (interval1.end < interval2.start) {
                    res.add(interval1);
                    i ++;
                }
                else {
                    res.add(interval2);
                    j ++;
                }
            }
        }
        while(i < i1.size()) {
            res.add(i1.get(i));
            i ++;
        }
        while(j < i2.size()) {
            res.add(i2.get(j));
            j ++;
        }
        return res;
    }
    public List<Interval> merge(List<Interval> intervals) {
        if (intervals == null || intervals.size() == 0) return intervals;
        List<Interval> res = new ArrayList<>();
        Interval i1 = intervals.get(0);
        for(int i = 1; i < intervals.size(); i ++) {
            Interval i2 = intervals.get(i);
            if (overlap(i1, i2)) {
                i1 = new Interval(Math.min(i1.start, i2.start), Math.max(i1.end, i2.end));
            }
            else {
                res.add(i1);
                i1 = i2;
            }
        }
        res.add(i1);
        return res;
    }
    public Interval mergeTwo(Interval i1, Interval i2) {
        return new Interval(Math.min(i1.start, i2.start),
                Math.max(i1.end, i2.end));
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
    public boolean overlap(Interval i1, Interval i2) {
        return (i1.start <= i2.end && i2.start <= i1.end);
    }
}
