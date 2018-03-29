package facebook.array;
/*
* Given an array S of n integers,
* are there elements a, b, c in S such that a + b + c = 0? Find all unique triplets in the array which gives the sum of zero.
* result里面的三个数有一个数字可以重复2次的
*
* a b c
* */
import java.util.*;
public class ThreeSumDuplicateTwice {
    public List<List<Integer>> threesum_duplicateTwice(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums == null || nums.length == 0) return res;
        Arrays.sort(nums);
        int i = 0;
        while(i < nums.length - 2) {
            int low = i;
            int high = nums.length - 1;
            while(low <= high) {
                if (nums[i] + nums[low] + nums[high] == 0) {
                    if (i == low && low == high) {
                        // three duplicate
                        continue;
                    }
                    List<Integer> path = new ArrayList<>();
                    path.add(nums[i]);
                    path.add(nums[low]);
                    path.add(nums[high]);
                    low ++;
                    high --;
                }
                else if (nums[i] + nums[low] + nums[high] < 0) {
                    low ++;
                }
                else if (nums[i] + nums[low] + nums[high] > 0) {
                    high --;
                }
            }
            i ++;
        }
        return res;
    }
}
