package facebook.sort;

import java.util.ArrayList;
import java.util.List;

// find k closet nums with target
// Sol 1: priorityqueue: straightforward
// Sol 2: quick select: partition
public class KSmallerNumbers {
    public List<Integer> kSmallernOSorting(int[] nums, int k) {
        List<Integer> res = new ArrayList<>();
        int low = 0;
        int high = nums.length - 1;
        while(low < high) {
            int index = partition(nums, low, high); // select a random pivot and put pivot to correct pos: index
            if (index == k) {
                // this is the kth element, all elements < kth elements are before index
                break;
            }
            else if (index < k) {
                // we need to find kth element, it is after this index
                low = index + 1;
            }
            else {
                // kth element is before this index
                high = index - 1;
            }
        }
        for (int i = 0 ; i < k; i ++) {
            res.add(nums[i]);
        }
        return res;
    }
    public int partition(int[] nums, int low, int high) {
        // choose a pivot randomly
        int pivot = nums[high];
        // partition array into two subsets, one is smaller than pivot, another is larger than pivot
        // keep record the last element which is smaller than the pivot
        int firstHalf = low;
        for (int i = low; i < high; i ++) {
            if (nums[i] <= pivot) {
                // should be put to the first half : swap
                int tmp = nums[firstHalf];
                nums[firstHalf] = nums[i];
                nums[i] = tmp;
                firstHalf ++;
            }
            else if (nums[i] > pivot) {
                // nothing to do here;
            }
        }
        // now the array: [smaller, larger, pivot]
        // change pivot back to  its position
        int tmp = nums[firstHalf];
        nums[firstHalf] = nums[high];
        nums[high] = tmp;
        return firstHalf;
    }
    public static  void main(String[] args) {
        KSmallerNumbers ob = new KSmallerNumbers();
        int[] nums = {2,3,6,1,4};
        System.out.println(ob.kSmallernOSorting(nums, 3)); // only choose k elements without sorting
    }
}
