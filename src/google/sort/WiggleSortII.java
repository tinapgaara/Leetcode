package google.sort;

import java.util.Arrays;

/**
 * Created by yingtan on 8/20/17.
 *
 * 324. Wiggle Sort II
 DescriptionHintsSubmissionsDiscussSolution
 Discuss Pick One
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
        if ((nums == null) || (nums.length <= 1))
            return;
        int[] tmp = new int[nums.length];
        // o(n) space, o(nlogn) time
        for (int i = 0 ; i < nums.length; i ++) {
            tmp[i] = nums[i];
        }
        /*
        我们可以先给数组排序，然后在做调整。调整的方法是找到数组的中间的数，相当于把有序数组从中间分成两部分，然后从前半段的末尾取一个，在从后半的末尾去一个，这样保证了第一个数小于第二个数，然后从前半段取倒数第二个，从后半段取倒数第二个，这保证了第二个数大于第三个数，且第三个数小于第四个数

        */
        Arrays.sort(tmp);
        //相当于把有序数组从中间分成两部分
        int mid = (nums.length - 1) / 2;
        int end = nums.length - 1;

        int i = 0;
        while(i < nums.length) {
            //从前半段的末尾取一个 放入i
            nums[i] = tmp[mid];
            mid --;
            // important !!!
            // 从后半的末尾去一个,放入i+1
            if (i + 1 < nums.length) {
                nums[i + 1] = tmp[end];
                end --;
                // important !! + 2
                i = i + 2;
            }
            else // important !!!
                break;

        }
    }
}
