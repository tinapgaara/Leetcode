package array;

/**
 * Created by yingtan on 2/26/18.
 *
 * 414. Third Maximum Number
 DescriptionHintsSubmissionsDiscussSolution
 Pick One
 Given a non-empty array of integers, return the third maximum number in this array. If it does not exist, return the maximum number. The time complexity must be in O(n).

 Example 1:
 Input: [3, 2, 1]

 Output: 1

 Explanation: The third maximum is 1.
 Example 2:
 Input: [1, 2]

 Output: 2

 Explanation: The third maximum does not exist, so the maximum (2) is returned instead.
 Example 3:
 Input: [2, 2, 3, 1]

 Output: 1

 Explanation: Note that the third maximum here means the third maximum distinct number.
 Both numbers with value 2 are both considered as second maximum.
 */
public class ThirdMaximumNumber {
    public int thirdMax(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        Integer max = null;
        Integer secondMax = null;
        Integer thirdMax = null;
        for (Integer num : nums) {
            if (num.equals(max) || num.equals(secondMax) || num.equals(thirdMax)) {
                continue;
            }
            if (max == null || num > max) {
                thirdMax = secondMax;
                secondMax = max;
                max = num;
            }
            else if (secondMax == null || num > secondMax) {
                thirdMax = secondMax;
                secondMax = num;
            }
            else if (thirdMax == null || num > thirdMax) {
                thirdMax = num;
            }
        }
        if (thirdMax == null) {
            thirdMax = max;
        }
        return thirdMax;
    }
}
