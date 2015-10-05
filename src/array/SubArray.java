package array;

/**
 * Created by yingtan on 9/1/15.
 */
public class SubArray {

    // Solution 1: very slow
    /*
    public int minSubArrayLen(int s, int[] nums) {

        if (nums == null) {
            return 0;
        }

        int start = 0;
        int end = 0;

        int minLen = Integer.MAX_VALUE;
        int sum = 0;

        int len = nums.length;
        int tmpLen = 0;
        while(start < len) {

            System.out.println(sum);
            if ((sum >= s) && (tmpLen < minLen)) {
                minLen = Math.min(minLen, tmpLen);
                tmpLen = 0;
                start ++;
                end = start;
                sum = 0;
            }
            else if (tmpLen >= minLen) {
                tmpLen = 0;
                start ++;
                end = start;
                sum = 0;
            }
            else if (sum < s) {
                if (end >= len) {
                    break;
                }
                sum = sum + nums[end];
                end ++;
                tmpLen ++;
            }
        }

        if (minLen >= Integer.MAX_VALUE) {
            minLen = 0;
        }

        return minLen;
    }
    */

    public int minSubArrayLen(int s, int[] nums) {

        if (nums == null) {
            return 0;
        }

        int start = 0;
        int end = 0;

        int minLen = Integer.MAX_VALUE;
        int sum = 0;

        int len = nums.length;
        int tmpLen = 0;
        while( (start <= end) && (end < len) ) {
            while((end < len) && (sum < s)) {
                sum = sum + nums[end];
                tmpLen ++;
                end ++;
            }
            while((start < len) && (sum >= s) ) {
                minLen = Math.min(minLen, tmpLen);
                sum = sum - nums[start];

                start ++;
                tmpLen --;
            }
        }
        if (minLen == Integer.MAX_VALUE) {
            minLen = 0;
        }
        return minLen;
    }

    public static void main(String[] args) {
        SubArray ob = new SubArray();
        int[] nums = new int[]{2,3,1,2,4,3};
        System.out.println("end::" + ob.minSubArrayLen(7, nums)) ;
    }
}
