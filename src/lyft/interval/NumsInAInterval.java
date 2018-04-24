package lyft.interval;

/**
 * Created by yingtan on 4/11/18.
 */
import interval.Interval;

import java.util.*;
public class NumsInAInterval {
    // Given a interval, a list of nums, find all nums in the interval
    public int countNumsInInterval(int[] nums, Interval interval) {
        TreeMap<Integer, Integer> numToCount = new TreeMap<>();
        for (int num : nums) {
            if (numToCount.containsKey(num)) {
                numToCount.put(num, numToCount.get(num) + 1);
            }
            else {
                numToCount.put(num, 1);
            }
        }
        Map<Integer, Integer> submap = numToCount.subMap(interval.start, true, interval.end, true);
        int count = 0;
        for (Integer key : submap.keySet()) {
            count = count + submap.get(key);
        }
        return count;
    }
    // Solution 2:  using nums: build BST tree
    // [start, end] = totalcount - smallerCount(start) - largerCount(end)
}
