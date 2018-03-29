package uber.interval;

import java.util.*;

/**
 * Created by erict on 2017/11/9.
 *
 * 给一堆interval，比如{3,6},{2,4}，然后给一个数，返回哪些interval包含了这个数，比如5就返回{3,6},{2,4}，2就只返回{2,4}

 */
public class IntervalHit2 {

    public Collection<Interval> getAllHitIntervals(int num, List<Interval> intervals) {
        TreeMap<Integer, List<Interval>> leftMap = buildTreeMap(intervals, true);
        TreeMap<Integer, List<Interval>> rightMap = buildTreeMap(intervals, false);
        Map<Integer, List<Interval>> leftSub = leftMap.headMap(num + 1);
        Map<Integer, List<Interval>> rightSub = rightMap.tailMap(num);

        Collection<Interval> result = new HashSet<>();
        Collection<List<Interval>> leftListSet = leftSub.values();
        for (List<Interval> list: leftListSet) result.addAll(list);

        Collection<List<Interval>> rightListSet = rightSub.values();
        Collection<Interval> rightSet = new HashSet<>();
        for (List<Interval> list: rightListSet) rightSet.addAll(list);

        result.retainAll(rightSet);
        return result;
    }

    private TreeMap<Integer, List<Interval>> buildTreeMap(List<Interval> intervals, boolean useLeft) {
        TreeMap<Integer, List<Interval>> result = new TreeMap<>();
        for (Interval interval : intervals) {
            int key;
            if (useLeft)
                key = interval.m_left;
            else
                key = interval.m_right;
            List<Interval> value = result.get(key);
            if (value == null) {
                value = new ArrayList<>();
                result.put(key, value);
            }
            value.add(interval);
        }
        return result;
    }

    public class Interval {
        public int m_left, m_right;
    }

}
