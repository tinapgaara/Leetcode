package tree;

import java.util.*;

/**
 * Created by yingtan on 12/10/17.
 *
 * 220. Contains Duplicate III
 DescriptionHintsSubmissionsDiscussSolution
 Discuss Pick One
 Given an array of integers, find out whether there are two distinct indices i and j in the array such that the absolute difference between nums[i] and nums[j] is at most t and the absolute difference between i and j is at most k.


 */
public class ContainsDuplicateIII {

    // more faster way: only maintain a treeset with values, make sure all elements in this treeset's indexs are
    // apart from the current j with distance <= k
    /*
    * [_ _ _ ]
    *  i   j
    *
    *  Java O(N lg K) solution
This problem requires to maintain a window of size k of the previous values that can be queried for value ranges.
The best data structure to do that is Binary Search Tree.
As a result maintaining the tree of size k will result in time complexity O(N lg K).
In order to check if there exists any value of range abs(nums[i] - nums[j])
to simple queries can be executed both of time complexity O(lg K)
    * */
    public boolean containsNearbyAlmostDuplicate_better(int[] nums, int k, int t) {
        if (nums == null || nums.length == 0 || k <= 0) {
            return false;
        }
        final TreeSet<Integer> values = new TreeSet<>();
        for (int ind = 0; ind < nums.length; ind++) {
            // nums[j] - t <= nums[i] <= nums[j] + t
            // max keys smaller than nums[ind] + t
            final Integer floor = values.floor(nums[ind] + t);
            // min keys larger than nums[ind] - t
            final Integer ceil = values.ceiling(nums[ind] - t);
            if ((floor != null && floor >= nums[ind])
                    || (ceil != null && ceil <= nums[ind])) {
                return true;
            }

            values.add(nums[ind]);
            if (ind >= k) {
                values.remove(nums[ind - k]);
            }
        }
        return false;
    }
    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        if (nums == null || nums.length == 0) return false;
        // nums[j] - t <= nums[i] <= nums[j] + t
        if (t < 0) return false;
        TreeMap<Long, Set<Integer>> valueToIndex = new TreeMap<>();
        for (int i = 0 ; i < nums.length ; i ++) {
            if (valueToIndex.containsKey((long)nums[i])) {
                valueToIndex.get((long)nums[i]).add(i);
            }
            else {
                Set<Integer> set = new HashSet<>();
                set.add(i);
                valueToIndex.put((long)nums[i], set);
            }
        }
        // j-k <= i <= j+k
        for (int j = 0 ; j < nums.length; j ++) {
            long min = (long)nums[j] - t;
            long max = (long)nums[j] + t;
            if (min > max) {
                long tmp = min;
                min = max;
                max = tmp;
            }
            Map<Long, Set<Integer>> submap = valueToIndex.subMap(min, true, max, true);
            for(Set<Integer> indexs : submap.values()) {
                for (Integer index : indexs) {
                    if (index != j && j-k <= index && index <= j+k) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {
        ContainsDuplicateIII ob = new ContainsDuplicateIII();
        int[] nums = {-1,2147483647};
        ob.containsNearbyAlmostDuplicate(nums, 1, 2147483647);
    }
}
