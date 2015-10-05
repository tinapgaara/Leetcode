package sort;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yingtan on 9/30/15.
 */
public class QuickSort {

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
        ob.quicksort(diff, 0, 2, indexs);
    }
}
