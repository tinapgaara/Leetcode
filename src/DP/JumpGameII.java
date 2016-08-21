package DP;

/**
 * Created by yingtan on 10/10/15.
 */
public class JumpGameII {

    // Greedy:
    /*
    *  2 3 1 1 4
    *  constructs a subarray, which reachs to first element's farest point
    * [2 3 1] :
    * Go through all elements in [2 3 1] to find the farest point these three elements can reach :
    *    maxDist = Math.max(maxDist, i + nums[i]);
    *   Here, can reach the last element (farest one)
    *
    * Then, while loop, constructs a new subarray: using the last element in [2 3 1] + all sub arrays before 4
    * [1 1 4] :
    *
    * So,
    * [2 3 1]: jump 1
    * [1 1 4]: jump 2
    *
    * Reason:
    * [2 3 1]'s step = 1: means 2 start point can reach any elements before and including 1 using one step.
    * [1 1 4]'s step = 1: means there exist one element before 1 which can reach any elements
    *                       between [1 1 4] using one step
    *
    * Conclusion:
    *  There is a element in [2 3 1] which can be reached from start point using one step, and go the end point 4
    *  using one step. All steps : 1+1 = 2;
    *
    * */
    public int jumpGreedy(int[] nums) {
        if ( (nums == null) || (nums.length == 0) ) return 0;
        if (nums.length == 1)
            return 0;
        int count = 1;
        int start = 0;
        int end = nums[0];
        while (end < nums.length - 1) {
            int farestReachPoint = 0;
            for (int i = start; i <= end; i ++) {
                int curNumReachPoint = i + nums[i];
                farestReachPoint = Math.max(farestReachPoint, curNumReachPoint);
            }
            count ++;
            start = end;
            end = farestReachPoint;
        }
        return count;
    }

    // DP: Memory out of Limit
    public int jump(int[] nums) {
        if ( (nums == null) || (nums.length == 0) ) return 0;

        int[][] steps = new int[nums.length][nums.length];

        int len = 1;
        while (len < nums.length) {
            int low = 0;
            int high  = low + len;
            while (high < nums.length) {
                if (nums[low] >= (high - low)) {
                    steps[low][high] = 1;
                }
                else {
                    int min = Integer.MAX_VALUE;
                    for (int i = low + 1; i < high; i++) {
                        min = Integer.min(min, steps[low][i] + steps[i][high]);
                    }
                    steps[low][high] = min;
                }
                low ++;
                high = low + len;
            }
            len ++;
        }
        return steps[0][nums.length-1];
    }

    public static void main(String[] args) {
        JumpGameII ob = new JumpGameII();
        int[] a = new int[]{2,3,1,1,4};
        System.out.println(ob.jump(a));
    }
}
