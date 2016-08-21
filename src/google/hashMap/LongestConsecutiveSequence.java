package google.hashMap;

import java.util.HashMap;

/**
 * Created by yingtan on 12/22/15.
 */
public class LongestConsecutiveSequence {

    public int longestConsecutive(int[] nums) {
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
}
