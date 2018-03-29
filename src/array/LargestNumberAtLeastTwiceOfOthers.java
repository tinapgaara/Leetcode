package array;

/**
 * Created by yingtan on 3/1/18.
 *
 * 747. Largest Number At Least Twice of Others
 DescriptionHintsSubmissionsDiscussSolution
 Pick One
 In a given integer array nums, there is always exactly one largest element.

 Find whether the largest element in the array is at least twice as much as every other number in the array.

 If it is, return the index of the largest element, otherwise return -1.

 Example 1:

 Input: nums = [3, 6, 1, 0]
 Output: 1
 Explanation: 6 is the largest integer, and for every other number in the array x,
 6 is more than twice as big as x.  The index of value 6 is 1, so we return 1.


 Example 2:

 Input: nums = [1, 2, 3, 4]
 Output: -1
 Explanation: 4 isn't at least as big as twice the value of 3, so we return -1.
 */
public class LargestNumberAtLeastTwiceOfOthers {
    public int dominantIndex(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int max = -1;
        int secondMax = -1;
        int maxIndex = -1;
        int secondIndex = -1;
        for (int i = 0; i < nums.length ; i ++) {
            int num = nums[i];
            if (num == max || num == secondMax) continue;;
            if (num > max) {
                secondMax = max;
                secondIndex = maxIndex;
                max = num;
                maxIndex = i;
                continue;
            }
            else if (num > secondMax) {
                secondMax = num;
                secondIndex = i;
            }
        }
        if (max >= secondMax * 2) {
            return maxIndex;
        }
        else {
            return -1;
        }
    }
}
