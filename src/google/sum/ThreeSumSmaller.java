package google.sum;

/**
 * Created by max2 on 11/10/15.
 */
/*
*
*Given an array of n integers nums and a target, find the number of index triplets i, j, k
* with 0 <= i < j < k < n that satisfy the condition nums[i] + nums[j] + nums[k] < target.

For example, given nums = [-2, 0, 1, 3], and target = 2.

Return 2. Because there are two triplets which sums are less than 2:

[-2, 0, 1]
[-2, 0, 3]
Follow up:
Could you solve it in O(n2) runtime?
*
* */
public class ThreeSumSmaller {

    public int threeSumSmaller(int[] nums, int target) {
        if ((nums == null) || (nums.length == 0)) return 0;
        int p1 = 0;
        int count = 0;
        while (p1 < nums.length -2) {
            int p2 = p1 + 1;
            int p3 = nums.length - 1;

            while (p2 < p3) {
                if (nums[p1] +nums[p2] + nums[p3] <= target) {
                    count = count + p3 - p2;
                    p2 ++;
                }
                else {
                    p3 --;
                }
            }
            p1 ++;
        }
        return count;
    }
}
