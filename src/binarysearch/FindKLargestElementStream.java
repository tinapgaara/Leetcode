package binarysearch;

/**
 * Created by yingtan on 1/21/18.
 */
import java.util.*;
public class FindKLargestElementStream {

    public int findKthLargestElement_stream(Iterator<Integer> stream, int k) {
        // use a memory: 2*k - 1, to store 2*k -1 elements, and do partition
        int[] nums = new int[2 * k -1];
        int size = 0;
        while(stream.hasNext()) {
            int num = stream.next();
            nums[size] = num;
            size ++;
            if (size == 2 * k - 1) {
                // need to sort and eliminate
                findKthLargest(nums, 0, 2 * k - 2, k);
                // only keep first k elements
                size = k;
            }
        }
        return nums[k];
    }
    public int findKthLargest(int[] nums, int low, int high, int k) {
        Random r = new Random();
        while(low < high) {
            int pivotIndex = r.nextInt(high - low + 1) + low;
            int realIndex = partition(nums, pivotIndex, low, high);
            if (realIndex == k-1) {
                return nums[realIndex];
            }
            else if (realIndex < k-1) {
                low = realIndex + 1;
            }
            else {
                high = realIndex - 1;
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
                // put to left
                tmp = nums[realIndex];
                nums[realIndex] = nums[i];
                nums[i] = tmp;
                realIndex ++;
            }
        }
        tmp = nums[high];
        nums[high] = nums[realIndex];
        nums[realIndex] = tmp;
        return realIndex;
    }
}
