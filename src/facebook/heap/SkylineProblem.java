package facebook.heap;

import java.util.*;

/**
 * Created by yingtan on 5/15/17.
 *
 * 218. The Skyline problem
 */

public class SkylineProblem {
    public List<int[]> getSkyline(int[][] buildings) {
        List<int[]> res = new ArrayList<>();
        if (buildings == null || buildings.length == 0) {
            return res;
        }
        List<int[]> height = new ArrayList<>();
        for (int[] b : buildings) {
            // important !!! when add height, the height is minus. This related to the sort function:
            height.add(new int[]{b[0], -b[2]});
            // when remove height, height >0.
            height.add(new int[]{b[1], b[2]});
        }

        // smaller one is at front
        Collections.sort(height, (a, b) -> {
            if (a[0] != b[0]) return a[0]-b[0];
            // important !! when x is the same, put the lower height one firstly beause the <0 height is incoming height
            else return a[1]-b[1];
        });
        // larger one is at front
        Queue<Integer> queue = new PriorityQueue<Integer>((a, b) -> (b - a));
        // Important !!! when queue is empty, return 0
        queue.offer(0);
        int prev = 0;
        for (int[] h : height) {
            int index = h[0];
            int hg = h[1];
            // add a height
            if (hg < 0) queue.offer(-1 * hg);
            // remove a height
            else queue.remove(hg);
            int cur = queue.peek();
            // the prev stores the largest height. if peek == prev then this means the new added does not change the largest number in queue thus this height is not largest one, in this case, we do not need to store this
            if (cur != prev) {
                res.add(new int[]{index, cur});
                prev = cur;
            }
        }
        return res;
    }



    public static void main(String[] args) {
        SkylineProblem ob = new SkylineProblem();
        int[][] buildings = new int[][]{{0,2,3},{2,5,3}};
        ob.getSkyline(buildings);
    }
}
