package array;

import java.util.Random;

/**
 * Created by yingtan on 4/23/18.
 */
public class ThreeWayPartition {
    // move all numbers < pivot to left
    // move all numbers > pivot to right
    // keep equal ones in middle
    // one pointer from low, one pointer from high
    // sol1 : [p1 p2 p3]
    public void partition_3pointers(int[] nums, int pivotIndex) {
        int p1 = 0;
        int p2 = 0;
        int p3 = nums.length - 1;
        while(p2 < p3) {
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
                int tmp = nums[p2];
                nums[p2] = nums[p3];
                nums[p3] = tmp;
                p3 --;
            }
        }
    }
    // if put [smaller + equals] to left, and [larger] to right. Can use this.
    public void threepartition_notorder(int[] nums, int pivotIndex) {
        int pivot = nums[pivotIndex];
        int low = 0;
        int high = nums.length - 1;
        while(low <= high) {
            while(low <= high && nums[low] <= pivot) { // has to use <= pivot when partition <= pivot to left side
                low ++;
            }
            while(low <= high && nums[high] > pivot) {
                high --;
            }
            if (low <= high) {
                int tmp = nums[low];
                nums[low] = nums[high];
                nums[high] = tmp;
                low ++;
                high --;
            }
        }
        System.out.println(low + "," + high);
        for (int i = 0 ; i < nums.length; i ++) {
            System.out.println(nums[i]);
        }
    }
    public void quicksort(int[] nums, int low, int high) {
        if (low >= high) return; // base case
        int i = low;
        int j = high;
        Random rnd = new Random();
        int pivotIndex = low + rnd.nextInt(high - low + 1);
        int pivot = nums[pivotIndex];
        while(i <= j) {
            while(i <= j && nums[i] < pivot) { // when do quicksort, can't used <= pivot
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
        System.out.println(low + "," + j);
        // [low - j] : <= pivot
        // [i - high] : > pivot
        quicksort(nums, low, j);
        quicksort(nums, i, high);
    }
    // apply partition to quicksort
    public void quicksort_partition(int[] nums, int low, int high) {
        if (low > high) return;
        int mid = partition(nums, low, high);
        quicksort_partition(nums, low, mid - 1);
        quicksort_partition(nums, mid + 1, high);
    }
    public int partition(int[] nums, int low, int high) {
        int pivotIndex = low + (high - low) / 2;
        // exchange pivot with high elements
        int tmp = nums[high];
        nums[high] = nums[pivotIndex];
        nums[pivotIndex] = tmp;

        int pivot = nums[high];
        int firstHalf = low;
        for (int i = low; i < high; i ++) {
            if (nums[i] <= pivot) {
                tmp = nums[firstHalf];
                nums[firstHalf] = nums[i];
                nums[i] = tmp;
                firstHalf ++;
            }
        }
        tmp = nums[firstHalf];
        nums[firstHalf] = nums[high];
        nums[high] = tmp;
        return firstHalf;

    }
    public static void main(String[] args) {
        ThreeWayPartition ob = new ThreeWayPartition();
        int[] nums = {2,3,3,1,1,5,7,2,0, 0, 2};
        ob.threepartition_notorder(nums, 0);
        int[] nums2 = {2,3,3,1,1,5,7,2,0, 0, 2};
        ob.quicksort_partition(nums2, 0, nums2.length - 1);
        for (int i = 0 ; i < nums2.length ; i ++) {
            System.out.println(nums2[i]);
        }
    }
}
