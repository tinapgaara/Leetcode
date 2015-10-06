package array;

import java.util.Arrays;
import java.util.Comparator;

/**
 * Created by yingtan on 10/5/15.
 */
public class LargestCombineNumber {

    public String largestNumber(int[] nums) {
        if ( (nums == null) || (nums.length == 0) ) return "";

        Integer[] nNums = new Integer[nums.length];
        for (int i = 0 ; i < nums.length; i ++) {
            nNums[i] = new Integer(nums[i]);
        }

        NumComparator comparator = new NumComparator();
        Arrays.sort(nNums, comparator);
        String res = "";
        boolean ifZero = true; // Attention !!!! boundary case
        for (int i = 0 ; i < nNums.length; i ++) {
            if (nNums[i] != 0) ifZero = false;
            res = res + nNums[i];
        }
        if (ifZero) return "0"; // boundary case
        else return res;
    }
    public class NumComparator implements Comparator<Integer> {
        @Override
        public int compare(Integer i1, Integer i2) { //i2 i1 = 1
            String num1 = i1 + "" + i2;
            long gNum1 = Long.parseLong(num1); // Attention: must use Long type

            String num2 = i2 + "" + i1;
            long gNum2 = Long.parseLong(num2);
            if (gNum1 < gNum2) return 1;
            else if (gNum1 > gNum2) return -1;
            else return 0;
        }
    }

    public static void main(String[] args) {
        LargestCombineNumber ob = new LargestCombineNumber();
        int[] nums = new int[]{1,2};
        ob.largestNumber(nums);
    }
}
