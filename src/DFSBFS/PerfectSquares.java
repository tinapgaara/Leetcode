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
* Given a positive integer n, find the least number of perfect square numbers (for example, 1, 4, 9, 16, ...) which sum to n.
* For example, given n = 12, return 3 because 12 = 4 + 4 + 4; given n = 13, return 2 because 13 = 4 + 9.
*
* */
public class PerfectSquares {

    public int numSquares(int n) {
        Queue<Integer> queue = new LinkedList<Integer>();
        Queue<Integer> dist = new LinkedList<Integer>();
        dist.offer(0);
        queue.offer(n);

        while ( ! queue.isEmpty()) {
            int element = queue.poll();
            int d = dist.poll();
            int sq = (int)Math.sqrt((double)element);
            for (int i = sq; i > 0 ; i--) {
                int sqrt = i * i;
                int restVl = element - sqrt;
                if (restVl == 0) return (d + 1);
                else if (restVl > 0) {
                    queue.offer(restVl);
                    dist.offer(d + 1);
                }
            }
        }
        return 0;
    }

    public static void main(String[] args) {
        PerfectSquares squares = new PerfectSquares();
        System.out.println(squares.numSquares(13));
    }
}
