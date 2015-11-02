package google.array;

/**
 * Created by yingtan on 11/2/15.
 */
/*
* Given an array of integers eg [1, 2, -3, 1]
* find whether there is a sub-sequence that sums to 0 and return it
* (eg [1, 2, -3] or [2, -3, 1]).
Checking every sub-sequence is O(n^2) which is too inefficient.
Any idea for improvements?
*
* */
public class ConseqSubSeqSum {

    // Condition 1: no negative numbers in num array
    // use greedy
    // two pointer
    public static boolean checkSubSequence(int[] num, int sum) {
        int head = 0;
        int tail  = 1;

        if (num.length == 1) {
            return sum == num[0];
        }

        int tmpSum = num[head];
        if (tmpSum == sum) return true;
        while ((tail < num.length) && (head < tail)) { // important !!! head < tail
            tmpSum = tmpSum + num[tail];
            if (tmpSum == sum) return true;
            else if (tmpSum < sum) {
                tail ++;
            }
            else {
                tmpSum = tmpSum - num[head] - num[tail]; // important need to subtract num[tail], because
                                                            // need to add num[tail] again
                head ++;
            }
        }
        return false;
    }

    // find min length of array whose total value == sum
    public static int minSubSequenceLen(int[] nums, int sum) {
        int head = 0;
        int tail  = 1;

        if ( (nums == null) || (nums.length == 0) ) return 0;
        if (nums.length == 1) {
            if (sum == nums[0]) return 1;
            else return 0;
        }

        int tmpSum = nums[head];
        int minLen = Integer.MAX_VALUE;
        if (tmpSum >= sum) minLen = 1;

        int len = 1;
        while ((tail < nums.length) && (head <= tail)) { // important !!! head < tail
            tmpSum = tmpSum + nums[tail];
            len ++;
            if (tmpSum == sum) {
                minLen = Math.min(minLen, len);
                tmpSum = tmpSum - nums[head];
                head ++;
                tail ++;
                len --;
            }
            else if (tmpSum < sum) {
                tail ++;
            }
            else {
                tmpSum = tmpSum - nums[head] - nums[tail]; // important need to subtract num[tail], because
                minLen = Math.min(minLen, len);
                // need to add num[tail] again
                len = len - 2;
                head ++;
            }
        }
        if (minLen == Integer.MAX_VALUE) {
            minLen = 0;
        }
        return minLen;
    }

    // find min length of array whose total value >= sum
    public int minSubArrayLen(int s, int[] nums) {
        int head = 0;
        int tail  = 1;
        if ( (nums == null) || (nums.length == 0) ) return 0;
        if (nums.length == 1) {
            if (s == nums[0]) return 1;
            else return 0;
        }

        int tmpSum = nums[head];
        int minLen = Integer.MAX_VALUE;
        if (tmpSum >= s) minLen = 1;
        int len = 1;
        while ((tail < nums.length) && (head <= tail)) { // important !!! head < tail
            tmpSum = tmpSum + nums[tail];
            len ++;
            if (tmpSum == s) {
                minLen = Math.min(minLen, len);
                tmpSum = tmpSum - nums[head];
                head ++;
                tail ++;
                len --;
            }
            else if (tmpSum < s) {
                tail ++;
            }
            else {
                tmpSum = tmpSum - nums[head] - nums[tail];
                // important need to subtract num[tail], because need to add num[tail] again
                minLen = Math.min(minLen, len);
                len = len - 2;
                head ++;
            }
        }
        if (minLen == Integer.MAX_VALUE) {
            minLen = 0;
        }
        return minLen;
    }

    // Condition 2: has negative numbers in num array  ?????
    public static boolean checkSubSequenceHasNegative(int[] num, int sum) {
        int head = 0;
        int tail  = 1;

        if (num.length == 1) {
            return sum == num[0];
        }

        int tmpSum = num[head];
        if (tmpSum == sum) return true;
        while ((tail < num.length) && (head < tail)) { // important !!! head < tail
            tmpSum = tmpSum + num[tail];
            if (tmpSum == sum) return true;
            else if (tmpSum < sum) {
                tail ++;
            }
            else {
                tmpSum = tmpSum - num[head] - num[tail]; // important need to subtract num[tail], because
                // need to add num[tail] again
                head ++;
            }
        }
        return false;
    }


    public static void main(String[] args) {
        int[] num = new int[]{10,2,3};
        System.out.println(minSubSequenceLen(num, 6));
    }
}
