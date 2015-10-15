package DP;

/**
 * Created by yingtan on 10/11/15.
 */
/* Leetcode: Distinct Subsequences

    Given a string S and a string T, count the number of distinct subsequences of T in S.

    A subsequence of a string is a new string which is formed from the original string by deleting some (can be none) of the characters without disturbing the relative positions of the remaining characters. (ie, "ACE" is a subsequence of "ABCDE" while "AEC" is not).

    Here is an example:
    S = "rabbbit", T = "rabbit"

    Return 3.

    Because:
    T:  ra + b + bbit = S
    T:  rab + b + bit = S
    T:  rabb + b + it = S

* each entry in matrix means: the value of maxnumb ways to insert new chars (or none)
* to subsequence of T to form the same subsequence of S
*
* Analysis:
* S: ______ a
* T: ______ b
* s.charAt(i) != t.charAt(i), a must be transformed from T.
*  - Transform way: T firstly transfer to S:____ then, add a to tail to S.
* Here, a must be tail of S. So, there is only one way to attach a, and so, the different ways to transform T to S:____
* equals to the number of different ways to transform T to S. And we got:
* count[i][j] = count[i-1][j] // i \in T, j is in S
*
* S: ______ b
* T: ______ b
* If s.charAt(i) == t.charAt(i) == b,
* 1) S's b can be the same as T'b, so this S'b is not transfromed from T'b.: insertion b is before the tail b.
*   count[i][j] = count[i-1][j-1]
* 2) S's b can be transformed from b.
*    - Transform way: T firstly transfer to S:____ then, add b to tail to S. : Always insert b at the end of string (1 kind)
*   count[i][j] = count[i-1][j]
*
* Finally,
* count[i][j] = count[i-1][j-1] + count[i-1][j]
*
* Eg:     i
* S:      null  r   a   b  b  b  i  t
* T: null  1    1   1   1  1  1  1  1    /// because only one way null string can transform to S. (+S)
* j   r    0    1   1   1  1  1  1  1
*     a    0    0   1   1  1  1  1  1
*     b    0    0   0   1  2  3
*     b    0
*     i    0
*     t    0
*
* // Means:
* T: rab - > S:rabb: 2 ways
* 1) [i-1][j-1] == 1:  ra -> rab : one way.  T:rab -> S:rabb: ra +b+  b -> rabb (insert middle)
* 2) [i-1][j] == 1:  rab -> rab: one way. T:rab -> S:rabb:  rab +b  -> rabb (insert tail)
*     b  b
* a   1  1
* b   1  2
*
* Means:
* T: rabb -> S:rabbb : 3 ways
* 1) [i-1][j-1] = 1: rab +b+  b -> rabbb
* 2) [i-1][j] = 2: rabb +b -> rabbb  ; ra +b+ bb -> rabbb
*     b  b
* a   1  1
* b   2  3
*
*
* */

public class NumDistinctSubsequence {

    public int numDistinct(String s, String t) {
        if ((s == null) && (t == null)) return 0;
        if ( (s == null) && (t != null)) return t.length();
        if ( (t == null) && (s != null)) return s.length();

        int[][] nums = new int[s.length()+1][t.length()+1];
        for (int i = 0 ; i <= s.length(); i ++){
            nums[i][0] = 1;
        }
        for (int i = 1; i <= s.length(); i ++) {
            for (int j = 1; j <= t.length(); j ++) {
                if (s.charAt(i-1) == t.charAt(j-1)) {
                    nums[i][j] = nums[i-1][j] + nums[i-1][j-1];
                }
                else {
                    nums[i][j] = nums[i-1][j];
                }
            }
        }
        return nums[s.length()][t.length()];
    }
}
