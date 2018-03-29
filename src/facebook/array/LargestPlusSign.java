package facebook.array;

import java.util.Arrays;

public class LargestPlusSign {
    // array solution
    public int orderOfLargestPlusSign(int N, int[][] mines) {
        if (N == 0) return 0;
        if (mines == null || mines.length == 0) {
            return (N - 1) / 2 + 1;
        }
        int[][] grid = new int[N][N];
        for (int[] g : grid) {
            Arrays.fill(g, 1);
        }
        for (int[] mine : mines) {
            grid[mine[0]][mine[1]] = 0;
        }
        int res = 0;
        for (int i = 0 ; i < N; i ++) {
            for (int j = 0; j < N; j ++) {
                if (grid[i][j] == 1) {
                    int delta = 1;
                    int max = 1;
                    while(i -delta >= 0 && i + delta < N && j - delta >= 0 && j + delta < N) {
                        if (grid[i-delta][j] == 1 && grid[i+delta][j] == 1
                                && grid[i][j-delta] == 1 && grid[i][j+delta] == 1) {
                            max = Math.max(max, delta + 1);
                            delta ++;
                        }
                        else {
                            break;
                        }
                    }
                    res = Math.max(max, res);
                }
            }
        }
        return res;
    }
    // Time exceed Limit !!! dp solution
    public int orderOfLargestPlusSign_TEL(int N, int[][] mines) {
        if (N == 0) return 0;
        if (mines == null || mines.length == 0) {
            return (N-1)/2 + 1;
        }
        boolean[][] dp = new boolean[N][N];
        boolean[][] block = new boolean[N][N];
        for (int[] mine: mines) {
            block[mine[0]][mine[1]] = true;
        }
        boolean hasValid = false;
        // init
        for (int i = 0 ; i < N; i ++) {
            for (int j = 0 ; j < N; j ++) {
                if (! block[i][j]) {
                    dp[i][j] = true;
                    hasValid = true;
                }
            }
        }
        for (int n = 2; n <= N ; n ++) {
            boolean localhasValid = false;
            for (int i = n - 1; i < N - (n-1); i ++) {
                for (int j = n - 1; j < N - (n - 1); j ++) {
                    if (dp[i][j]) {
                        //System.out.println(i + "," + j + "," + n);
                        if (i-n+1 >= 0 && i+n-1 < N && j-n+1 >= 0 && j+n-1 < N) {
                            if (! block[i-n+1][j] && ! block[i+n-1][j]
                                    && ! block[i][j+n-1] && ! block[i][j-n+1]) {
                                dp[i][j] = true;
                                //System.out.println(i + "," + j + "," + n + ": is true");
                                localhasValid = true;
                            }
                        }
                    }
                }
            }
            if (! localhasValid && hasValid) {
                return n-1;
            }
        }
        //System.out.println(hasValid);
        if (hasValid) return N;
        else return 0;
    }
}
