package google.random;

/**
 * Created by yingtan on 11/25/15.
 */
/*
* 第四面： 美国大叔， 就是给一个无重复的排序数组，给一个rand（），这个function返回随机数在[0, 1)之间，然后让你写一
个getRandom，返回一个在数组上下界range里，但是又不在数组里的数，每个这样的数必须被返回的几率相同。
*
* */
public class GenerateMissingNumRandom {

    public int generateMissingNum(int[] nums) {
        int len = nums.length;
        int missingNum = nums[len] - nums[0] + 1 - len;

        if (missingNum <= 0) return 0;
        int missingNumNo = 1 + (int)Math.floor((Math.random() * missingNum));

        return binarySearch(missingNumNo, 0, len-1, nums);
    }

    public int binarySearch(int missingNumNo, int low, int high, int[] nums) {

        if (high == low  + 1)
            return nums[low] + missingNumNo;

        int med = (low + high) / 2;

        int missingLeftNum = nums[med] - nums[low] + (med - low);
        if (missingLeftNum < missingNumNo) {
            return binarySearch(missingLeftNum, low, med-1, nums);
        }
        else {
            return binarySearch(missingLeftNum - missingNumNo, med+1, high, nums);
        }
    }
}
