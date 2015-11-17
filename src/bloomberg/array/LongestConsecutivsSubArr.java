package bloomberg.array;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by yingtan on 11/14/15.
 */
public class LongestConsecutivsSubArr {

    public int longestConsecutiveSubArr(int[] arr) {
        Set<Integer> set = new HashSet<>();
        for (int i = 0 ; i < arr.length ; i ++) {
            set.add(arr[i]);
        }
        int maxLen = 0;
        int curLen = 0;
        while (!set.isEmpty()) {
            int min = findMin(set);
            curLen ++;
            int next = min + 1;
            while (set.contains(next)) {
                set.remove(next);
                curLen ++;
                next = next + 1;
            }
            maxLen = Math.max(maxLen, curLen);
            curLen = 0;
        }
        return maxLen;
    }

    public int findMin(Set<Integer> set) {

        int min = Integer.MAX_VALUE;
        for (Integer element : set) {
            min = Math.min(min, element);
        }
        set.remove(min);
        return min;
    }

    public int longestConsecutive_2(int[] nums) {

        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        for (int i : nums) map.put(i, 0);

        int res = Integer.MIN_VALUE;
        for (int i = 0; i < nums.length; i++) {
            int count = 1, left = -1, right = 1;
            int num = nums[i];
            if (map.get(num) == 1) // Visited
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

    public static void main(String[] args) {
        LongestConsecutivsSubArr ob = new LongestConsecutivsSubArr();
        int[] arr = new int[]{5,7,6,26,27,28,29,30};
        System.out.println(ob.longestConsecutiveSubArr(arr));
    }
}
