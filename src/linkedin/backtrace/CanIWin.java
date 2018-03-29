package linkedin.backtrace;

/**
 * Created by yingtan on 11/19/17.
 *
 * 464. Can I Win
 DescriptionHintsSubmissionsDiscussSolution
 Discuss Pick One
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

    public boolean canIWin(int maxChoosableInteger, int desiredTotal) {
        boolean[] vis = new boolean[maxChoosableInteger + 1];
        int total = 0;
        return canWin(maxChoosableInteger, vis, total, desiredTotal, 0, 0);
    }
    public boolean canWin(int maxChoosableInteger, boolean[] vis, int total, int desiredTotal, int p, int depth) {
        int loseCount = 0;
        int round = 0;
        for (int i = 1; i <= maxChoosableInteger; i ++) {
            if (! vis[i]) {
                vis[i] = true;
                round ++;
                total = total + i;
                if (total >= desiredTotal) {
                    vis[i] = false;
                    return true;
                }
                if (depth == 1) {
                    System.out.println();
                }
                // if another persona win at any chance, return false here
                if (canWin(maxChoosableInteger, vis, total, desiredTotal, p, depth + 1)) {
                    loseCount ++ ;
                    if (depth == 1) {
                        System.out.println("player 2 can not win with num: " + i);
                    }
                }
                else {
                    vis[i] = false;
                    return true;
                }
                vis[i] = false;
                total = total - i;

            }
        }
        if (loseCount == round) return false;
        return true;
    }

    public static void main(String[] args) {
        CanIWin ob = new CanIWin();
        System.out.println(ob.canIWin(4, 5));
    }
}
