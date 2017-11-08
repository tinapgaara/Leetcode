package facebook;

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
            height.add(new int[]{b[0], -b[2]});
            height.add(new int[]{b[1], b[2]});
        }

        // smaller one is at front
        Collections.sort(height, (a, b) -> {
            if (a[0] != b[0]) return a[0]-b[0];
            else return a[1]-b[1];
        });

        // larger one is at front
        Queue<Integer> queue = new PriorityQueue<Integer>((a, b) -> (b - a));
        queue.offer(0);
        int prev = 0;
        for (int[] h : height) {
            int index = h[0];
            int hg = h[1];
            if (hg < 0) queue.offer(-1 * hg);
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
        int[][] buildings = new int[][]{{2,9,10},{3,7,15},{5,12,12},{15,20,10},{19,24,8}};
        ob.getSkyline(buildings);
    }
}
