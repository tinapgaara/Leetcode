package lyft.interval;

import interval.Interval;

import java.util.Arrays;
import java.util.Comparator;

public class AllowDriverMode {
    public boolean allowDriverMode(Interval[] intervals, int current) {
        IntervalComparator comp = new IntervalComparator();
        Arrays.sort(intervals, comp);
        int sum = 0;
        sum = sum + intervals[0].end - intervals[0].start;
        for (int i = 1 ; i < intervals.length; i ++) {
            Interval curInter = intervals[i];
            if (curInter.start - intervals[i-1].end >= 8) {
                // sleep 8 hours
                sum = 0;
            }
            sum = sum + curInter.end - curInter.start;
        }
        if (current - intervals[intervals.length - 1].end >= 8) {
            return true;
        }
        else {
            if (sum >= 12) return false;
            return true;
        }
    }
    public class IntervalComparator implements Comparator<Interval> {
        public int compare(Interval i1, Interval i2) {
            if (i1.start == i2.start) {
                return i1.end - i2.end;
            } else {
                return i1.start - i2.start;
            }
        }
    }
}
