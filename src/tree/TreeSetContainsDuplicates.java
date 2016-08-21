package tree;

import java.util.HashMap;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 * Created by yingtan on 10/14/15.
 */
/*
*  Leetcode: Contains Duplicate III
*
*  Given an array of integers, find out whether there are two distinct indices i and j in the array such that the difference
*  between nums[i] and nums[j] is at most t and the difference between i and j is at most k
*
* TreeSet.subset (lowerNum, highNum): return the set's elements whose value is larger than lowerNum and smaller than highNum
*  |num[i] - num[j]| <= t:
*   -t <= num[i]-num[j] <= t
*   num[i] - t <= num[j] <= t + num[i]
*
*  i=0
* [1   2   4   5]  subset(num[i]-t, num[i] + t) not null return true, add(1) to set
*
*
*     i=1
* [1   2   4   5]  subset(num[i]-t, num[i] + t), since i = 1 , i < k, if subset != null, should return true, add(2) to set
*
*         i=2
* [1   2   4   5]  i=2 > k, because we need to find the elements' indexs between k,
* so, 1 will never be returned in subset, so remove 1: set.remove[i-k]
* So, subset will find the suitable numberss after 1.
*
*             i=3
* [   2   4   5]  i=3 > k
* 2 will never be returned in subset, so remove 2: set.remove[i-k]
* So, subset will find the suitable number after 2
*
*
* Main idea:
* 1) maintain a window, to record if current pointer >= k
* 2) use subset to extract elements' values > nums[i] - t && < nums[i] + t
*
*
*
* */
public class TreeSetContainsDuplicates {

    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        if (k < 1 || t < 0 || nums == null || nums.length < 2) return false;
        SortedSet<Long> set = new TreeSet<Long>();
        for (int i = 0 ; i < nums.length; i ++) {
            SortedSet<Long> partSet = set.subSet((long)nums[i] - t, (long)nums[i] + t + 1);
            if (!partSet.isEmpty()) return true;
            if (i >= k) {
                set.remove((long)nums[i-k]);
            }
            set.add((long)nums[i]);
        }
        return false;
    }
}
