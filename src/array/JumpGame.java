package array;

/**
 * Created by yingtan on 2/20/18.
 *
 * 55. Jump Game
 DescriptionHintsSubmissionsDiscussSolution
 Pick One
 Given an array of non-negative integers, you are initially positioned at the first index of the array.

 Each element in the array represents your maximum jump length at that position.

 Determine if you are able to reach the last index.

 For example:
 A = [2,3,1,1,4], return true.

 A = [3,2,1,0,4], return false.


 */
public class JumpGame {
    public boolean canJump(int[] nums) {
        if (nums == null || nums.length == 0) return false;
        int farest = 0;
        for (int i = 0 ; i < nums.length; i ++) {
            farest = Math.max(farest, i + nums[i]);
            if (farest >= nums.length - 1) {
                return true;
            }
            else if (farest == i) { // only keep the previous stage
                return false;
            }
        }
        return false;
    }
}
