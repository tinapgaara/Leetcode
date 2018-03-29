package DP;

/**
 * Created by yingtan on 1/13/18.
 *
 * 464. Can I Win
 DescriptionHintsSubmissionsDiscussSolution
 DiscussPick One
 In the "100 game," two players take turns adding, to a running total, any integer from 1..10. The player who first causes the running total to reach or exceed 100 wins.

 What if we change the game so that players cannot re-use integers?

 For example, two players might take turns drawing from a common pool of numbers of 1..15 without replacement until they reach a total >= 100.

 Given an integer maxChoosableInteger and another integer desiredTotal, determine if the first player to move can force a win, assuming both players play optimally.

 You can always assume that maxChoosableInteger will not be larger than 20 and desiredTotal will not be larger than 300.

 Example

 Input:
 maxChoosableInteger = 10
 desiredTotal = 11

 Output:
 false

 Explanation:
 No matter which integer the first player choose, the first player will lose.
 The first player can choose an integer from 1 up to 10.
 If the first player choose 1, the second player can only choose integers from 2 up to 10.
 The second player will win by choosing 10 and get a total = 11, which is >= desiredTotal.
 Same with other integers chosen by the first player, the second player will always win.

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

    public boolean canIWin(int maxChoosableInteger, int desiredTotal) {
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

    public static void main(String[] args) {
        CanIWin ob = new CanIWin();
        System.out.println(ob.canIWin(5, 50));
    }
}
