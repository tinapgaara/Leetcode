package facebook.sort;
import java.util.*;
public class KClosetPoints {
    public List<int[]> findKClosestPoints(List<int[]> points, int k, int[] target) {
        if (points == null || points.size() == 0) return points;
        return quickselect(points, k, target);
    }
    public List<int[]> quickselect(List<int[]> points, int k, int[] target) {
        // quicksort/ quickselect
        List<int[]> res = new ArrayList<>();
        int low = 0;
        int high = points.size() - 1;
        while(low < high) {
            int index = partition(points, target, low, high);
            if (index == k) {
                break;
            }
            else if (index < k) {
                low = index + 1;
            }
            else {
                high = index - 1;
            }
        }
        for (int i = 0 ; i < k; i ++) {
            res.add(points.get(i));
        }
        return res;
    }
    public int partition(List<int[]> points, int[] target, int low, int high) {
        // pick high as pivot number
        int[] pivot = points.get(high);
        int pivotIndex = low;
        // put all nums < pivot to the list left side, put all nums > pivot to the list right side
        for (int i = low; i < high; i ++) {
            if (distance(points.get(i), target) < distance(pivot, target)) {
                // smaller than target
                int[] tmp = points.get(pivotIndex);
                int[] cur = points.get(i);
                points.set(pivotIndex, cur);
                points.set(i, tmp);
                pivotIndex ++;
            }
        }
        return pivotIndex;
    }
    public int distance(int[] p, int[] target) {
        return Math.abs(p[0] - target[0]) * Math.abs(p[0] - target[0]) + Math.abs(p[1] - target[1]) * Math.abs(p[1] - target[1]);
    }
    // Solution 1 : simpler way
    public List<int[]> kcloset_heap(List<int[]> points, int k, int[] target) {
        PointComaparator comp = new PointComaparator(target);
        PriorityQueue<int[]> queue = new PriorityQueue<int[]>(comp);
        // max heap
        for (int[] p : points) {
            queue.offer(p);
            if (queue.size() > k) {
                queue.poll();
            }
        }
        List<int[]> res = new ArrayList<>();
        while(! queue.isEmpty()) {
            res.add(queue.poll()); // from farest to closet
        }
         Collections.reverse(res);
        return res;
    }
    public class PointComaparator implements Comparator<int[]> {
        int[] target;
        public PointComaparator(int[] target) {
            this.target = target;
        }
        public int compare(int[] p1, int[] p2) {
            return distance(p2) - distance(p1);
        }
        public int distance(int[] p) {
            return Math.abs(p[0] - target[0]) * Math.abs(p[0] - target[0]) + Math.abs(p[1] - target[1]) * Math.abs(p[1] - target[1]);
        }
    }
}
