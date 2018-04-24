package array;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yingtan on 4/1/18.
 *
 * 324. Wiggle Sort II
 DescriptionHintsSubmissionsDiscussSolution
 Pick One
 Given an unsorted array nums, reorder it such that nums[0] < nums[1] > nums[2] < nums[3]....

 Example:
 (1) Given nums = [1, 5, 1, 1, 6, 4], one possible answer is [1, 4, 1, 5, 1, 6].
 (2) Given nums = [1, 3, 2, 2, 3, 1], one possible answer is [2, 3, 1, 3, 1, 2].

 Note:
 You may assume all input has valid answer.

 Follow Up:
 Can you do it in O(n) time and/or in-place with O(1) extra space?

 Credits:
 Special thanks to @dietpepsi for adding this problem and creating all test cases.


 */
public class WiggleSortII {
    public void wiggleSort(int[] nums) {
        // o(n) three way partition
        int k = (nums.length - 1) / 2;
        int medianIndex = selectKth(nums, k);
        List<Integer> leftArr = new ArrayList();
        for(int i = 0; i <= medianIndex; i++)
            leftArr.add(nums[i]);
        List<Integer> rightArr = new ArrayList();
        for(int i = medianIndex + 1; i<nums.length; i++)
            rightArr.add(nums[i]);
        int i;

    }

    public int selectKth(int[] nums, int k) {
        int low = 0;
        int high = nums.length - 1;
        while(low < high) {
            int[] pivotIndexs = partition(nums, low, high);
            if (pivotIndexs[1] < k + 1) {
                low = pivotIndexs[1] + 1;
            }
            else if (pivotIndexs[0] > k + 1){
                high = pivotIndexs[0] - 1;
            }
            else {
                return k - 1;
            }

        }
        return -1;
    }
    public int[] partition(int[] nums, int low, int high) {
        int pivot = nums[low];
        int small = low;
        int same = low;
        while(same <= high) {
            if (nums[same] == pivot) {
                same ++;
            }
            else if (nums[same] < pivot) {
                int tmp = nums[same];
                nums[same] = nums[small];
                nums[small] = tmp;
                small ++;
                same ++;
            }
            else {
                int tmp = nums[high];
                nums[high] = nums[same];
                nums[same] = tmp;
                high --;
            }
        }
        return new int[]{small, high}; // store all same elements
    }
    public static void main(String[] args) {
        WiggleSortII ob = new WiggleSortII();
        int[] nums = {1,3,2,2,3,1};
        ob.wiggleSort(nums);
    }
}
