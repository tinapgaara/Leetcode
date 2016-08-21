package google.dp;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by max2 on 11/2/15
 * */
public class LongestConsecutiveSeq {

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
