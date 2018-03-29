package linkedin.array;

/**
 * Created by yingtan on 11/28/17.
 */
public class MaximuProductSubarray {

    public int maxProduct(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int[] maxEnds = new int[nums.length];
        int[] minEnds = new int[nums.length];

        int res = nums[0];
        maxEnds[0] = nums[0];
        minEnds[0] = nums[0];

        for (int i = 1; i < nums.length; i ++) {
            minEnds[i] = nums[i];
            maxEnds[i] = nums[i];
            minEnds[i] = Math.min(minEnds[i],Math.min(minEnds[i-1] * nums[i], maxEnds[i-1] * nums[i]));

            maxEnds[i] = Math.max(maxEnds[i], Math.max(minEnds[i-1] * nums[i], maxEnds[i-1] * nums[i]));
            res = Math.max(res, maxEnds[i]);
        }
        return Math.max(res, maxEnds[nums.length - 1]);
    }
}
