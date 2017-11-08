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
