package linkedin.practice;

/**
 * Created by yingtan on 11/22/17.
 *
 * (i.e., 0 1 2 4 5 6 7 might become 4 5 6 7 0 1 2).

 You are given a target value to search. If found in the array return its index, otherwise return -1.

 */
public class SearchInRotatedSortedArray {

    public boolean search(int[] A, int target) {
        if (A == null) return false;
        int low = 0;
        int high = A.length - 1;
        while(low + 1 < high) {
            int mid = low + (high - low) / 2;
            if (A[low] < A[mid]) {
                if (target >= A[low] && target <= A[mid]) {
                    high = mid;
                }
                else {
                    low = mid + 1;
                }
            }
            else if (A[low] > A[low]) {
                if (target >= A[mid] && target <= A[high]) {
                    low = mid;
                }
                else {
                    high = mid - 1;
                }
            }
            else {
                low ++;
            }
        }
        if (A[low] == target) return true;
        if (A[high] == target) return true;
        return false;
    }
}
