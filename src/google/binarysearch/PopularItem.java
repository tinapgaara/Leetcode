package google.binarysearch;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by yingtan on 11/26/15.
 */
/*
*
* popular item定义就是大于size的1/4。
*
*
第二题只需要检查0, 1/4, 2/4, 3/4 4/4位置的元素看它们是不是就可以了，检查每一个数用二分搜索找左右boundary算个数，所以是(10 logn) = logn

找n/4 n/2 3n/4 n这几个candidate，然后分别用二分搜索看长度满不满足
* */
public class PopularItem {

    public int popularNum(int[] nums) {
        int len = nums.length;
        for (int i = 1; i <= 4; i ++) {
            int candidateIndex = ((len-1) * i) / 4;

            if (nums[candidateIndex] == nums[(len * (i-1))/4])
                return nums[candidateIndex];

            int rightConsecutiveMost = binarySearchRightMostIndex(nums[candidateIndex], nums, candidateIndex, len - 1);
            if (rightConsecutiveMost == -1) continue;

            int leftConsecutiveMost = binarySearchLeftMostIndex(nums[candidateIndex], nums, 0, candidateIndex);

            if ( (leftConsecutiveMost != -1)
                    && (rightConsecutiveMost - leftConsecutiveMost + 1 > (len / 4))) {
                return nums[candidateIndex];
            }
        }
        return -1;
    }

    // get elements which appear more than [n/3] times
    public List<Integer> MajorityElementII(int[] nums) {
        int len = nums.length;
        Arrays.sort(nums);
        List<Integer> res = new ArrayList<>();
        if ((nums == null) || (nums.length == 0)) return res;

        for (int i = 1; i <= 3; i ++) {

            int candidateIndex = ((len-1) * i) / 3;

            if (res.contains(nums[candidateIndex])) continue;

            /*
            if (nums[candidateIndex] == nums[((len-1) * (i-1))/3]) {
                res.add(nums[candidateIndex]);
                continue;
            }
            */

            int rightConsecutiveMost = binarySearchRightMostIndex(nums[candidateIndex], nums, candidateIndex, len - 1);
            if (rightConsecutiveMost == -1) continue;

            int leftConsecutiveMost = binarySearchLeftMostIndex(nums[candidateIndex], nums, 0, candidateIndex);

            if ( (leftConsecutiveMost != -1)
                    && (rightConsecutiveMost - leftConsecutiveMost + 1 > (len / 3))) {
                res.add(nums[candidateIndex]);
            }
        }
        return res;
    }

    // 33334
    public int binarySearchRightMostIndex(int candidate, int[] nums, int low, int high) {
        if (low > high) return -1;
        int med = (low + high) / 2;
        if ((med - 1 >= 0) && (med + 1 < nums.length)) {
            if ((candidate == nums[med]) && (nums[med] == nums[med-1])) {
                if (nums[med+1] > nums[med]) return med;
                else if (nums[med] == nums[med+1])
                    return binarySearchRightMostIndex(candidate, nums, med+1, high);
            }
            else if (nums[med] > candidate) {
                return binarySearchRightMostIndex(candidate, nums, low, med-1);
            }
        }
        else if (nums[med] == candidate) {
                return med;
        }
        else if (med - 1 >= 0) {
            return binarySearchRightMostIndex(candidate, nums, low, med - 1);
        }
        return -1;
    }

    // 223333
    public int binarySearchLeftMostIndex(int candidate, int[] nums, int low, int high) {
        if (low > high) return -1;
        int med = (low + high) / 2;
        if ((med - 1 >= 0) && (med + 1 < nums.length)) {
            if ((candidate == nums[med]) && (nums[med] == nums[med+1])) {
                if (nums[med] > nums[med-1]) return med;
                else if (nums[med] == nums[med-1])
                    return binarySearchLeftMostIndex(candidate, nums, low, med - 1);
            }
            else if (nums[med] < candidate) {
                return binarySearchLeftMostIndex(candidate, nums, med + 1, high);
            }
        }
        else if (nums[med] == candidate) {
            return med;
        }
        else if (med + 1 < nums.length) {
            return binarySearchLeftMostIndex(candidate, nums, med + 1, high);
        }
        return -1;
    }

    public static void main(String[] args) {
        PopularItem ob = new PopularItem();
        int[] nums = new int[]{3,2,3,2};
        System.out.println(ob.MajorityElementII(nums));
    }
}
