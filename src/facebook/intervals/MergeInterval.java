package facebook.intervals;

import interval.Interval;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by yingtan on 5/20/17.
 *
 * 56. Merge Intervals Add to List
 DescriptionHintsSubmissionsSolutions
 Total Accepted: 120965
 Total Submissions: 412070
 Difficulty: Medium
 Contributor: LeetCode
 Given a collection of intervals, merge all overlapping intervals.

 For example,
 Given [1,3],[2,6],[8,10],[15,18],
 return [1,6],[8,10],[15,18].
 */
public class MergeInterval {
    public List<Interval> merge(List<Interval> intervals) {
        List<Interval> res = new ArrayList<>();
        if (intervals == null || intervals.size() == 0) return res;
        IntervalComparator comp = new IntervalComparator();
        Collections.sort(intervals, comp);
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
        res.add(i1);
        return res;
    }
    public boolean overlap(Interval i1, Interval i2) {
        if (i1.start <= i2.end && i2.start <= i1.end) {
            return true;
        }
        else{
            return false;
        }
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
