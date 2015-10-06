package sort;

/**
 * Created by yingtan on 10/5/15.
 */
/*
*
* time: O(digitsNum * (n + maxDigitNum))
* space; O(maxDigitNum)
*/
public class RadixSort {

    public int[] radixSort(int[] num, int maxDigitNum, int digitsNum) {
        int len = num.length;
        for (int i = digitsNum-1 ; i >= 0; i --) {
            num = countSort(num, maxDigitNum, i);
        }
        return num;
    }

    public int[] countSort(int[] nums, int maxDigitNum, int digitPos) {
        int[] count = new int[maxDigitNum + 1];
        for (int i = 0 ; i < nums.length; i ++) {
            int num = nums[i];
            int digitNum = Integer.parseInt((num+ "").charAt(digitPos)+"");
            count[digitNum]++;
        }
        for (int i = 1 ; i < count.length; i ++) {
            count[i] = count[i] + count[i-1];
        }
        int[] newNum = new int[nums.length];
        for (int i = nums.length - 1; i >= 0 ; i --) {
            int num = nums[i];
            int digitNum = Integer.parseInt((num+ "").charAt(digitPos)+"");
            int numberBeforeDigits = count[digitNum];
            newNum[numberBeforeDigits-1] = num;
            count[digitNum] --;
        }
        return newNum;
    }

    /*
    *
    * Maximum Gap My Submissions Question Solution
    *
        Given an unsorted array, find the maximum difference between the successive elements in its sorted form.

        Try to solve it in linear time/space.

        Return 0 if the array contains less than 2 elements.

        You may assume all elements in the array are non-negative integers and fit in the 32-bit signed integer range.
    *
    * */

    public int maximumGap(int[] nums) {

        for (int i = 0; i < 32; i ++) {
            nums = radixSortGap(nums, i, 1);
        }
        int max = 0;
        int prev = 0;
        int cur = 1;
        while (cur < nums.length) {
            max = Math.max((nums[cur] - nums[prev]), max);
            prev ++;
            cur ++;
        }
        return max;
    }
    // time: O(n) , space: O(n)
    public int[] radixSortGap(int[] nums, int digitIndex, int maxDigitNum) {
        int[] count = new int[maxDigitNum + 1];
        for (int i = 0 ; i < nums.length; i ++) {
            int digit = (nums[i] >> digitIndex) & 1;
            count[digit] ++;
        }
        for (int i = 1 ;i < count.length; i ++) {
            count[i] = count[i] + count[i-1];
        }
        int[] res = new int[nums.length];
        for (int i = nums.length-1; i >= 0 ; i --) {
            int digit = (nums[i] >> digitIndex) & 1;
            int countBeforeDigit = count[digit];
            res[countBeforeDigit - 1] = nums[i];
            count[digit] --;
        }
        return res;
    }

    public static void main(String[] args) {
        RadixSort ob = new RadixSort();
        int[] num = new int[]{329, 457, 657, 839, 436, 720, 355};
        num = ob.radixSort(num, 9, 3);
    }
}
