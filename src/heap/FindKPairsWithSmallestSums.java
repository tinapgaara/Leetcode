package heap;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

/**
 * Created by yingtan on 4/16/18.
 */
public class FindKPairsWithSmallestSums {
    public List<int[]> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        List<int[]> res = new ArrayList<>();
        if (nums1 == null || nums1.length == 0 || nums2 == null || nums2.length == 0) return res;
        PointComparator comp = new PointComparator();
        PriorityQueue<int[]> queue = new PriorityQueue<>(comp);
        for (int i = 0 ; i < nums2.length; i  ++) {
            queue.offer(new int[]{0, i, nums1[0] + nums2[i]});
        }
        while(! queue.isEmpty() && k > 0) {
            int[] p = queue.poll();
            int i1 = p[0];
            int i2 = p[1];
            int sum = p[2];
            res.add(new int[]{i1, i2});
            if (i1 + 1 < nums1.length) {
                queue.offer(new int[]{i1 + 1, i2});
            }
            k --;
        }
        return res;
    }
    public class PointComparator implements Comparator<int[]> {
        public int compare(int[] p1, int[] p2) {
            return p1[2] - p2[2];
        }
    }
}
