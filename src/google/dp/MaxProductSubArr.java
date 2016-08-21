package google.dp;

/**
 * Created by yingtan on 12/22/15.
 */
public class MaxProductSubArr {

    public int maxProduct(int[] nums) {
        if ((nums == null) || (nums.length == 0)) return 0;

        int[] maxEnd = new int[nums.length];
        int[] minEnd = new int[nums.length];

        maxEnd[0] = nums[0];
        minEnd[0] = nums[0];

        int max = nums[0];
        int min = nums[0];
        for (int i = 1; i < nums.length; i ++) {
            int maxCandidate = maxEnd[i-1] * nums[i];
            int minCandidate = minEnd[i-1] * nums[i];
            if (maxCandidate < minCandidate) {
                int tmp = maxCandidate;
                maxCandidate = minCandidate;
                minCandidate = tmp;
            }

            maxCandidate = Math.max(maxCandidate, nums[i]);
            minCandidate = Math.min(minCandidate, nums[i]);;

            max = Math.max(max, maxCandidate);
            min = Math.min(min, minCandidate);
            maxEnd[i] = maxCandidate;
            minEnd[i] = minCandidate;
        }
        return max;
    }
}
