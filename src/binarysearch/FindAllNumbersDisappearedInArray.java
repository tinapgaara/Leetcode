package binarysearch;

/**
 * Created by yingtan on 1/21/18.
 *
 * 448. Find All Numbers Disappeared in an Array
 DescriptionHintsSubmissionsDiscussSolution
 DiscussPick One
 Given an array of integers where 1 ≤ a[i] ≤ n (n = size of array), some elements appear twice and others appear once.

 Find all the elements of [1, n] inclusive that do not appear in this array.

 Could you do it without extra space and in O(n) runtime? You may assume the returned list does not count as extra space.

 Example:

 Input:
 [4,3,2,7,8,2,3,1]

 Output:
 [5,6]
 */
import java.util.*;
public class FindAllNumbersDisappearedInArray {

    public List<Integer> findDisappearedNumbers(int[] nums) {
        List<Integer> res = new ArrayList<>();
        if (nums == null || nums.length == 0) return res;
        for (int i = 0 ; i < nums.length; i ++) {
            while(nums[i] - 1 >= 0 && nums[i] - 1 < nums.length &&
                    nums[nums[i]-1] != nums[i]) {
                int supposedPos = nums[i] - 1;
                int tmp = nums[i];
                nums[i] = nums[supposedPos];
                nums[supposedPos] = tmp;
            }
        }
        for (int i = 0; i < nums.length; i ++) {
            if (nums[i] != (i+1)) {
                // this is missing
                res.add(i + 1);
            }
        }
        return res;
    }
    public static void main(String[] args) {
        FindAllNumbersDisappearedInArray ob = new FindAllNumbersDisappearedInArray();
        int[] nums = {4,3,2,7,8,2,3,1};
        System.out.println(ob.findDisappearedNumbers(nums));
    }
}
