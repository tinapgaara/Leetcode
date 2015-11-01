package permutation;

/**
 * Created by yingtan on 10/24/15.
 */
/*
* http://fisherlei.blogspot.com/2012/12/leetcode-next-permutation.html
*
*
* */
public class NextPermutation {

    public void nextPermutation(int[] nums) {
        if ( (nums== null) || (nums.length == 0)) return ;
        if (nums.length == 1) return;

        int cur = nums[nums.length-1];
        int i = nums.length;
        for (i = nums.length-2; i >=0 ; i --) {
            int prev = nums[i];
            if (prev < cur) break;
            else {
                cur = prev;
            }
        }
        // Very Important !!!!!!!   need to judge what if: 3 2 1
        if (i < 0) {
            increaseNumber(nums, 0, nums.length-1);
            return;
        }

        int pivot = nums[i];
        int pivotIndex = i;
        for (i = nums.length-1; i >pivotIndex ; i --) {
            if (nums[i] > pivot) {
                break;
            }
        }

        int exchangeIndex = i;
        int exchangeValue = nums[i];

        nums[pivotIndex] =  exchangeValue;
        nums[exchangeIndex] = pivot;

        int low = pivotIndex + 1;
        int high = nums.length - 1;
        increaseNumber(nums, low, high);

    }

    public void increaseNumber(int[] nums, int low, int high) {
        while (low < high) {
            int tmp = nums[low];
            nums[low] = nums[high];
            nums[high] = tmp;

            low ++;
            high --;
        }
    }
}
