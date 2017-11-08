package square;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by yingtan on 10/19/17.
 *
 * Given an array S of n integers, are there elements a, b, c in S such that a + b + c = 0? Find all unique triplets in the array which gives the sum of zero.

 Note: The solution set must not contain duplicate triplets.

 For example, given array S = [-1, 0, 1, 2, -1, -4],

 A solution set is:
 [
 [-1, 0, 1],
 [-1, -1, 2]
 ]
 */
public class ThreeSum {

    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        if (nums == null || nums.length == 0) return res;
        Arrays.sort(nums);
        int i = 0;
        while(i < nums.length - 1) {
            int first = nums[i];

            int low = i + 1;
            int high = nums.length - 1;
            while (low < high) {
                if (first + nums[low] + nums[high] == 0) {
                    List<Integer> list = new ArrayList<Integer>();
                    list.add(first);
                    list.add(nums[low]);
                    list.add(nums[high]);

                    res.add(list);

                    while ((low+1 < nums.length) && (nums[low] == nums[low+1]))
                        low ++;
                    low ++;
                    while ((high- 1 >= 0) && (nums[high] == nums[high-1]))
                        high --;
                    high --;
                }
                else if (first + nums[low] + nums[high] < 0) {
                    while ((low+1 < nums.length) && (nums[low] == nums[low+1]))
                        low ++;
                    low ++;
                }
                else {
                    while ((high- 1 >= 0) && (nums[high] == nums[high-1]))
                        high --;
                    high --;
                }
            }
            while ((i+1 < nums.length) && (nums[i] == nums[i+1]))
                i ++;
            i ++;
        }
        return res;
    }
}
