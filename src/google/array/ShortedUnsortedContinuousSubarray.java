package google.array;

/**
 * Created by yingtan on 9/17/17.
 *
 * 581. Shortest Unsorted Continuous Subarray
 DescriptionHintsSubmissionsDiscussSolution
 Discuss Pick One
 Given an integer array, you need to find one continuous subarray that if you only sort this subarray in ascending order, then the whole array will be sorted in ascending order, too.

 You need to find the shortest such subarray and output its length.

 Example 1:
 Input: [2, 6, 4, 8, 10, 9, 15]
 Output: 5
 Explanation: You need to sort [6, 4, 8, 10, 9] in ascending order to make the whole array sorted in ascending order.
 Note:
 Then length of the input array is in range [1, 10,000].
 The input array may contain duplicates, so ascending order here means <=.
 */
public class ShortedUnsortedContinuousSubarray {

    public int findUnsortedSubarray(int[] nums) {
        if(nums == null || nums.length == 0) return 0;
        /*
        Sol 1:
        Approach #3 Using Sorting [Accepted]

Algorithm

Another very simple idea is as follows. We can sort a copy of the given array numsnums, say given by nums\_sortednums_sorted. Then, if we compare the elements of numsnums and nums\_sortednums_sorted, we can determine the leftmost and rightmost elements which mismatch. The subarray lying between them is, then, the required shorted unsorted subarray.

Java

Complexity Analysis

Time complexity : O(nlogn)O(nlogn). Sorting takes nlognnlogn time.

Space complexity : O(n)O(n). We are making copy of original array.
        int[] newNums = nums.clone();

        Arrays.sort(newNums);
        int start = 0;
        for (int i = 0 ; i < nums.length; i ++) {
            if (nums[i] != newNums[i]) {
                start = i;
                break;
            }
        }
        int end = 0;
        for (int i = nums.length - 1; i >= 0 ; i --) {
            if (nums[i] != newNums[i]) {
                end = i;
                break;
            }
        }
        if (start == 0 && end == 0) return 0;
        return (end-start + 1);
        */

        // Sol 3:
        /*
        * Approach #5 Without Using Extra Space [Accepted]:

Algorithm

The idea behind this method is that the correct position of the minimum element in the unsorted subarray helps to determine the required left boundary. Similarly, the correct position of the maximum element in the unsorted subarray helps to determine the required right boundary.

Thus, firstly we need to determine when the correctly sorted array goes wrong. We keep a track of this by observing rising slope starting from the beginning of the array. Whenever the slope falls, we know that the unsorted array has surely started. Thus, now we determine the minimum element found till the end of the array numsnums, given by minmin.

Similarly, we scan the array numsnums in the reverse order and when the slope becomes rising instead of falling, we start looking for the maximum element till we reach the beginning of the array, given by maxmax.

Then, we traverse over numsnums and determine the correct position of minmin and maxmax by comparing these elements with the other array elements. e.g. To determine the correct position of minmin, we know the initial portion of numsnums is already sorted. Thus, we need to find the first element which is just larger than minmin. Similarly, for maxmax's position, we need to find the first element which is just smaller than maxmax searching in numsnums backwards.

We can take this figure for reference again:
        * */
        int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
        boolean isUnsorted = false;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] < nums[i - 1]) // break the sorted rules, isUnsorted=true
                isUnsorted = true;
            if (isUnsorted)
                min = Math.min(min, nums[i]);//记录所有break sorted rules的元素的min值
        }
        isUnsorted = false;
        for (int i = nums.length - 2; i >= 0; i--) {
            if (nums[i] > nums[i + 1])// break the sorted rules, isUnsorted=true
                isUnsorted = true;
            if (isUnsorted)
                max = Math.max(max, nums[i]);//记录所有break sorted rules的元素的max值
        }
        int l, r;//找出min本应该放的位置l 和max本应该在sorted array里放的位置r
        for (l = 0; l < nums.length; l++) {
            if (min < nums[l])//从左往右第一个比min大的元素的位置就是min本应该放的位置
                break;
        }
        for (r = nums.length - 1; r >= 0; r--) {//从右往左第一个比max小的位置就是max本应该放的位置
            if (max > nums[r])
                break;
        }
        return r - l < 0 ? 0 : r - l + 1;
    }
}
