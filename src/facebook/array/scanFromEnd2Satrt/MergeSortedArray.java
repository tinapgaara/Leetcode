package facebook.array.scanFromEnd2Satrt;

/**
 * Created by yingtan on 5/21/17.
 *
 * 88. Merge Sorted Array Add to List
 DescriptionHintsSubmissionsSolutions
 Total Accepted: 159734
 Total Submissions: 502546
 Difficulty: Easy
 Contributor: LeetCode
 Given two sorted integer arrays nums1 and nums2, merge nums2 into nums1 as one sorted array.

 Note:
 You may assume that nums1 has enough space (size that is greater or equal to m + n) to hold additional elements from nums2. The number of elements initialized in nums1 and nums2 are m and n respectively.

 */
public class MergeSortedArray {

    // since we know nums1 has enough space, so we start from nums1 end to start
    // scan from tail to start
    // if this is a large one, then put to nums1 tail
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        if (nums1 == null || nums2 == null) return;

        // Important !!!! from tail to start
        int k = m + n - 1;
        int i = m -1;
        int j = n - 1;

        while (i >= 0 && j >= 0) {
            if (nums1[i] > nums2[j]) {
                nums1[k] = nums1[i];
                k --;
                i --;
            }
            else if (nums1[i] < nums2[j]) {
                nums1[k] = nums2[j];
                k --;
                j --;
            }
            else {
                nums1[k] = nums1[i];
                k --;
                nums1[k] = nums2[j];
                k --;

                i --;
                j --;
            }
        }
        if (i >= 0) {
            while (i >= 0) {
                nums1[k] = nums1[i];
                k --;
                i --;
            }
        }
        else if (j >= 0) {
            while(j >= 0) {
                nums1[k] = nums2[j];
                k --;
                j --;
            }
        }
    }

}
