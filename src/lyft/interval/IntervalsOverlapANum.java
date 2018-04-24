package lyft.interval;

import interval.Interval;

import java.util.*;

/**
 * Created by yingtan on 4/11/18.
 */
public class IntervalsOverlapANum {
    // 1. Given a list of intervals, a num, find on interval that overlaps with the num
    public List<Interval> intervalOverlapNum(Interval[] intervals, int num) {
        // binary search
        return null;
    }
    // 2. Given a list of intervals, a num, find on count of intervals that overlaps with the num
    public int countIntervalsOverlapNum(Interval[] intervals, int num) {
        // treemap : <start, set<end>>
        TreeMap<Integer, Set<Integer>> startToEnds = new TreeMap<>();
        for (Interval interval : intervals) {
            if (! startToEnds.containsKey(interval.start)) {
                Set<Integer> set = new HashSet<>();
                startToEnds.put(interval.start, set);
            }
            startToEnds.get(interval.start).add(interval.end);
        }
        int count = 0;
        // find out start <= num
        Map<Integer, Set<Integer>> submap = startToEnds.headMap(num, true);
        for (Integer key : submap.keySet()) {
            for (Integer end : submap.get(key)) {
                if (end >= num) { // make sure its end >= num.
                    count ++;
                }
            }
        }
        return count;
    }
}
