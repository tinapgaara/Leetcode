package google.median;

/**
 * Created by yingtan on 8/19/17.
 *
 * 4. Median of Two Sorted Arrays
 DescriptionHintsSubmissionsDiscussSolution
 Discuss Pick One
 There are two sorted arrays nums1 and nums2 of size m and n respectively.

 Find the median of the two sorted arrays. The overall run time complexity should be O(log (m+n)).

 Example 1:
 nums1 = [1, 3]
 nums2 = [2]

 The median is 2.0
 Example 2:
 nums1 = [1, 2]
 nums2 = [3, 4]

 The median is (2 + 3)/2 = 2.5

 */
public class FindMedianTwoSortedArray {

    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        // if nums1.med  < nums2.med :
        // means using nums1.med as global med is too smaller, using nums2.med as global med is too large so,
        // newmed must between num1.right + num2.left :  find[num1.med, num1.high] or find[num2.low, num2.med]

        // if num1.med > num2.med:
        // means using num1.med as global med is too large, using num2.med as global med is too smaller, so
        // newmed must between find[num1.low, num1.med] or find[num2.med, num2.high]



        int m = nums1.length, n = nums2.length;
        if ( (m + n) % 2 != 0) { // odd
            return findKth(nums1, nums2, (m + n)/ 2, 0, m-1, 0, n-1);
        }
        else { // even, find (m+n)/2th element in the num1, find (m+n)/2+1th element in the num2
            return (findKth(nums1, nums2, (m + n)/ 2, 0, m-1, 0, n-1) + findKth(nums1, nums2, (m + n)/2 - 1, 0, m-1, 0, n-1))
                    * 0.5;
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

        // supposed , try to find kth element in num1 + nums2
        // this equals to (propotional)
        // find kth * (len1 / (len1 + len2)) in num1
        // example:
        // 1 2 3
        // 4 5 6 7

        int mid1 = (len1 * k) / (len1 + len2); // k ratio th element
        int mid2 = k - mid1 - 1; // rest index of mid of num2

        // make mid1 and mid2 to be array index
        mid1 = low1 + mid1;
        mid2 = low2 + mid2;

        // example:
        // 4 5 6 7
        // 1 2 3

        // 5 > 2, then we discard [1,2]: k = k - (mid2 - low2 + 1)
        if (num1[mid1] > num2[mid2]) {
            k = k - (mid2 - low2 + 1);
            high1 = mid1;
            low2 = mid2 + 1;
        }
        // example:
        // 1 2 3
        // 4 5 6 7

        // 2 < 5, then we discard [1,2]: k = k - (mid1 - low1 + 1)
        else {
            k = k - (mid1 - low1 + 1);
            low1 = mid1 + 1;
            high2 = mid2;
        }
        return findKth(num1, num2, k, low1, high1, low2, high2);
    }
}
