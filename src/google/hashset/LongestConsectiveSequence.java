package google.hashset;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by yingtan on 12/22/15.
 */
/*
* For example,
Given [100, 4, 200, 1, 3, 2],
The longest consecutive elements sequence is [1, 2, 3, 4]. Return its length: 4.
*
* */
public class LongestConsectiveSequence {

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

    public int longestConsecutive_2(int[] nums) {
        Set<Integer> set = new HashSet<>();
        int max = 0;
        for(int num : nums) set.add(num);
        for(int num : nums) {
            if (!set.contains(num-1)) {
                int val = num;
                while(set.remove(val++));
                max = Math.max(max, val-num-1);
            }
        }
        return max;
    }

    public int longestConsecutive_3(int[] nums) {
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        for (int i : nums) map.put(i, 0);

        int res = Integer.MIN_VALUE;
        for (int i= 0; i < nums.length; i++) {
            int count = 1, left = -1, right = 1;
            int num = nums[i];
            if (map.get(num) == 1) //Visited
                continue;

            while (map.containsKey(num + left)) {
                map.put(num + left, 1);
                count++;
                left--;
            }
            while (map.containsKey(num + right)) {
                map.put(num + right, 1);
                count++;
                right++;
            }
            res = Math.max(res, count);
            map.put(num, 1);
        }
        return res;
    }

    public int findMin(Set<Integer> set) {
        int min = Integer.MAX_VALUE;
        for (Integer n : set) {
            min = Math.min(n, min);
        }
        return min;
    }
}
