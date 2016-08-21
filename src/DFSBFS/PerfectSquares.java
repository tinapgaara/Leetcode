package DFSBFS;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by max2 on 10/8/15.
 */
/*
* Leetcode:
*
*
* Given a positive integer n, find the least number of perfect square numbers (for example, 1, 4, 9, 16, ...) which twoPointers.sum to n.
* For example, given n = 12, return 3 because 12 = 4 + 4 + 4; given n = 13, return 2 because 13 = 4 + 9.
*
* */
public class PerfectSquares {

    public class Square {
        int val;
        int dist;

        public Square(int val, int dist) {
            this.val = val;
            this.dist = dist;
        }
    }

    public int numSquares(int n) {
        Queue<Square> queue = new LinkedList<Square>();
        queue.offer(new Square(n, 0));

        while ( ! queue.isEmpty()) {
            Square element = queue.poll();
            int d = element.dist;
            int val = element.val;
            int sq = (int)Math.sqrt((double)val);
            for (int i = sq; i > 0 ; i--) {
                int sqrt = i * i;
                int restVl = val - sqrt;
                if (restVl == 0) return (d + 1); // BFS property
                else if (restVl > 0) {
                    queue.offer(new Square(restVl, (d+1)));
                }
            }
        }
        return 0;
    }

    public int numSquares_2(int n) {
        int[] minNum = new int[n+1];
        int len = (int) Math.sqrt((double) n);

        for (int i = 1; i <= len; i ++) {
            minNum[i * i] = 1;
        }
        for (int i = 2; i <= n ; i ++) {
            int mid = (int) Math.sqrt((double) i);
            int minCount = n;
            for (int j = 1; j <= mid; j++) {
                int val = j * j;
                minCount = Math.min(minCount, (minNum[val]+minNum[i - val]));
            }
            minNum[i] = minCount;
        }
        return minNum[n];
    }

    public static void main(String[] args) {
        PerfectSquares squares = new PerfectSquares();
        System.out.println(squares.numSquares(13));
    }
}
