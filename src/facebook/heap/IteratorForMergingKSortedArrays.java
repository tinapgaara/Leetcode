package facebook.heap;

import java.util.*;

public class IteratorForMergingKSortedArrays{
    public class IteratorSortedArray implements Iterator<Integer> {
        List<int[]> nums;
        PriorityQueue<int[]> queue;
        public IteratorSortedArray(List<int[]> nums) {
            this.nums = nums;
            PointComparator comp = new PointComparator();
            queue = new PriorityQueue<int[]>(comp);
            for (int i = 0 ; i < nums.size(); i ++) {
                queue.offer(new int[]{i, 0, nums.get(i)[0]});
            }
        }
        public Integer next() {
            if (hasNext()) {
                int[] p = queue.poll();
                int res = p[2];
                int listIndex = p[0];
                int numIndex = p[1];
                if (numIndex + 1 < nums.get(listIndex).length) {
                    queue.offer(new int[]{listIndex, numIndex + 1, nums.get(listIndex)[numIndex + 1]});
                }
                return res;
            }
            else {
                return -1;
            }
        }
        public boolean hasNext() {
            return ! queue.isEmpty();
        }
    }

    public class PointComparator implements Comparator<int[]> {
        public int compare(int[] p1, int[] p2) {
            return p1[2] - p2[2];
        }
    }
}
