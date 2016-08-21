package bloomberg.string;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by yingtan on 11/12/15.
 */
/*
* Given an unsorted array of integers, find the length of the longest consecutive elements sequence.

For example,
Given [100, 4, 200, 1, 3, 2],
The longest consecutive elements sequence is [1, 2, 3, 4]. Return its length: 4.

Your algorithm should run in O(n) complexity.
* */
public class LongestConsecutiveSequence {

    public int longestConsecutive(int[] nums) {

        Set<Integer> set = new HashSet<Integer>();
        for (int i = 0 ; i < nums.length ; i ++) {
            set.add(nums[i]);
        }

        int maxLen = 1;
        int curLen = 1;
        while (set.size() > 0) {
            int min = findMin(set);
            int nextElement = min + 1;
            set.remove(min);
            while (set.contains(nextElement)) {
                curLen ++;
                set.remove(nextElement);
                nextElement = nextElement + 1;
            }
            maxLen = Math.max(maxLen, curLen);
            curLen = 1;
            // how to jump to the next small element?
        }
        return maxLen;
    }

    public int findMin(Set<Integer> set) {
        int min = Integer.MAX_VALUE;
        for (Integer n : set) {
            min = Math.min(n, min);
        }
        return min;
    }

}
