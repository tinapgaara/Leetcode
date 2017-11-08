package google.DFS;

/**
 * Created by yingtan on 2/12/17.
 *
 * 351. Android Unlock Patterns
 Description  Submission  Solutions  Add to List
 Total Accepted: 10550
 Total Submissions: 24541
 Difficulty: Medium
 Contributors: Admin
 Given an Android 3x3 key lock screen and two integers m and n, where 1 ≤ m ≤ n ≤ 9, count the total number of unlock patterns of the Android lock screen, which consist of minimum of m keys and maximum n keys.

 Rules for a valid pattern:
 Each pattern must connect at least m keys and at most n keys.
 All the keys must be distinct.
 If the line connecting two consecutive keys in the pattern passes through any other keys, the other keys must have previously selected in the pattern. No jumps through non selected key is allowed.
 The order of keys used matters.


 xplanation:
 | 1 | 2 | 3 |
 | 4 | 5 | 6 |
 | 7 | 8 | 9 |
 Invalid move: 4 - 1 - 3 - 6
 Line 1 - 3 passes through key 2 which had not been selected in the pattern.

 Invalid move: 4 - 1 - 9 - 2
 Line 1 - 9 passes through key 5 which had not been selected in the pattern.

 Valid move: 2 - 4 - 1 - 3 - 6
 Line 1 - 3 is valid because it passes through key 2, which had been selected in the pattern

 Valid move: 6 - 5 - 4 - 1 - 9 - 2
 Line 1 - 9 is valid because it passes through key 5, which had been selected in the pattern.

 Example:
 Given m = 1, n = 1, return 9.

 */
public class AndroidUnlockPattern {
    public int dfs(int[][] skip, boolean[] vis, int curNum, int restLen) {
        if (restLen < 0) return 0;
        // important
        if (restLen == 0) return 1;
        int ways = 0;
        vis[curNum] = true;
        // loop all possible jumped targets, jump from curNum to  next possible num
        for (int nextNum = 1; nextNum <= 9; nextNum ++) {

            if ( (skip[curNum][nextNum] == 0) // nextNum is curNum's neighbor
                    && (! vis[nextNum]) )  { // nextNum has not been visited
                ways = ways + dfs(skip, vis, nextNum, restLen - 1);
            }
            else { // nextNum is not curNum's neighbor
                int skippedNum = skip[curNum][nextNum];
                if (vis[skippedNum] // nextNum is not curNum's neighbor, but curNum can jump to i since i has been visited
                        && (! vis[nextNum])) // nextNum has not been visited
                {
                    ways = ways + dfs(skip, vis, nextNum, restLen - 1);
                }
            }

        }
        vis[curNum] = false;
        return ways;
    }

    public int numberOfPatterns(int m, int n) {

        int skip[][] = new int[10][10];
        // important ! skip[i][j] : which number is need to be vis when jump from i to j
        skip[1][3] = skip[3][1] = 2;
        skip[1][7] = skip[7][1] = 4;
        skip[3][9] = skip[9][3] = 6;
        skip[7][9] = skip[9][7] = 8;
        skip[1][9] = skip[9][1] = skip[2][8] = skip[8][2] = skip[3][7] = skip[7][3] = skip[4][6] = skip[6][4] = 5;
        boolean vis[] = new boolean[10];
        int count = 0;
        // min pathLen , max pathLen
        for (int len = m ; len <= n ; len ++) {
            for (int curNum = 1; curNum <= 9 ; curNum ++) {
                count = count + dfs(skip, vis, curNum, len-1);
            }
        }
        return count;
    }

}
