package array;

/**
 * Created by yingtan on 3/4/18.
 *
 * 645. Set Mismatch
 DescriptionHintsSubmissionsDiscussSolution
 Pick One
 The set S originally contains numbers from 1 to n. But unfortunately, due to the data error, one of the numbers in the set got duplicated to another number in the set, which results in repetition of one number and loss of another number.

 Given an array nums representing the data status of this set after the error. Your task is to firstly find the number occurs twice and then find the number that is missing. Return them in the form of an array.

 Example 1:
 Input: nums = [1,2,2,4]
 Output: [2,3]
 Note:
 The given array size will in the range [2, 10000].
 The given array's numbers won't have any order.

 */
public class SetMismatch {
    // 基数排序
    public int[] findErrorNums(int[] nums) {
        if (nums == null || nums.length == 0) return nums;
        for (int i = 0 ; i < nums.length; i ++) {
            while (nums[i] != i + 1) {
                // put nums[i]to the correct place
                int index = nums[i] - 1;
                int tmp = nums[index];
                if (tmp == nums[i]) {
                    // this is the dup number
                    break;
                }
                nums[index] = nums[i];
                nums[i] = tmp;
            }
        }
        int[] res = new int[2];
        for (int i = 0 ; i < nums.length; i ++) {
            if (nums[i] != i + 1) {
                res[0] = nums[i]; // dup
                res[1] = i + 1; // missing
            }
        }
        return res;
    }

}
