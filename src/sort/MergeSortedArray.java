package sort;

/**
 * Created by yingtan on 12/12/17.
 *
 * 88. Merge Sorted Array
 DescriptionHintsSubmissionsDiscussSolution
 Discuss Pick One
 Given two sorted integer arrays nums1 and nums2, merge nums2 into nums1 as one sorted array.


 */
public class MergeSortedArray {

    public void merge(int[] nums1, int m, int[] nums2, int n) {
        if (nums1 == null || nums1.length == 0 || nums2 == null || nums2.length == 0) return;
        int i = m-1;
        int j = n-1;
        int writeIndex = m + n - 1;
        while(i >= 0 && j >= 0) {
            int num1 = nums1[i];
            int num2 = nums2[j];
            if (num1 < num2) {
                nums1[writeIndex] = num2;
                j --;
            }
            else {
                nums1[writeIndex] = num1;
                i --;
            }
            writeIndex --;
        }
        while(i >= 0) {
            nums1[writeIndex] = nums1[i];
            i --;
            writeIndex --;
        }
        while(j >= 0) {
            nums1[writeIndex] = nums2[j];
            j --;
            writeIndex --;
        }
    }
}
