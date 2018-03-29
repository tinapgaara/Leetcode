package facebook.hashmap;

/**
 * Created by yingtan on 2/8/18.
 *
 * 350. Intersection of Two Arrays II
 DescriptionHintsSubmissionsDiscussSolution
 DiscussPick One
 Given two arrays, write a function to compute their intersection.

 Example:
 Given nums1 = [1, 2, 2, 1], nums2 = [2, 2], return [2, 2].

 Note:
 Each element in the result should appear as many times as it shows in both arrays.
 The result can be in any order.
 Follow up:
 What if the given array is already sorted? How would you optimize your algorithm?
 What if nums1's size is small compared to nums2's size? Which algorithm is better?
 What if elements of nums2 are stored on disk, and the memory is limited such that you cannot load all elements into the memory at once?
 */
import java.util.*;
public class IntersectionOfTwoArraysII {
    public int[] intersect(int[] nums1, int[] nums2) {
        Map<Integer, Integer> num1Count = new HashMap<>();
        List<Integer> reslist = new ArrayList<>();
        for (int num : nums1) {
            if (num1Count.containsKey(num)) {
                num1Count.put(num, num1Count.get(num) + 1);
            }
            else {
                num1Count.put(num, 1);
            }
        }
        for (int num : nums2) {
            if (num1Count.containsKey(num) && num1Count.get(num) > 0) {
                // intersection
                reslist.add(num);
                num1Count.put(num, num1Count.get(num) - 1);
            }
        }
        int[] res = new int[reslist.size()];
        for (int i = 0 ; i < reslist.size(); i ++) {
            res[i] = reslist.get(i);
        }
        return res;
    }
}
