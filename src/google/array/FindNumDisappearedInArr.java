package google.array;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yingtan on 1/16/17.
 *
 448. Find All Numbers Disappeared in an Array   Add to List QuestionEditorial Solution  My Submissions
 Total Accepted: 19752
 Total Submissions: 35752
 Difficulty: Easy
 Contributors: yuhaowang001
 Given an array of integers where 1 ≤ a[i] ≤ n (n = size of array), some elements appear twice and others appear once.

 Find all the elements of [1, n] inclusive that do not appear in this array.

 Could you do it without extra space and in O(n) runtime? You may assume the returned list does not count as extra space.

 Example:

 Input:
 [4,3,2,7,8,2,3,1]

 Output:
 [5,6]



 Sol:

 Simple Java In-place sort solution
 The idea is simple, if nums[i] != i + 1 and nums[i] != nums[nums[i] - 1], then we swap nums[i] with nums[nums[i] - 1], for example, nums[0] = 4 and nums[3] = 7, then we swap nums[0] with nums[3]. So In the end the array will be sorted and if nums[i] != i + 1, then i + 1 is missing.
 The example run as follows

 [4,3,2,7,8,2,3,1]
 [7,3,2,4,8,2,3,1]
 [3,3,2,4,8,2,7,1]
 [2,3,3,4,8,2,7,1]
 [3,2,3,4,8,2,7,1]
 [3,2,3,4,1,2,7,8]
 [1,2,3,4,3,2,7,8]
 Since every swap we put at least one number to its correct position, the time is O(n)

 public class Solution {
     public List<Integer> findDisappearedNumbers(int[] nums) {
         for (int i = 0; i < nums.length; i++) {
             while (nums[i] != i + 1 && nums[i] != nums[nums[i] - 1]) {
             int tmp = nums[i];
             nums[i] = nums[tmp - 1];
             nums[tmp - 1] = tmp;
         }
     }
     List<Integer> res = new ArrayList<Integer>();
         for (int i = 0; i < nums.length; i++) {
             if (nums[i] != i + 1) {
                res.add(i + 1);
             }
         }
        return res;
     }
 }
 */

public class FindNumDisappearedInArr {

    public List<Integer> findDisappearedNumbers(int[] nums) {
        int size = nums.length;
        List<Integer> res = new ArrayList<Integer>();

        // in place sorting
        // ele: 1 2 3 4 _ _ 7 8
        // pos :0 1 2 3 4 5 6 7
        // nums[i] = 4, then 4 need to be put under pos 3th

        for (int i = 0 ; i < size; i ++) {
            while ( (nums[i] != i + 1) && (nums[i] != nums[nums[i] - 1]) ) {
                // swap
                int tmp = nums[i];
                nums[i] = nums[tmp - 1];
                nums[tmp - 1] = tmp;
            }
        }

        for (int i = 0 ; i < size; i ++) {
            int num = nums[i];
            if (num != i + 1) {
                res.add(i + 1);
            }
        }
        return res;
    }
}
