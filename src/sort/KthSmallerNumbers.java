package sort;
import java.util.*;
// find k closet nums with target
// Sol 1: priorityqueue: straightforward
// Sol 2: quick select: partition
public class KthSmallerNumbers {
    public List<Integer> kSmallernOSorting(int[] nums, int k) {
        List<Integer> res = new ArrayList<>();
        int low = 0;
        int high = nums.length - 1;
        while(low < high) {
            int index = partition(nums, low, high); // select a random pivot and put pivot to correct pos: index
            if (index + 1 == k) {
                // this is the kth element, all elements < kth elements are before index
                break;
            }
            else if (index + 1 < k) {
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
    // partition with deterministic pivot
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
    // partition with 3-way partition
    public int three_partition(int[] nums, int low, int high, int pivotIndex) {
        Random rnd = new Random();
        pivotIndex = low + rnd.nextInt(high - low + 1);
        // partition array into three subsets, one is smaller than pivot, one is equals to pivot, another is larger than pivot
        int p1 = low;
        int p2 = low;
        int p3 = high;
        while(p2 <= p3) {
            if (nums[p2] < nums[pivotIndex]) {
                int tmp = nums[p1];
                nums[p1] = nums[p2];
                nums[p2] = tmp;
                p1 ++;
                p2 ++;
            }
            else if (nums[p2] == nums[pivotIndex]) {
                p2 ++;
            }
            else {
                int tmp = nums[p3];
                nums[p3] = nums[p2];
                nums[p2] = tmp;
                p3 --;
            }
        }
        return p2;
    }

    // partition with randomized pivot
    public int kSmallernOSorting_random(int[] nums, int k) {
        int low = 0;
        int high = nums.length - 1;
        return partition_randomPivot(nums, low, high, k);
    }
    // partition with random pivot
    public int partition_randomPivot(int[] nums, int low, int high, int k) {
        Random rnd = new Random();
        if (low == high) return nums[low];
        int rndPivotIndex = low + rnd.nextInt(high - low + 1);
        int pivot = nums[rndPivotIndex];
        int i = low;
        int j = high;
        while(i <= j) {
            while(i <= j && nums[i] <= pivot) {
                i ++;
            }
            while(i <= j && nums[j] > pivot) {
                j --;
            }
            if (i <= j) {
                int tmp = nums[i];
                nums[i] = nums[j];
                nums[j] = tmp;
                i ++;
                j --;
            }
        }
        if (low + k - 1 <= j) {
            return partition_randomPivot(nums, low, j, k);
        }
        if (low + k - 1 >= i) {
            return partition_randomPivot(nums, i, high, k - (i - low));
        }
        // j + 1 is pivot index
        return nums[j + 1];
    }

    public static  void main(String[] args) {
        KthSmallerNumbers ob = new KthSmallerNumbers();
        int[] nums = {2,3,3,1,6,1,4};
        System.out.println(ob.kSmallernOSorting(nums, 3)); // only choose k elements without sorting
    }
}
