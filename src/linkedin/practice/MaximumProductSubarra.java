package linkedin.practice;

/**
 * Created by yingtan on 11/22/17.
 */
public class MaximumProductSubarra {

    public int maxProduct(int[] nums) {
        if ((nums == null) || (nums.length == 0)) return 0;
        int maxSoFar = 0;
        int minSoFar = 0;
        int[] maxEnds = new int[nums.length];
        int[] minEnds = new int[nums.length];
        maxEnds[0] = nums[0];
        minEnds[0] = nums[0];
        for (int i = 1 ; i < nums.length ; i ++) {
            int maxCandidate = maxEnds[i-1] * nums[i];
            int minCandidate = minEnds[i-1] * nums[i];
            if (minCandidate > maxCandidate) {
                int tmp = minCandidate;
                minCandidate = maxCandidate;
                maxCandidate = tmp;
            }
            maxCandidate = Math.max(maxCandidate, nums[i]);
            minCandidate = Math.min(minCandidate, nums[i]);

            maxSoFar = Math.max(maxSoFar, maxCandidate);
            minSoFar = Math.min(minSoFar, minCandidate);
            maxEnds[i] = maxSoFar;
            minEnds[i] = minSoFar;
        }
        return maxSoFar;
    }
}
