package array;

/**
 * Created by yingtan on 2/20/18.
 * 45. Jump Game II
 DescriptionHintsSubmissionsDiscussSolution
 Pick One
 Given an array of non-negative integers, you are initially positioned at the first index of the array.

 Each element in the array represents your maximum jump length at that position.

 Your goal is to reach the last index in the minimum number of jumps.

 For example:
 Given array A = [2,3,1,1,4]

 The minimum number of jumps to reach the last index is 2. (Jump 1 step from index 0 to 1, then 3 steps to the last index.)

 Note:
 You can assume that you can always reach the last index.
 */
public class JumpGameII {
    public int jump(int[] nums) {
        int end = 0;
        int steps = 0;
        int farest = 0;
        //  using greedy
        // [start, end, .... , farest], we set step[start, end] = init_step,
        // when reach end, this means one step is finished, we need to take another step, so we set step ++ = init_step + 1,
        // and between [end + 1, farest], the min step is the same = init_step + 1
        // important !!! not loop the last element,  for case [0]
        for (int i = 0 ; i < nums.length - 1; i ++) {
            farest = Math.max(farest, i + nums[i]);
            if (i == end) { // for the first 0, we take the first step, so step ++
                steps ++;
                // between end - farest, the min step is the same.
                end = farest;
            }
        }
        return steps;
    }
}
