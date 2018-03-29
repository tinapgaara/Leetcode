package facebook.array;
/*
*
* 第一题：给俩排好序的数组，找出两个数组的最小差，比如 [3 5 7] [5 9 12], 最小差是5-5=0. 双指针从头到尾扫一遍，O(m+n) 秒了
* */
public class MinDiffTwoSortedArray {
    public int minDiff(int[] nums1, int[] nums2) {
        int i1 = 0;
        int i2 = 0;
        int minDiff = Integer.MAX_VALUE;
        while(i1 < nums1.length && i2 < nums2.length) {
            int num1 = nums1[i1];
            int num2 = nums2[i2];
            if (num1 < num2) {
                minDiff = Math.min(minDiff, num2 - num1);
                i1 ++;
            }
            else if (num1 > num2) {
                minDiff = Math.min(minDiff, num1 - num2);
                i2 ++;
            }
            else {
                return 0;
            }
        }
        while(i1 < nums1.length) {
            minDiff = Math.min(Math.abs(nums1[i1] - nums2[nums2.length - 1]), minDiff);
            i1 ++;
        }
        while(i2 < nums2.length) {
            minDiff = Math.min(Math.abs(nums2[i2] - nums1[nums1.length - 1]), minDiff);
            i2 ++;
        }
        return minDiff;
    }
}
