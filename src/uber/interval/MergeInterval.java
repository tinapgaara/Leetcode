package uber.interval;
import java.util.*;
/**
 * Created by yingtan on 11/9/17.
 */
public class MergeInterval {

    public class Interval {
        int start;
        int end;

        public Interval(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }
    public List<Interval> merge(List<Interval> intervals) {
        List<Interval> res = new ArrayList<Interval>();
        IntervalComparator comp = new IntervalComparator();
        Collections.sort(intervals, comp);
        Interval prev = intervals.get(0);
        for (int i = 1; i < intervals.size(); i ++) {
            Interval cur = intervals.get(i);
            if (overlap(prev, cur)) {
                prev = merge(prev, cur);
            }
            else {
                res.add(prev);
                prev = cur;
            }
        }
        if (prev != null) {
            res.add(prev);
        }
        return res;
    }

    public Interval merge(Interval i1, Interval i2) {
        return new Interval(Math.min(i1.start, i2.start), Math.min(i1.end, i2.end));
    }

    public boolean overlap(Interval i1, Interval i2) {
        if (i1.start <= i2.end && i2.start <= i1.end) return true;
        else return false;
    }

    public class IntervalComparator implements Comparator<Interval> {
        @Override
        public int compare(Interval i1, Interval i2) {
            if (i1.start != i2.start) {
                return i1.start - i2.start;
            }
            else {
                return i1.end - i2.end;
            }
        }
    }
}
