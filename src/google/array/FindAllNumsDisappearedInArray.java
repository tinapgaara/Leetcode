package google.array;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yingtan on 9/15/17.
 *
 * 448. Find All Numbers Disappeared in an Array
 DescriptionHintsSubmissionsDiscussSolution
 Discuss Pick One
 Given an array of integers where 1 ≤ a[i] ≤ n (n = size of array), some elements appear twice and others appear once.

 Find all the elements of [1, n] inclusive that do not appear in this array.

 Could you do it without extra space and in O(n) runtime? You may assume the returned list does not count as extra space.

 Example:

 Input:
 [4,3,2,7,8,2,3,1]

 Output:
 [5,6]
 */
public class FindAllNumsDisappearedInArray {

    public List<Integer> findDisappearedNumbers(int[] nums) {
        // swap
        List<Integer> res = new ArrayList<Integer>();
        if (nums == null || nums.length == 0)  {
            return res;
        }

        for (int i = 0 ; i < nums.length; i ++) {
            int num = nums[i];
            int pos = num - 1;
            while (pos >= 0 && pos < nums.length && nums[pos] != num) {
                int prevNum = nums[pos];
                nums[pos] = num;
                pos = prevNum - 1;
                num = prevNum;
            }
        }

        for (int i = 0 ; i < nums.length; i ++) {
            if (nums[i] != i + 1) {
                res.add(i+1);
            }
        }
        return res;
    }
}
