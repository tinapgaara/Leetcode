package google.binarysearch;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * Created by yingtan on 11/6/17.
 *
 * 658. Find K Closest Elements
 DescriptionHintsSubmissionsDiscussSolution
 Discuss Pick One
 Given a sorted array, two integers k and x, find the k closest elements to x in the array. The result should also be sorted in ascending order. If there is a tie, the smaller elements are always preferred.

 Example 1:
 Input: [1,2,3,4,5], k=4, x=3
 Output: [1,2,3,4]
 Example 2:
 Input: [1,2,3,4,5], k=4, x=-1
 Output: [1,2,3,4]
 */
public class FindKClosetElements {

    public List<Integer> findClosestElements(int[] arr, int k, int x) {
        List<Integer> res = new ArrayList<Integer>();
        int index = findFirstLargerThan(arr, x);
        if (index == -1) {
            index = 0;
        }
        int low = index - 1;
        int high = index;
        while(k > 0){
            // [low .. x . high] count high in k result, move high to righter
            if (low < 0 || (high < arr.length && Math.abs(arr[low] - x) > Math.abs(arr[high] - x))) {
                high ++;
            }
            else {
                low --;
            }
            k --;
        }
        for (int i = low + 1 ; i < high; i ++) {
            res.add(arr[i]);
        }
        return res;
    }

    private int findFirstLargerThan(int[] arr, int x) {
        int low = 0;
        int high = arr.length - 1;
        while(low + 1 < high) {
            int mid = low + (high - low) / 2;
            if (arr[mid] == x) return mid;
            if (arr[mid] < x) {
                low = mid + 1;
            }
            else {
                high = mid;
            }
        }
        if (arr[low] >= x) return low;
        if (arr[high] >= x) return high;
        return -1;
    }
}
