package linkedin.dp;

import java.util.HashMap;

/**
 * Created by yingtan on 11/29/17.
 *
 * After solving several "Game Playing" questions in leetcode, I find them to be pretty similar. Most of them can be solved using the top-down DP approach, which "brute-forcely" simulates every possible state of the game.

 The key part for the top-down dp strategy is that we need to avoid repeatedly solving sub-problems. Instead, we should use some strategy to "remember" the outcome of sub-problems. Then when we see them again, we instantly know their result. By doing this, we can always reduce time complexity from exponential to polynomial.
 (EDIT: Thanks for @billbirdh for pointing out the mistake here. For this problem, by applying the memo, we at most compute for every subproblem once, and there are O(2^n) subproblems, so the complexity is O(2^n) after memorization. (Without memo, time complexity should be like O(n!))

 For this question, the key part is: what is the state of the game? Intuitively, to uniquely determine the result of any state, we need to know:

 The unchosen numbers
 The remaining desiredTotal to reach
 A second thought reveals that 1) and 2) are actually related because we can always get the 2) by deducting the sum of chosen numbers from original desiredTotal.

 Then the problem becomes how to describe the state using 1).
 */
public class CanIWin {

    /*
    * Important Idea:
    *
    * For this question, the key part is:
    * what is the state of the game? Intuitively, to uniquely determine the result of any state, we need to know:

The unchosen numbers
The remaining desiredTotal to reach
A second thought reveals that
1) and 2) are actually related because we can always get the 2) by deducting the sum of chosen numbers from original desiredTotal.
    *
    * */
    public boolean canIWin_selfSolution(int maxChoosableInteger, int desiredTotal) {
        boolean[] nums = new boolean[maxChoosableInteger + 1];
        int codeLen = 0;
        int sum= 0;
        for (int i = 1; i < nums.length; i ++) {
            codeLen = (codeLen << 1) + 1;
            sum = sum + i;
        }
        if (sum < desiredTotal) return false;
        // encode 1-maxChoosableInteger to an integer with bits [1,2,3,....maxChoosableInteger]
        Boolean[] dp = new Boolean[codeLen + 1];
        return recurCanWin(nums, desiredTotal, dp);
    }
    public boolean recurCanWin(boolean[] nums, int total, Boolean[] dp) {
        int code = encode(nums);
        if (dp[code] != null) {
            return dp[code];
        }
        // if one of nums can ensure win, then it wins (one nextCanWin=true)
        // if all nums will lead the next player wins, then it loses. (all nextCanWin=false)

        // boundary case: 5, 50. Taget is big, both two players can not reach!
        for (int i = 1; i < nums.length; i ++) {
            if (! nums[i]) {
                // i have not been choosen, can choose number i at this point
                nums[i] = true;
                if (i >= total) {
                    // this player wins
                    dp[code] = true;
                    nums[i] = false;
                    return true;
                }
                else {
                    boolean nextCanWin = recurCanWin(nums, total - i, dp);
                    // logic here is very important !!!!!
                    if (! nextCanWin) {
                        dp[code] = true;
                        nums[i] = false;
                        return true;
                    }
                }
                nums[i] = false;
            }
        }
        dp[code] = false;
        return dp[code];
    }
    public int encode(boolean[] nums) {
        int res = 0;
        for (int i = 1; i < nums.length; i ++) {
            if (nums[i]) {
                res = (res << 1) + 1;
            }
            else {
                res = res << 1;
            }
        }
        return res;
    }



    public boolean canIWin(int maxChoosableInteger, int desiredTotal) {
        int sum = (1 + maxChoosableInteger) * maxChoosableInteger / 2;
        if (sum < desiredTotal) return false;
        if (desiredTotal <= 0) return true;

        HashMap<Integer, Boolean> states = new HashMap<>();
        boolean[] vis = new boolean[maxChoosableInteger + 1];
        return recurWin(desiredTotal, vis, states, maxChoosableInteger);
    }
    public boolean recurWin(int desiredTotal, boolean[] vis, HashMap<Integer, Boolean> states, int maxChoosableInteger) {
        if (desiredTotal <= 0) return false;
        int key = format(vis);
        if (! states.containsKey(key)) {
            for (int i = 1 ; i <= maxChoosableInteger ; i ++) {
                if (! vis[i]) {
                    vis[i] = true;
                    if(! recurWin(desiredTotal - i, vis, states, maxChoosableInteger)) {
                        vis[i] = false;
                        states.put(key, true);
                        return true;
                    }
                    vis[i] = false;
                }
            }
            states.put(key, false);
        }
        return states.get(key);
    }

    public int format(boolean[] vis) {
        int num = 0;
        for (boolean b : vis) {
            num = num << 1;
            if (b) {
                num = num | 1;
            }
        }
        return num;
    }
}
