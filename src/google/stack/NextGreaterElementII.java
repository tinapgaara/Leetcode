package google.stack;

import java.util.Arrays;
import java.util.Stack;

/**
 * Created by yingtan on 9/15/17.
 *
 * 503. Next Greater Element II
 DescriptionHintsSubmissionsDiscussSolution
 Discuss Pick One
 Given a circular array (the next element of the last element is the first element of the array), print the Next Greater Number for every element. The Next Greater Number of a number x is the first greater number to its traversing-order next in the array, which means you could search circularly to find its next greater number. If it doesn't exist, output -1 for this number.

 Example 1:
 Input: [1,2,1]
 Output: [2,-1,2]
 Explanation: The first 1's next greater number is 2;
 The number 2 can't find next greater number;
 The second 1's next greater number needs to search circularly, which is also 2.
 */
public class NextGreaterElementII {
    public int[] nextGreaterElements(int[] nums) {
        int[] res = new int[nums.length];

        if(nums == null || nums.length == 0) return res;
        int len = nums.length;

        // Sol 1:  simpler slower
        /*
        for (int i = 0 ; i < nums.length; i ++) {
            res[i] = -1;
            for (int j = i + 1; j < i + len; j ++) {
                if (nums[j % len] > nums[i]) {
                    res[i] = nums[j % len];
                    break;
                }
            }
        }
        */

        // Sol2  : using stack to record indexs of numbers, when pushing element is larger than top elemeents, pop stack elements and mark this element's next greater element equals to this pushing element
        // need to loop 2*len
        Stack<Integer> stack = new Stack<Integer>();
        Arrays.fill(res, -1);
        for(int i = 0 ; i < 2 * len; i ++) {
            //int index  = i % len;
            int num = nums[i % len];

            while(! stack.isEmpty() && nums[stack.peek()] < num) {
                // pop stack
                int prevIndex = stack.pop();
                res[prevIndex] = num;
            }
            if (i < len) stack.push(i);
        }
        return res;
    }
}
