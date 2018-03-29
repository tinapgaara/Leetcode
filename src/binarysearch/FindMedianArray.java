package binarysearch;

/**
 * Created by yingtan on 1/21/18.
 */
import java.util.*;
public class FindMedianArray {

    public double findMedian(int[] nums) {
        int low = 0;
        int high = nums.length - 1;
        Random r = new Random();
        while(low < high) {
            int pivotIndex = r.nextInt(high - low + 1) + low;
            // swap all elements smaller than pivot to left
            // swap all elements larger than pivot to right
            // then, pivot is put to a correct pos index
            int index = partition(nums, pivotIndex, low, high);
            int smallerNum = index;
            int largerNum = nums.length - index - 1;
            if (nums.length % 2 != 0) {
                if (smallerNum == largerNum) {
                    // this is median for odd
                    return nums[index];
                }
                else if (smallerNum < largerNum) {
                    low = index + 1;
                }
                else {
                    high = index - 1;
                }
            }
            else if (nums.length % 2 == 0) {
                // [_ _]  find the first one
                if (smallerNum + 1 == largerNum) {
                    return (nums[index] + nums[index + 1]) / 2.0;
                }
                else if (smallerNum + 1 < largerNum) {
                    low = index + 1;
                }
                else {
                    high = index - 1;
                }
            }
        }
        return -1;
    }
    public int partition(int[] nums, int pivotIndex, int low, int high) {
        int pivot = nums[pivotIndex];
        int tmp = nums[high];
        nums[high] = nums[pivotIndex];
        nums[pivotIndex] = tmp;
        int realIndex = low;
        for (int i = low; i < high; i ++) {
            if (nums[i] < pivot) {
                tmp = nums[i];
                nums[i] = nums[realIndex];
                nums[realIndex] = tmp;
                realIndex ++;
            }
        }
        // now realIndex is the corret pos
        tmp = nums[realIndex];
        nums[realIndex] = nums[high];
        nums[high] = tmp;
        return realIndex;
    }

    public static void main(String[] args) {
        FindMedianArray ob = new FindMedianArray();
        int[] nums = {1,3,2,4,5};
        System.out.println(ob.findMedian(nums));
    }
}
