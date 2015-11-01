package sort;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yingtan on 9/30/15.
 */
public class QuickSort {

    /*
    * O(nlogn) comparisons
    * */
    /*
    * QuickSort(int[] num, int low, int high) {
    *   if (low < high) {
    *       int mid = Partition(num, low, high);
    *       QuickSort(num, low, mid-1);
    *       QuickSort(num, mid+1, high);
    *   }
    * }
    *
    * // in place, shift larg numbers to right
    * int Partition(int[] num, int low, int high) {
    *   int pivot = num[high];
    *   int smallNumEndIndex = low -1;
    *   int largeNumStartIndex = low;
    *   for (;largeNumStartIndex <= high; largeNumStartIndex ++) {
    *       if (num[largeNumStartIndex] < pivot) {
    *           smallNumEndIndex ++;
    *           int largeNum = num[smallNumEndIndex];
    *           num[smallNumEndIndex] = num[largeNumStartIndex];
    *           num[largeNumStartIndex] = largeNum;
    *       }
    *   }
    *   smallNumEndIndex ++;
    *   int largeNum =  num[smallNumEndIndex];
    *   num[smallNumEndIndex] = pivot;
    *   num[high] = largeNum;
    *
    *   return smallNumEndIndex;
    * }
    *
    * */

    // Solution 2: quicker, just one scan
    public void quickSort(int[] num, int low, int high) {
        if (low < high) {
            int mid = partition(num, low, high);
            quickSort(num, low, mid - 1);
            quickSort(num, mid + 1, high);
        }
    }

    public int partition(int[] num, int low, int high) {
        int mid = (low + high) / 2;
        // change pivot to last element
        int tmp = num[high];
        num[high] = num[mid];
        num[mid] = tmp;

        int smallNumEndIndex = low - 1;
        int largeNumStartIndex = low;

        int pivot = num[high];

        for (; largeNumStartIndex < high; largeNumStartIndex ++) {
            if (num[largeNumStartIndex] < pivot) {
                smallNumEndIndex ++;
                int largeNum = num[smallNumEndIndex];
                num[smallNumEndIndex] = num[largeNumStartIndex];
                num[largeNumStartIndex] = largeNum;
            }
        }
        smallNumEndIndex ++;
        int largeNum = num[smallNumEndIndex];
        num[smallNumEndIndex] = pivot;
        num[high] = largeNum;

        return smallNumEndIndex;
    }

    // Solution 1: while (while, while)
    public int[] quicksort(List<Double> diffs, int low, int high, int[] indexs) {
        int i = low;
        int j = high;
        double midVal = diffs.get(( i + j ) / 2);

        while (i < j) {
            while (diffs.get(j) > midVal) {
                j --;
                indexs[j] = j;
            }
            while (diffs.get(i) < midVal) {
                i ++;
                indexs[i] = i;
            }
            if (i < j) {
                double tmp = diffs.get(i);
                diffs.set(i, diffs.get(j));
                diffs.set(j, tmp);
                indexs[i] = j;
                indexs[j] = i;
                i ++;
                j --;
            }
            if (i < high) quicksort(diffs, i+1, high, indexs);
            if (j > low) quicksort(diffs, low, j -1, indexs);
        }
        return indexs;
    }

    public static void main(String[] args) {
        List<Double> diff = new ArrayList<>();
        diff.add(2.1);
        diff.add(2.5);
        diff.add(2.4);
        QuickSort ob = new QuickSort();
        int[] indexs = new int[3];
        indexs[0] = 0;
        indexs[1] = 1;
        indexs[2] = 2;
        // ob.quicksort(diff, 0, 2, indexs);

        int[] num = new int[]{2,7,4,0,8,10};
        ob.quickSort(num, 0, 3);
        for (int i = 0 ; i < num.length; i ++) {
            System.out.println(num[i]);
        }
    }
}
