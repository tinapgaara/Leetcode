package stack;

/**
 * Created by yingtan on 1/20/18.
 *
 * 496. Next Greater Element I
 DescriptionHintsSubmissionsDiscussSolution
 DiscussPick One
 You are given two arrays (without duplicates) nums1 and nums2 where nums1’s elements are subset of nums2. Find all the next greater numbers for nums1's elements in the corresponding places of nums2.

 The Next Greater Number of a number x in nums1 is the first greater number to its right in nums2. If it does not exist, output -1 for this number.

 Example 1:
 Input: nums1 = [4,1,2], nums2 = [1,3,4,2].
 Output: [-1,3,-1]
 Explanation:
 For number 4 in the first array, you cannot find the next greater number for it in the second array, so output -1.
 For number 1 in the first array, the next greater number for it in the second array is 3.
 For number 2 in the first array, there is no next greater number for it in the second array, so output -1.
 Example 2:
 Input: nums1 = [2,4], nums2 = [1,2,3,4].
 Output: [3,-1]
 Explanation:
 For number 2 in the first array, the next greater number for it in the second array is 3.
 For number 4 in the first array, there is no next greater number for it in the second array, so output -1.
 */
import java.util.*;
public class NextGreaterElementI {

    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        int[] res = new int[nums1.length];
        if (nums1 == null || nums2 == null || nums1.length == 0 || nums2.length == 0) {
            return res;
        }
        Map<Integer, Integer> nextElement = new HashMap<>();
        Stack<Integer> stack = new Stack<>();
        for (int i = 0 ; i < nums2.length; i ++) {
            int num = nums2[i];
            if (stack.isEmpty()) {
                stack.push(i);
            }
            else {
                while (! stack.isEmpty() && num > nums2[stack.peek()]) {
                    nextElement.put(nums2[stack.peek()], num);
                    stack.pop();
                }
                stack.push(i);
            }
        }
        for (int i = 0 ; i < nums1.length; i ++) {
            if (nextElement.containsKey(nums1[i])) {
                res[i] = nextElement.get(nums1[i]);
            }
            else {
                res[i] = -1;
            }
        }
        return res;
    }

    public int[] nextGreaterElements(int[] nums) {
        int[] res = new int[nums.length];
        Arrays.fill(res, -1);
        if (nums == null || nums.length == 0) return res;
        Stack<Integer> stack = new Stack<>();
        int circuLen = nums.length + nums.length; // important !!!!
        for (int i = 0 ; i < circuLen ; i ++) {
            int index = i % nums.length; // important !!!!
            if (stack.isEmpty()) {
                stack.push(index);
            }
            else {
                while(! stack.isEmpty() && nums[index] > nums[stack.peek()]) {
                    res[stack.peek()] = nums[index];
                    stack.pop();
                }
                stack.push(index);
            }
        }
        return res;
    }
}
