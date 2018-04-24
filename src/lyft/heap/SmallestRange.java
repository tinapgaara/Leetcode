package lyft.heap;

/**
 * Created by yingtan on 4/11/18.
 *
 * 632. Smallest Range
 DescriptionHintsSubmissionsDiscussSolution
 You have k lists of sorted integers in ascending order. Find the smallest range that includes at least one number from each of the k lists.

 We define the range [a,b] is smaller than range [c,d] if b-a < d-c or a < c if b-a == d-c.

 Example 1:
 Input:[[4,10,15,24,26], [0,9,12,20], [5,18,22,30]]
 Output: [20,24]
 Explanation:
 List 1: [4, 10, 15, 24,26], 24 is in range [20,24].
 List 2: [0, 9, 12, 20], 20 is in range [20,24].
 List 3: [5, 18, 22, 30], 22 is in range [20,24].
 Note:
 The given list may contain duplicates, so ascending order means >= here.
 1 <= k <= 3500
 -105 <= value of elements <= 105.
 For Java users, please note that the input type has been changed to List<List<Integer>>. And after you reset the code template, you'll see this point.

 */
import java.util.*;
public class SmallestRange {
    public int[] smallestRange(List<List<Integer>> nums) {
        if (nums == null || nums.size() == 0) return null;
        int[] res = new int[2];
        PointComparator comp = new PointComparator();
        PriorityQueue<int[]> queue = new PriorityQueue<>(comp); // stores [i, j, value]: minheap: k
        int max = Integer.MIN_VALUE;
        for (int i = 0 ; i < nums.size(); i ++) {
            if (nums.get(i).size() > 0) {
                queue.offer(new int[]{i, 0, nums.get(i).get(0)});
                max = Math.max(max, nums.get(i).get(0));
            }
        }
        int minRange = Integer.MAX_VALUE;
        while(! queue.isEmpty()) {
            int[] p = queue.poll();
            // current the min one
            int i = p[0];
            int j = p[1];
            if (max - nums.get(i).get(j) < minRange) {
                minRange = max - nums.get(i).get(j);
                res[0] = nums.get(i).get(j);
                res[1] = max;
            }
            if (j + 1 < nums.get(i).size()) {
                queue.offer(new int[]{i, j + 1, nums.get(i).get(j + 1)});
                max = Math.max(max, nums.get(i).get(j + 1));
            }
            else {
                break; // Important !!!!
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
