package sort;

/**
 * Created by yingtan on 10/4/15.
 */
public class MergeSort {

    // time : O(nlogn)
    // space; not in place
    /*
    * Merge-Sort (int[] num, int low, int high) {
    * if (low < high) {
    *   int mid = (low + high) / 2
    *   Merge-Sort (num, low, mid)
    *   Merge-Sort (num, mid+1, high)
    *   Merge(num, low, mid, high)
    * }
    *
    * Merge (int[] num, int low, int mid, int high) {
    * int len1 = mid - low + 1
    * int len2 = high - mid
    * int[] left = new int[len1]
    * int[] right = new int[len2]
    * for (int i = 0 ; i < len1; i ++) left[i] = num[low + i]
    * for (int i = 0 ; i < len2; i ++) right[i] = num[mid + i]
    * int i = 0;
    * int j = 0;
    * for (int k = low; k <= high; k ++) {
    *   if (left[i] <= right[j]) {
    *       num[k] = left[i]
    *       i ++;
    *   }
    *   else {
    *       num[k] = right[j]
    *       j ++
    *   }
    * }
    * }
    * */

    public void mergeSort(int[] num, int low, int high) {
        if (low < high) {
            int mid = (low + high) / 2;
            mergeSort(num, low, mid);
            mergeSort(num, mid+1, high);
            merge(num, low, mid, high);
        }
    }
    public void merge(int[] num, int low, int mid, int high) {
        int leftLen = mid - low + 1;
        int rightLen = high - mid;
        int[] left = new int[leftLen + 1];
        int[] right = new int[rightLen + 1];

        for (int i = 0 ; i < leftLen; i ++) {
            left[i] = num[low + i]; // pay attention
        }
        for (int i = 0 ; i < rightLen; i ++) {
            right[i] = num[mid + i + 1]; // pay attention
        }

        int i = 0 ;
        int j = 0 ;
        left[leftLen] = Integer.MAX_VALUE; // in case i ++  or j ++ out of bound
        right[rightLen] = Integer.MAX_VALUE; // pay attention

        for (int k = low; k <= high; k ++) {
            if (left[i] <= right[j]) {
                num[k] = left[i];
                i ++;
            }
            else {
                num[k] = right[j];
                j ++;
            }
        }
    }

    public static void main(String[] args) {
        MergeSort ob = new MergeSort();
        int[] num = new int[]{3,5,1,4,6};
        ob.mergeSort(num, 0, num.length-1);
    }
}
