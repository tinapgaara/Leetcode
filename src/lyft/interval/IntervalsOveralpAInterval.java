package lyft.interval;

import interval.Interval;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

/**
 * Created by yingtan on 4/11/18.
 */
public class IntervalsOveralpAInterval {
    // 1. Given a list of intervals, a Interval, find on intervals that overlaps with the given interval
    public int countIntervalsOverlapNum(Interval[] intervals, Interval candidate) {
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
        // find out start <= [start]
        Map<Integer, Set<Integer>> leftOverlapMap = startToEnds.headMap(candidate.start, true);
        for (Integer key : leftOverlapMap.keySet()) {
            for (Integer end : leftOverlapMap.get(key)) {
                if (end >= candidate.start) { // make sure its end >= num.
                    count ++;
                }
            }
        }
        // find out start >= start[]
        Map<Integer, Set<Integer>> rightOverlapMap = startToEnds.tailMap(candidate.start, true);
        for (Integer key : rightOverlapMap.keySet()) {
            if (key <= candidate.end) { // make sure its end >= num.
                count ++;
            }
        }
        return count;
    }
}
