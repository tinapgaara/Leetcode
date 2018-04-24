package vmware;

/**
 * Created by yingtan on 10/27/15.
 */
public class MedianTwoSortedArray {

    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        // if nums1.med  < nums2.med :
        // newmed is between [num1.med ->] + [<- num2.med]

        // if num1.med > num2.med :
        // newmed is between [0 -> num1.med] + [num2.med  ->]
        int m = nums1.length, n = nums2.length;
        if ( (m + n) % 2 != 0) { // odd
            return findKth(nums1, nums2, (m + n)/ 2, 0, m-1, 0, n-1);
        }
        else {
            // even
            return (findKth(nums1, nums2, (m + n)/ 2, 0, m-1, 0, n-1) + findKth(nums1, nums2, (m + n)/2 - 1, 0, m-1, 0, n-1)) * 0.5;
        }
    }

    public int findKth(int[] num1, int[] num2, int k, int low1, int high1, int low2, int high2) {
        int len1 = high1- low1 + 1;
        int len2 = high2 - low2 + 1;

        if (len1 == 0) return num2[low2 + k];
        if (len2 == 0) return num1[low1 + k];
        if (k == 0) {
            if (num1[low1] < num2[low2]) return num1[low1];
            else return num2[low2];
        }

        int mid1 = (len1 * k) / (len1 + len2); // k ratio th element
        int mid2 = k - mid1 - 1; // rest index of mid of num2

        // make mid1 and mid2 to be array index
        mid1 = low1 + mid1;
        mid2 = low2 + mid2;

        if (num1[mid1] > num2[mid2]) {
            k = k - (mid2 - low2 + 1);
            high1 = mid1;
            low2 = mid2 + 1;
        }
        else {
            k = k - (mid1 - low1 + 1);
            low1 = mid1 + 1;
            high2 = mid2;
        }
        return findKth(num1, num2, k, low1, high1, low2, high2);
    }
}
