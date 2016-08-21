package google.heap;

import java.util.*;

/**
 * Created by yingtan on 12/22/15.
 */
public class SkylineProblem {

    public List<int[]> getSkyline(int[][] buildings) {

        List<int[]> res = new ArrayList<>();
        if ((buildings == null) || (buildings.length == 0)) return res;

        List<int[]> heights = new ArrayList<>();

        for (int[] building : buildings) {
            int low = building[0];
            int high = building[1];
            int height = building[2];

            heights.add(new int[]{low, -1 * height});
            heights.add(new int[]{high, height});
        }

        BuildComparator comparator = new BuildComparator();
        QueueComparator comparator_2 = new QueueComparator();
        Collections.sort(heights, comparator);
        PriorityQueue<Integer> queue = new PriorityQueue<Integer>(comparator_2);
        queue.offer(0);
        int prevHeight = 0;
        for (int[] height : heights) {
            if (height[1] < 0) {
                queue.offer(-1 * height[1]);
            }
            else {
                queue.remove(height[1]);
            }

            int curHeight = queue.peek();
            if (prevHeight != curHeight) {
                res.add(new int[]{height[0], curHeight});
                prevHeight = curHeight;
            }
        }
        return res;
    }

    public class BuildComparator implements Comparator<int[]> {
        public int compare(int[] i1, int[] i2) {
            if (i1[0] < i2[0]) return -1;
            else if (i1[0] > i2[0]) return 1;
            else {
                if (i1[1] < i2[1]) return -1;
                else if (i1[1] > i2[1]) return 1;
                else return 0;
            }
        }
    }

    public class QueueComparator implements Comparator<Integer> {
        public int compare(Integer i1, Integer i2) {
            if (i1 > i2) return -1;
            else if (i1 < i2) return 1;
            else return 0;
        }
    }
}
