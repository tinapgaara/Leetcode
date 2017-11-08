package facebook.array.scanTwiceFromStartAndEnd;

/**
 * Created by yingtan on 5/21/17.
 *
 * 238. Product of Array Except Self Add to List
 DescriptionHintsSubmissionsSolutions
 Total Accepted: 94569
 Total Submissions: 195521
 Difficulty: Medium
 Contributor: LeetCode
 Given an array of n integers where n > 1, nums, return an array output such that output[i] is equal to the product of all the elements of nums except nums[i].

 Solve it without division and in O(n).

 For example, given [1,2,3,4], return [24,12,8,6].

 Follow up:
 Could you solve it with constant space complexity? (Note: The output array does not count as extra space for the purpose of space complexity analysis.)
 */
public class ProductArrayExceptSelf {

    public int[] productExceptSelf(int[] nums) {
        int[] res =  new int[nums.length];
        if (nums == null || nums.length == 0) return res;

        int productFromStart = 1;
        int productFromEnd = 1;
        for (int i = 0 ; i < nums.length; i ++) {
            res[i] = productFromStart;
            productFromStart = productFromStart * nums[i];
        }
        for (int i = nums.length - 1; i >= 0; i --) {
            res[i] = res[i] * productFromEnd;
            productFromEnd = productFromEnd * nums[i];
        }
        return res;
    }
}
