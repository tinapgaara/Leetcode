package binarysearch;

/**
 * Created by yingtan on 1/21/18.
 * 41. First Missing Positive
 DescriptionHintsSubmissionsDiscussSolution
 DiscussPick One
 Given an unsorted integer array, find the first missing positive integer.

 For example,
 Given [1,2,0] return 3,
 and [3,4,-1,1] return 2.

 Your algorithm should run in O(n) time and uses constant space.
 */
public class FindFirstMissingPositive {

    public int firstMissingPositive(int[] nums) {
        if (nums == null || nums.length == 0) return 1;
        for (int i = 0 ; i < nums.length; i ++) {
            while(nums[i] - 1 >= 0 && nums[i] - 1 < nums.length &&
                    nums[nums[i] - 1] != nums[i]) {
                // condition nums[nums[i] -1] != nums[i] can check wrong placed number as well as duplicate number
                // so used to identify missing number
                // but this will ignorethe duplicate number
                int supposedPos = nums[i] - 1;
                int tmp = nums[i];
                nums[i] = nums[supposedPos];
                nums[supposedPos] = tmp;
            }
        }
        int i = 0;
        for (; i < nums.length; i ++) {
            if (nums[i] != i + 1) {
                return (i + 1);
            }
        }
        return i + 1;
    }
}
