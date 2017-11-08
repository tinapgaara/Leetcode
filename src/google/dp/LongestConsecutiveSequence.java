package google.dp;

import java.util.HashMap;

/**
 * Created by yingtan on 8/8/17.
 *
 Add to List
 128. Longest Consecutive Sequence
 DescriptionHintsSubmissionsDiscussSolution
 Discuss Pick One
 Given an unsorted array of integers, find the length of the longest consecutive elements sequence.

 For example,
 Given [100, 4, 200, 1, 3, 2],
 The longest consecutive elements sequence is [1, 2, 3, 4]. Return its length: 4.

 Your algorithm should run in O(n) complexity.
 */
public class LongestConsecutiveSequence {

    public int longestConsecutive(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        // use hashtable
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        for (int i : nums) map.put(i, 0);
        int res = 0;
        for (int i = 0 ; i < nums.length; i ++) {
            if (map.get(nums[i]) == 1) {
                // visited;
                continue;
            }
            map.put(nums[i], 1);

            // expand to left
            int left = -1;
            int len = 1;

            while (map.containsKey(nums[i] + left)) {
                map.put(nums[i] + left, 1);
                len ++;
                left --;
            }
            // expand to right
            int right = 1;
            while (map.containsKey(nums[i] + right)) {
                map.put(nums[i] + right, 1);
                len ++;
                right ++;
            }
            res = Math.max(res, len);

        }
        return res;
    }
}
