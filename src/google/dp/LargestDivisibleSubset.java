package google.dp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by yingtan on 9/4/17.
 *
 * 368. Largest Divisible Subset
 DescriptionHintsSubmissionsDiscussSolution
 Discuss Pick One
 Given a set of distinct positive integers, find the largest subset such that every pair (Si, Sj) of elements in this subset satisfies: Si % Sj = 0 or Sj % Si = 0.

 If there are multiple solutions, return any subset is fine.

 Example 1:

 nums: [1,2,3]

 Result: [1,2] (of course, [1,3] will also be ok)
 Example 2:

 nums: [1,2,4,8]

 Result: [1,2,4,8]
 */
public class LargestDivisibleSubset {

    public List<Integer> largestDivisibleSubset(int[] nums) {
        int n = nums.length;
        // count[i]: 表示到数字nums[i]位置最大可整除的子集合的长度(nums[i]是集合中最后一个数)
        int[] dp = new int[n];
        int[] pre = new int[n];
        Arrays.sort(nums);
        int max = 0, index = -1;
        for (int i = 0; i < n; i++) {
            dp[i] = 1;
            pre[i] = -1;
            for (int j = i - 1; j >= 0; j--) {
                if (nums[i] % nums[j] == 0) {
                    if (1 + dp[j] > dp[i]) {
                        dp[i] = dp[j] + 1;
                        pre[i] = j;
                    }
                }
            }
            // find which is largest dp[i] : which element has largest division subset
            if (dp[i] > max) {
                max = dp[i];
                index = i;
            }
        }
        //from nums[maxIndex] to 0, add every element belongs to the largest subset.
        List<Integer> res = new ArrayList<>();
        while (index != -1) {
            res.add(nums[index]);
            index = pre[index];
        }
        return res;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{3,4,16,8};
        LargestDivisibleSubset ob = new LargestDivisibleSubset();
        ob.largestDivisibleSubset(nums);
    }
}

