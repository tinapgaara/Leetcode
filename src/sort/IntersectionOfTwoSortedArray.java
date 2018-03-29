package sort;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yingtan on 12/12/17.
 */
public class IntersectionOfTwoSortedArray {

    public List<Integer> intersect(int[] arr1, int[] arr2) {
        int i = 0;
        int j = 0;
        List<Integer> res = new ArrayList<>();
        while(i < arr1.length && j < arr2.length) {
            if (arr1[i] < arr2[j]) {
                i ++;
            }
            else if (arr1[i] > arr2[j]) {
                j ++;
            }
            else {
                // avoid duplicate ones
                if (i == 0 || arr1[i] != arr1[i-1]) {
                    res.add(arr1[i]);
                }
                i ++;
                j ++;
            }
        }
        return res;
    }
}
