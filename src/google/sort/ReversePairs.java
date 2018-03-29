package google.sort;

/**
 * Created by yingtan on 11/10/17.
 *
 * 493. Reverse Pairs
 DescriptionHintsSubmissionsDiscussSolution
 Discuss Pick One
 Given an array nums, we call (i, j) an important reverse pair if i < j and nums[i] > 2*nums[j].

 You need to return the number of important reverse pairs in the given array.

 Example1:

 Input: [1,3,2,3,1]
 Output: 2
 Example2:

 Input: [2,4,3,5,1]
 Output: 3
 */
public class ReversePairs {
    /*
    * ---A--- | ----B--
    * 把数组分成两个部分,先算A当中满足条件的解,再算B当中满足条件得解,
    * 然后把A+B sort一下再返回
    * */
    public int reversePairs(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        return reversePairsSub(nums, 0, nums.length - 1);
    }
    private int reversePairsSub(int[] nums, int low, int high) {
        if (low >= high) return 0;
        int mid = low + (high - low) / 2;
        int count = reversePairsSub(nums, low, mid) + reversePairsSub(nums, mid+1, high);
        int[] merge = new int[high - low + 1];
        int i = low;
        int j = mid + 1;
        int k = 0;
        int p = mid + 1;
        while(i <= mid) {
            while(j <= high && nums[i] > 2L * nums[j]) {
                j ++;
            }
            count = count + (j - mid - 1);
            // merge
            while(p <= high && nums[i] >= nums[p]) {
                merge[k] = nums[p];
                k ++;
                p ++;
            }
            merge[k] = nums[i];
            k ++;
            i ++;
        }
        while(p <= high) {
            merge[k] = nums[p];
            k ++;
            p ++;
        }
        System.arraycopy(merge, 0, nums, low, merge.length);
        return count;
    }
}
