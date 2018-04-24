package vmware;
//3.给一个nums 数组, 再给一个 maxes 数组，找出nums数组中有多少个元素比maxes数组中的元素小，返回另一个数组
import java.util.*;
public class SmallerNumbers {
    public int[] findSmallerNumers(int[] nums, int[] maxs) {
        if (nums == null || maxs == null) return null;
        Arrays.sort(nums);
        int[] res = new int[maxs.length];
        for (int i = 0 ; i < maxs.length; i ++) {
            int max=  maxs[i];
            int firstNumLarger = findFirstLarger(nums, max, 0, nums.length - 1);
            if (firstNumLarger == -1) {
                res[i] = nums.length;
            }
            else {
                int numSmaller = firstNumLarger;
                res[i] = numSmaller;
            }
        }
        return res;
    }
    public int findFirstLarger(int[] nums, int max, int low, int high) {
        if (low > high) return -1;
        while(low + 1< high) {
            int med = low + (high - low) / 2;
            if (nums[med] >= max) {
                high = med;
            }
            else {
                low = med + 1;
            }
        }
        if (nums[low] >= max) return low;
        else if (nums[high] >= max) return high;
        return -1;// all nums < max
    }
}
