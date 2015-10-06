package sort;

/**
 * Created by yingtan on 10/5/15.
 */
// time: O(n + maxNum) where n = nums.length
// space: O(maxNum)
public class CountSort {

    public int[] countSort(int[] nums, int maxNum) {
        int len = nums.length;
        int[] count = new int[maxNum+1];

        for (int i = 0 ; i < len; i ++) {
            int num = nums[i];
            count[num] ++;
        }
        for (int i = 1 ; i <= maxNum; i ++) {
            count[i] = count[i] + count[i-1];
        }

        int[] res = new int[len];
        for (int j = len-1; j >= 0 ; j --) {
            int num = nums[j];
            int numCount = count[num];
            res[numCount-1] = num;
            count[num] --;
        }
        return res;
    }

    public static void main(String[] args) {
        CountSort ob = new CountSort();
        int[] nums = new int[]{1,4,8,2};
        nums = ob.countSort(nums, 8);
        for (int i = 0 ; i < nums.length; i ++) {
            System.out.println(nums[i]);
        }
    }

}
