package hashtable;

import java.util.HashSet;

/**
 * Created by yingtan on 12/17/17.
 */
public class LongestConsequtiveSubsequence {

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
            curNum = nums[i] + 1;
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

    public static void main(String[] args) {
        LongestConsequtiveSubsequence ob = new LongestConsequtiveSubsequence();
        int[] nums = {100,4,200,1,3,2};
        ob.longestConsecutive(nums);
    }
}
