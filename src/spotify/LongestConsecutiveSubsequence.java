package spotify;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by yingtan on 11/18/15.
 */
/*
* Given [100, 4, 200, 1, 3, 2],
The longest consecutive elements sequence is [1, 2, 3, 4]. Return its length: 4.
*
* */
public class LongestConsecutiveSubsequence {

    public int longestConsecutive(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i], 1);
        }

        int maxLen = Integer.MIN_VALUE;

        for (int i = 0 ; i < nums.length; i ++) {
            if (map.containsKey(nums[i])) {
                map.remove(nums[i]);

                int curLen = 1;

                int left = nums[i] -1;

                while (map.containsKey(left)) {
                    map.remove(left);
                    curLen ++;
                    left --;
                }

                int right = nums[i] + i;

                while (map.containsKey(right)) {
                    map.remove(right);
                    curLen ++;
                    right ++;
                }
                maxLen = Math.max(maxLen, curLen);
            }
        }
        return maxLen;
    }

    public static void main(String[] args) {
        LongestConsecutiveSubsequence ob = new LongestConsecutiveSubsequence();
        int[] nums = new int[]{100, 4, 200, 1, 3, 2,5};
        System.out.println(ob.longestConsecutive(nums));
    }

}
