package facebook.array;

/**
 * Created by yingtan on 2/10/18.
 *
 * 349. Intersection of Two Arrays
 DescriptionHintsSubmissionsDiscussSolution
 Pick One
 Given two arrays, write a function to compute their intersection.

 Example:
 Given nums1 = [1, 2, 2, 1], nums2 = [2, 2], return [2].

 Note:
 Each element in the result must be unique.
 The result can be in any order.

 */
import java.util.*;
public class IntersectionOfTwoArrays {
    // Solution 1: using hashset : o(n)
    public int[] intersection_hashset(int[] nums1, int[] nums2) {
        if (nums1 == null || nums2 == null) return null;
        Set<Integer> set1 = new HashSet<>();
        for (int num : nums1) {
            set1.add(num);
        }
        Set<Integer> intersect = new HashSet<>();
        for (int num : nums2) {
            if (set1.contains(num)) {
                intersect.add(num);
            }
        }
        int[] res = new int[intersect.size()];
        int i = 0;
        for (int num : intersect) {
            res[i] = num;
            i ++;
        }
        return res;
    }
    // Solution 2: two pointer + sort : nlogn
    public int[] intersection_twoPointer(int[] nums1, int[] nums2) {
        if (nums1 == null || nums2 == null) return null;
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        int i1 = 0;
        int i2 = 0;
        Set<Integer> intersect = new HashSet<>();
        while(i1 < nums1.length && i2 < nums2.length) {
            int num1 = nums1[i1];
            int num2 = nums2[i2];
            if (num1 == num2) {
                intersect.add(num1);
                i1 ++;
                i2 ++;
            }
            else if (num1 < num2) {
                i1 ++;
            }
            else {
                i2 ++;
            }
        }
        int[] res = new int[intersect.size()];
        int i = 0;
        for (int num : intersect) {
            res[i] = num;
            i ++;
        }
        return res;
    }
    // Solution 3:  Binary Search : o(nlogn)
    public int[] intersection_binarySearch(int[] nums1, int[] nums2) {
        if (nums1 == null || nums2 == null) return null;
        Arrays.sort(nums2);
        Set<Integer> intersect = new HashSet<>();
        for (int num : nums1) {
            if (Arrays.binarySearch(nums2, num) >= 0) {
                intersect.add(num);
            }
        }
        int[] res = new int[intersect.size()];
        int i = 0;
        for (int num : intersect) {
            res[i] = num;
            i ++;
        }
        return res;
    }

}
