package binarysearch;

/**
 * Created by yingtan on 1/21/18.
 * 442. Find All Duplicates in an Array
 DescriptionHintsSubmissionsDiscussSolution
 DiscussPick One
 Given an array of integers, 1 ≤ a[i] ≤ n (n = size of array), some elements appear twice and others appear once.

 Find all the elements that appear twice in this array.

 Could you do it without extra space and in O(n) runtime?

 Example:
 Input:
 [4,3,2,7,8,2,3,1]

 Output:
 [2,3]
 */
import java.util.*;
public class FindAllDuplicatedInArray {

    public List<Integer> findDuplicates(int[] nums) {
        List<Integer> res = new ArrayList<>();
        if (nums == null || nums.length == 0) return res;
        // idea: put each element to its correct place, after that, the left wrong placed elements are dup numbers
        for (int i = 0 ; i < nums.length; i ++) {
            while(nums[i] -1 >= 0 && nums[i] - 1 < nums.length
                    && nums[nums[i]-1] != nums[i]) {// nums[i] is at wrong place, should be moved to index (nums[i] - 1)
                int supposedPos = nums[i] - 1;
                int tmp = nums[i];
                nums[i] = nums[supposedPos];
                nums[supposedPos] = tmp;
            }
        }
        for (int i = 0 ; i < nums.length; i ++) {
            if (i+1 != nums[i]) {
                res.add(nums[i]);
            }
        }
        return res;
    }
}
