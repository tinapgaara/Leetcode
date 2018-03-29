package binarysearch;

/**
 * Created by yingtan on 1/20/18.
 */
import java.util.*;
public class FindKLargestElement {

    public int findKLargest_pivot(int[] nums, int k) {
        int low = 0;
        int high = nums.length - 1;
        Random r = new Random();
        while(low < high) {
            int pivotIndex = r.nextInt(high - low + 1) + low;
            int index = partition(nums, pivotIndex, low, high);
            if (index == k-1) {
                return nums[index]; // this is the kth largest element
            }
            else if (index > k-1) {
                high = index - 1;
            }
            else {
                low = index + 1;
            }
        }
        return -1;
    }
    // important !!! partition function
    public int partition(int[] nums, int pivotIndex, int low, int high) {
        int pivot = nums[pivotIndex];
        // swap pivotindex with high
        int tmp = nums[high];
        nums[high] = nums[pivotIndex];
        nums[pivotIndex] = tmp;
        int newpivot = low; // actually index of pivot

        for (int i = low; i < high; i ++) {
            if (nums[i] > pivot) {
                // put to left
                //swap ith with
                tmp = nums[i];
                nums[i] = nums[newpivot];
                nums[newpivot] = tmp;
                newpivot ++;
            }
        }
        tmp = nums[high];
        nums[high] = nums[newpivot];
        nums[newpivot] = tmp;
        return newpivot;
    }
    // what if requires stable sort ?
    // can use bubble sort for in-place : o(n*k)
    public int findKLargest_bubble(int[] nums, int k) {
        for (int i = 0 ; i < k; i ++) { // put max k elements to correct place.
            for (int j = 0; j < nums.length - i - 1; j ++) { // already has i elements put in higher correct place. don't need to scan there
                if (nums[j] > nums[j+1]) {
                    // swap temp and arr[i]
                    int temp = nums[j];
                    nums[j] = nums[j+1];
                    nums[j+1] = temp;
                }
            }
        }
        return nums[nums.length - k];
    }

    public static void main(String[] args) {
        FindKLargestElement ob = new FindKLargestElement();
        int[] nums = {1,3,2,4,5,6};
        System.out.println(ob.findKLargest_pivot(nums, 2));
    }
}
