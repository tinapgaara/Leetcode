package lyft.interval;
import interval.Interval;

import java.util.*;
/*
*关于“find intersect intervals of two list of intervals”
given two lists of sorted non-overlapping intervals, . visit 1point3acres.com for more.
比如输入 鏉ユ簮涓€浜�.涓夊垎鍦拌鍧�.
list1 = [[3, 8], [100.2, 150]], list2 = [[2, 5], [7, 10]]
输出 鏉ユ簮涓€浜�.涓夊垎鍦拌鍧�.
[[3, 5], [7, 8]]
补充内容 (2018-3-18 09:37):
是overlap， 不是union。

*
* */
public class IntersectOfTwoNonOverlapIntervalLists {
    public List<Interval> overlapTwoIntervalLists(List<Interval> i1, List<Interval> i2) { // union
        if (i1 == null) return i2;
        if (i2 == null) return i1;

        // similar to merge sort, but need to consider overlapped interval
        IntervalComparator comp = new IntervalComparator();
        Collections.sort(i1, comp);
        Collections.sort(i2, comp);
        int i = 0;
        int j = 0;
        List<Interval> res = new ArrayList<>();
        while(i < i1.size() && j < i2.size()) {
            Interval inter1 = i1.get(i);
            Interval inter2 = i2.get(j);
            if (overlap(inter1, inter2)) {
                res.add(overlapTwo(inter1, inter2));
                if (inter1.end < inter2.end) {
                    // inter2 may overlap with future intervals
                    i ++;
                }
                else {
                    // inter1 may overlap with future intervals
                    j ++;
                }
            }
            else if (inter1.end < inter2.start) {
                // inter2 may overlap with future intervals
                i ++;
            }
            else { // then, must be inter2.end < intr1.start
                // inter1 may overlap with future intervals
                j ++;
            }
        }
        return res;
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

    public Interval overlapTwo(Interval i1, Interval i2) {
        return new Interval(Math.max(i1.start, i2.start),
                Math.min(i1.end, i2.end));
    }
    public static void main(String[] args) {
        IntersectOfTwoNonOverlapIntervalLists ob = new IntersectOfTwoNonOverlapIntervalLists();
        Interval i1 = new Interval(2, 5);
        Interval i2 = new Interval(7, 10);
        List<Interval> list1 = new ArrayList<>();
        list1.add(i1);
        list1.add(i2);

        i1 = new Interval(3, 8);
        i2 = new Interval(100, 150);
        List<Interval> list2 = new ArrayList<>();
        list2.add(i1);
        list2.add(i2);

        ob.overlapTwoIntervalLists(list2, list1);
    }
}
