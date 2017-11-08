package google.permutation;

/**
 * Created by yingtan on 12/22/15.
 */
public class NextPermutation {

    public void nextPermutation_straightForward(int[] nums) {
        if (nums == null || nums.length == 0) return;
        int i = nums.length - 2;
        // step1：从右往左，找到第一个不increase的数，即decrease的数 nums[i] < nums[i+1]
        while((i >= 0) && (nums[i] >= nums[i+1]) ) {
            i --;
        }
        // step2,从右往左，找到第一个比nums[i]大的数:nums[j]
        if (i >= 0) {
            int j = nums.length - 1;
            while ((j >= 0) && (nums[j] <= nums[i])) {
                j --;
            }

            // step 3:交换nunms[i]与nums[j]:
            if (j >= 0) swap(nums, i, j);

        }

        // step 4:reverse descending part of array,即nums[i]右边的数
        int left = i+1;
        int right = nums.length - 1;
        while(left < right) {
            swap(nums, left, right);
            left ++;
            right --;
        }
    }

    public void swap(int[] A, int i, int j) {
        int tmp = A[i];
        A[i] = A[j];
        A[j] = tmp;
    }


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

    public static void main(String[] args) {
        NextPermutation ob = new NextPermutation();
    }
}
