package google.greedy;

import java.util.Stack;

/**
 * Created by yingtan on 9/6/17.
 *
 * 321. Create Maximum Number
 DescriptionHintsSubmissionsDiscussSolution
 Discuss Pick One
 Given two arrays of length m and n with digits 0-9 representing two numbers. Create the maximum number of length k <= m + n from digits of the two. The relative order of the digits from the same array must be preserved. Return an array of the k digits. You should try to optimize your time and space complexity.

 Example 1:
 nums1 = [3, 4, 6, 5]
 nums2 = [9, 1, 2, 5, 8, 3]
 k = 5
 return [9, 8, 6, 5, 3]

 Example 2:
 nums1 = [6, 7]
 nums2 = [6, 0, 4]
 k = 5
 return [6, 7, 6, 0, 4]

 Example 3:
 nums1 = [3, 9]
 nums2 = [8, 9]
 k = 3
 return [9, 8, 9]


 */
public class CreateMaximumNumber {

    public int[] maxNumber(int[] nums1, int[] nums2, int k) {
        int[] res = new int[k];
        if (nums1 == null && nums2 == null) return res;
        else if (nums1 == null && nums2 != null && nums2.length > 0) {
            return maxNumberInOneArray(nums2, k);
        }
        else if (nums1 != null && nums2 == null && nums1.length > 0) {
            return maxNumberInOneArray(nums1, k);
        }
        else {
            // important !!! i is from  Math.max(0, k - nums2.length) - Math.min(nums1.length, k)
            for (int i = Math.max(0, k - nums2.length) ; i <= Math.min(nums1.length, k) ; i ++) {
                int[] max1 = maxNumberInOneArray(nums1, i);
                int[] max2 = maxNumberInOneArray(nums2, k - i);
                int[] candidate = mergeMax(max1, max2, k);

                // candidate is greater than res
                if (greater(candidate, 0, res, 0)) {
                    res = candidate;
                }
            }

        }
        return res;
    }

    /*
    Easy Version No. 1

    Given one array of length n, create the maximum number of length k.

    The solution to this problem is Greedy with the help of stack. The recipe is as following

    Initialize a empty stack
    Loop through the array nums
        pop the top of stack if it is smaller than nums[i] until
        1) stack is empty
        2) the digits left is not enough to fill the stack to size k
    if stack size < k push nums[i]
    Return stack

    Since the stack length is known to be k, it is very easy to use an array to simulate the stack.
    The time complexity is O(n) since each element is at most been pushed and popped once.
    */
    public int[] maxNumberInOneArray(int[] nums, int k) {

        if (k > nums.length) {
            k = nums.length;
        }
        // maxLen = k
        Stack<Integer> stack = new Stack<Integer>();

        for (int i = 0; i < nums.length; i ++) {
            // more than k elements
            // important !!! (stack.size() + (left nums) > k ) so we still can pop up some numbers
            while ( (stack.size() + (nums.length - i) > k) && (!stack.isEmpty()) && (stack.peek() < nums[i])) {
                stack.pop();
            }
            if (stack.size() < k) {
                stack.push(nums[i]);
            }
        }
        int[] res = new int[k];
        int i = k - 1;
        while (! stack.isEmpty()) {
            res[i] = stack.pop();
            i --;
        }
        return res;
    }
    /*
    Easy Version No. 2

    Given two array of length m and n, create maximum number of length k = m + n.

    OK, this version is a lot closer to our original problem with the exception that we will use all the digits we have.

    Still, for this version, Greedy is the first thing come to mind. We have k decisions to make, each time will just need to decide ans[i] is from which of the two. It seems obvious, we should always choose the larger one right? This is correct, but the problem is what should we do if they are equal?

    This is not so obvious. The correct answer is we need to see what behind the two to decide. For example,

    nums1 = [6, 7]
    nums2 = [6, 0, 4]
    k = 5
    ans = [6, 7, 6, 0, 4]

    We decide to choose the 6 from nums1 at step 1, because 7 > 0. What if they are equal again? We continue to look the next digit until they are not equal. If all digits are equal then choose any one is ok. The procedure is like the merge in a merge sort. However due to the “look next until not equal”, the time complexity is O(nm).
    */
    public int[] mergeMax(int[] max1, int[] max2, int k) {
        int i1 = 0, i2 = 0, i = 0;
        int[] res = new int[k];
        while (i1 < max1.length || i2 < max2.length) {
            if (greater(max1, i1, max2, i2)) {
                res[i] = max1[i1];
                i ++;
                i1 ++;
            }
            else {
                res[i] = max2[i2];
                i ++;
                i2 ++;
            }
        }
        return res;
    }

    public boolean greater(int[] max1, int i1, int[] max2, int i2) {
        while(i1 < max1.length && i2 < max2.length) {
            if (max1[i1] > max2[i2]) return true;
            else if (max1[i1] < max2[i2]) return false;
            i1 ++;
            i2 ++;
        }
        if (i1 == max1.length) return false;
        else return true;
    }

    public static void main(String[] args) {
        CreateMaximumNumber ob = new CreateMaximumNumber();
        int[] nums = new int[]{6,7};
        int[] nums2 = new int[]{6,0,4};
        ob.maxNumber(nums, nums2, 5);
    }
}
