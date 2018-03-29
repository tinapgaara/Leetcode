package facebook.array;

/**
 * Created by yingtan on 2/11/18.
 *
 * 15. 3Sum
 DescriptionHintsSubmissionsDiscussSolution
 Pick One
 Given an array S of n integers, are there elements a, b, c in S such that a + b + c = 0? Find all unique triplets in the array which gives the sum of zero.

 Note: The solution set must not contain duplicate triplets.

 For example, given array S = [-1, 0, 1, 2, -1, -4],

 A solution set is:
 [
 [-1, 0, 1],
 [-1, -1, 2]
 ]

 Key: remove duplicates
 */
import java.util.*;
public class ThreeSum {
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums == null || nums.length == 0) return res;
        Arrays.sort(nums);
        for (int i = 0 ; i < nums.length - 2; i ++) {
            if (i > 0 && nums[i] == nums[i -1]) continue;
            int first = nums[i];
            int start = i + 1;
            int end = nums.length - 1;
            int target = 0 - first;
            while(start < end) {
                int sum = nums[start] + nums[end];
                if (sum == target) {
                    List<Integer> list = new ArrayList<>();
                    list.add(first);
                    list.add(nums[start]);
                    list.add(nums[end]);
                    res.add(list);
                    start ++;
                    while(start < end && nums[start - 1] == nums[start]) {
                        start ++;
                    }
                    end --;
                    while(end >= i && nums[end + 1] == nums[end]) {
                        end --;
                    }
                }
                else if (sum < target) {
                    start ++;
                    while(start < end && nums[start - 1] == nums[start]) {
                        start ++;
                    }
                }
                else {
                    end --;
                    while(end >= i && nums[end + 1] == nums[end]) {
                        end --;
                    }
                }
            }
        }
        return res;
    }

}
