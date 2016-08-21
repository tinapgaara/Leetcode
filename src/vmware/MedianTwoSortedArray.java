package vmware;

/**
 * Created by yingtan on 10/27/15.
 */
public class MedianTwoSortedArray {

    // for same length of two arrays
    // TODO:
    public double findMedianSortedArrays(int[] nums1, int[] nums2, int low, int high) {
        if (high+1 == 1) {
            return (double)(nums1[0] + nums2[0]) / 2;
        }
        else if (high+1 == 2) {
            return (double)(Math.max(nums1[0], nums2[0]) + Math.min(nums1[1], nums2[1])) / 2;
        }
        else {
            if ((high - low + 1) % 2 == 0) {
            }
            int m1 = nums1[(low + high) / 2];

        }
        return 0;
    }
}
