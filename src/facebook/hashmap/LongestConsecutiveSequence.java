package facebook.hashmap;

/**
 * Created by yingtan on 12/22/17.
 *
 * 128. Longest Consecutive Sequence
 DescriptionHintsSubmissionsDiscussSolution
 DiscussPick One
 Given an unsorted array of integers, find the length of the longest consecutive elements sequence.

 For example,
 Given [100, 4, 200, 1, 3, 2],
 The longest consecutive elements sequence is [1, 2, 3, 4]. Return its length: 4.

 Your algorithm should run in O(n) complexity.
 */
import java.util.*;
public class LongestConsecutiveSequence {
    public int longestConsecutive(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        HashSet<Integer> set = new HashSet<>();
        for (int num : nums) {
            set.add(num);
        }
        int i = 0;
        int max = 0;
        while(i < nums.length) {
            int leftlen = 0;
            int rightlen = 0;
            int curNum = nums[i];
            while(set.contains(curNum)) {
                set.remove(curNum);
                leftlen ++;
                curNum --;
            }
            curNum = nums[i] + 1; // important!!!
            while(set.contains(curNum)) {
                set.remove(curNum);
                rightlen ++;
                curNum ++;
            }
            max = Math.max(max, leftlen  + rightlen);
            i ++;
        }
        return max;
    }
}

