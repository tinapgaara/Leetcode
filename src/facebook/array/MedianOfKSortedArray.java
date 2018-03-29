package facebook.array;
import java.util.*;
public class MedianOfKSortedArray {
    public int medianOfKSortedArrays(int[][] nums) {
        // k nums
        int k = nums.length;
        int n = nums[0].length;
        int len = n * k;
        PointComparator comp = new PointComparator();
        PriorityQueue<int[]> queue = new PriorityQueue<>(comp);
        for (int i = 0 ; i < k ; i ++) {
            queue.offer(new int[]{i, 0, nums[i][0]});
        }
        boolean even = false;
        if (n % 2 == 0) {
            even = true;
        }
        int mid = (len - 1) / 2;
        int count = 0;
        int prevmin = 0;
        while(! queue.isEmpty()) {
           int[] p = queue.poll();
           int i = p[0];
           int j = p[1];
           // min one
           if (count == mid) {
               if (! even) {
                   return nums[i][j];
               }
           }
           else if (count == mid + 1) {
               if (even) {
                   return (prevmin + nums[i][j]) / 2;
               }
           }
           // offer new element
            if (j < nums[i].length) {
               queue.offer(new int[]{i, j + 1, nums[i][j+1]});
            }
            count ++;
           prevmin = nums[i][j];
        }
        return -1;
    }
    public class PointComparator implements Comparator<int[]> {
        public int compare(int[] p1, int[] p2) {
            return p1[2] - p2[2];
        }
    }
    public double medianOfAnArray(int[] nums) {
        int mid = (nums.length - 1) / 2;
        if (nums.length % 2 != 0) {
            return (double)nums[mid];
        }
        else {
            return ((double)nums[mid] + (double)nums[mid + 1]) / (2.0) ;
        }
    }
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        if (nums1 == null || nums1.length == 0) {
            return medianOfAnArray(nums2);
        }
        else if (nums2 == null || nums2.length == 0) {
            return medianOfAnArray(nums1);
        }
        PointComparator comp = new PointComparator();
        PriorityQueue<int[]> queue = new PriorityQueue<>(comp);
        queue.offer(new int[]{0, 0, nums1[0]}); // 0-> array0
        queue.offer(new int[]{1, 0, nums2[0]});// 1-> array1
        int totallen = nums1.length + nums2.length;
        int mid = (totallen - 1) / 2;
        boolean even = false;
        if (totallen % 2 == 0) {
            even = true;
        }
        int count = 0;
        int prevnum = 0;
        while(! queue.isEmpty()) {
            int[] p = queue.poll();
            if (count == mid) {
                if (! even) {
                    return (double)p[2];
                }
            }
            if (count == mid + 1) {
                if (even) {
                    return ((double)p[2] + (double)prevnum) / (2.0);
                }
            }
            int arrayIndex = p[0];
            int index = p[1];
            if (arrayIndex == 0) {
                if (index + 1 < nums1.length) {
                    queue.offer(new int[]{arrayIndex, index + 1, nums1[index + 1]});
                }
            }
            else if (arrayIndex == 1) {
                if (index + 1 < nums2.length) {
                    queue.offer(new int[]{arrayIndex, index + 1, nums2[index + 1]});
                }
            }
            prevnum = p[2];
            count ++;
        }
        return -1;
    }

}
