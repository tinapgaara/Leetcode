package greedy;

/**
 * Created by yingtan on 10/10/15.
 */
/*
* Leetcode:  Jump Game
*
* Given an array of non-negative integers, you are initially positioned at the first index of the array.

    Each element in the array represents your maximum jump length at that position.

    Determine if you are able to reach the last index.

    For example:
    A = [2,3,1,1,4], return true.

    A = [3,2,1,0,4], return false
*
* */
public class JumpGame {

    // Solution Greedy:
    /*
        to judge if some previous point can jump to this point
        [a b c d e] :
        if a can not -> c, and c can -> e, then:
        there is no case such that: a can -> b, and b can -> e.
        Because: when b can -> e, then b also can -> c.
        Also, since a can -> b, so a -> c = a -> b + b -> c. a can reach c as well.

        every time, we just need to find the closet point to e which can arrive e, and to judge if there is previous points can jump to e.
        If a can not -> c, even c can -> e, there is no path like: a-> b -> e. Thus, a can not -> e.
    */
    public boolean canJumpGreedy(int[] nums) {

        int last = nums.length - 1;
        for (int i = nums.length-2; i >=0 ; i --) {
            int curDistance = last - i ;
            int maxJumpAtCurPos = nums[i];
            if (maxJumpAtCurPos >= curDistance) {
                last = i;
            }
        }
        if (last != 0) return false;
        else return true;
    }

    // Solution 1; recursve: Time out of Limit
    public boolean canJump(int[] nums) {
        if ( (nums == null) || (nums.length == 0) ) return false;
        return recurCanJump(nums, 0);
    }
    public boolean recurCanJump(int[] nums, int index) {
        if (index >= nums.length) return false;
        if (index == nums.length - 1) return true;

        int maxLen = nums[index];
        for (int i = maxLen; i >= 1; i --) {
            if (recurCanJump(nums, (index + i))) return true;
        }
        return false;
    }
}
