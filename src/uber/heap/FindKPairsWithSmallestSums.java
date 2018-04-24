package uber.heap;
import java.util.*;
public class FindKPairsWithSmallestSums {
    public List<int[]> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        List<int[]> res =  new ArrayList<>();
        if (nums1 == null || nums1.length == 0 || nums2 == null || nums2.length == 0) return res;
        PointComparator comp = new PointComparator();
        PriorityQueue<int[]> queue = new PriorityQueue<>(comp);
        int count = 0;
        // very important !!!! here !!!!
        for (int i = 0; i < nums1.length; i ++) {
            queue.offer(new int[]{i, 0, nums1[i] + nums2[0]});
        }
        while(count < k && ! queue.isEmpty()) {
            int[] p = queue.poll();
            int i = p[0];
            int j = p[1];

            res.add(new int[]{nums1[i], nums2[j]});
            count ++;
            if (j + 1 < nums2.length) {
                queue.offer(new int[]{i, j+ 1, nums1[i] + nums2[j+1]});
            }
        }
        return res;
    }
    public class PointComparator implements Comparator<int[]> {
        public int compare(int[] p1, int[] p2) {
            return p1[2] - p2[2];
        }
    }
}
