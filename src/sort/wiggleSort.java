package sort;

import java.util.Arrays;

/**
 * Created by yingtan on 9/23/15.
 */
public class wiggleSort {

    //Solution 2:
    public void wiggleSort(int[] nums) {
        if (nums == null || nums.length == 0) return;
        for (int i = 1 ; i < nums.length; i ++) {
            if ((i % 2 == 0 && nums[i-1] < nums[i]) || (i % 2 != 0 && nums[i-1] > nums[i])) {
                int tmp = nums[i];
                nums[i] = nums[i-1];
                nums[i-1] = tmp;

            }
        }
    }

    //Solution 1:
    public void wiggleSort_2(int[] nums) {
        boolean isIncrease = true;
        if (nums == null || nums.length == 0) return;
        for (int i = 0 ; i < nums.length; i ++) {
            if (i == 0) continue;
            if (isIncrease) {
                if (nums[i-1] > nums[i]) {
                    int tmp = nums[i-1];
                    nums[i-1] = nums[i];
                    nums[i] = tmp;
                }
                isIncrease = false;
            }
            else {
                if (nums[i-1] < nums[i]) {
                    int tmp = nums[i-1];
                    nums[i-1] = nums[i];
                    nums[i] = tmp;
                }
                isIncrease = true;
            }
        }
    }


    public void wiggleSort_3(int[] nums) {
        if ((nums == null) || (nums.length <= 1))
            return;
        Arrays.sort(nums);

        int len = nums.length;
        if (len == 2) return;
        int low = 1 ;
        int high = 2;
        while (high < len) {
            int tmp = nums[low];
            nums[low] = nums[high];
            nums[high] = tmp;

            low = low + 2;
            high = low + 1;
        }
    }

    public static void main(String[] args) {
        String[] strs = new String[]{"abc", "bcd", "acef", "xyz", "az", "ba", "a", "z"};
        Arrays.sort(strs);
        System.out.println(('b' - 'a') + ('z' - 't') + 1);
        for (int i = 0 ; i < strs.length; i ++) {
            System.out.println(strs[i]);
        }
    }
}
