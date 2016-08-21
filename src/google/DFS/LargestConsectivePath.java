package google.DFS;

/**
 * Created by yingtan on 11/5/15.
 */
public class LargestConsectivePath {

    // longest path from any point in two-dim arr
    public int longestConsecutivePath(int[][] arr) {
        int max = 0;
        for (int i = 0 ; i < arr.length ; i ++) {
            for (int j = 0 ; j < arr[0].length; j ++) {
                boolean[][] visitedFlags = new boolean[arr.length][arr[0].length];
                max = Math.max(max, DFS(i, j, arr,visitedFlags));
            }
        }
        return max;
    }

    public int DFS(int x, int y, int[][] arr, boolean[][] visitedFlags) {
        int max = 0;
        if (!visitedFlags[x][y]) {
            visitedFlags[x][y] = true;
            if ((x - 1) >= 0) {
                if (arr[x-1][y] == arr[x][y] + 1) {
                    max = Math.max(max, DFS(x-1, y, arr, visitedFlags));
                }
            }
            if ((y - 1) >= 0) {
                if (arr[x][y-1] == arr[x][y] + 1) {
                    max = Math.max(max, DFS(x, y-1, arr, visitedFlags));
                }
            }
            if ((x + 1) < visitedFlags.length) {
                if (arr[x+1][y] == arr[x][y] + 1) {
                    max = Math.max(max, DFS(x+1, y, arr, visitedFlags));
                }
            }
            if ((y + 1) < visitedFlags[0].length) {
                if (arr[x][y + 1] == arr[x][y] + 1) {
                    max = Math.max(max, DFS(x, y + 1, arr, visitedFlags));
                }
            }
        }
        return max + 1;
    }

    public static void main(String[] args) {
        LargestConsectivePath ob = new LargestConsectivePath();
        int[][] nums = new int[][]{{7,7,6},{9,4,5},{2,3,1}};
        System.out.println(ob.longestConsecutivePath(nums));
    }
}
