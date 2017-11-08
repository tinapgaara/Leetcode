package google.array;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by yingtan on 1/22/17.
 *
 *
 * 487. Max Consecutive Ones II   Add to List QuestionEditorial Solution  My Submissions
 Total Accepted: 2030
 Total Submissions: 5025
 Difficulty: Medium
 Contributors: Stomach_ache
 Given a binary array, find the maximum number of consecutive 1s in this array if you can flip at most one 0.

 Example 1:
 Input: [1,0,1,1,0]
 Output: 4
 Explanation: Flip the first zero will get the the maximum number of consecutive 1s.
 After flipping, the maximum number of consecutive 1s is 4.
 Note:

 The input array will only contain 0 and 1.
 The length of input array is a positive integer and will not exceed 10,000


 [ 1s 0  1s]
 find maxLen of this subarray which separates by one 0
 zeroLeft: how many consecutive 1s lefter than 0, or include 0
 zeroRight: how many consecutive 1s righter than 0, not include 0
 */
public class MaxConsecutiveOnesII {

    public int findMaxConsecutiveOnes(int[] nums) {
        if ((nums == null) || (nums.length == 0)) return 0;
        int zeroLeft = 0;
        int zeroRight = 0; // index of subarray starts with 0
        int max = 0;

        for (int i = 0 ; i < nums.length; i ++) {
            zeroRight ++; // keep record of how many consecutive 1s after prev 0
            if (nums[i] == 0) {
                zeroLeft = zeroRight;// when reachs 0, zeroRight stores how many 1s lefter than this zero
                zeroRight = 0;// reset
            }
        }
        max = Math.max(max, zeroLeft + zeroRight);
        return max;
    }
}
