package google.array;

import java.util.HashMap;
import java.util.Map;

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
    // all positive numbers
    public static int minSubSequenceLen(int[] nums, int sum) {
        if ((nums == null) || (nums.length == 0)) return 0;

        int low = 0;
        int minLen = Integer.MAX_VALUE;
        int tmpSum = 0;
        int high = 0;
        while ((low <= high) && (high < nums.length)) {
            tmpSum  = tmpSum + nums[high];
            if (tmpSum == sum) {
                minLen = Math.min(minLen, high - low + 1);
                tmpSum = tmpSum - nums[low];
                low ++;
                high ++;
            }
            else if (tmpSum < sum) {
                high ++;
            }
            else {
                tmpSum = tmpSum - nums[low] - nums[high];
                low ++;
            }
        }
        return minLen;
    }

    // Condition 2: has negative numbers
    // Solution 1: o(n) use hashMap
    public static boolean checkSubSequenceHasNegative(int[] nums, int target) {
        Map<Integer, Integer> indexsOfPreviousSum = new HashMap<>();

        int curSum = 0;
        for (int i = 0 ; i < nums.length ; i ++) {
            curSum = curSum + nums[i];
            if (indexsOfPreviousSum.containsKey(curSum - target)) {
                return true;
            }
            else {
                indexsOfPreviousSum.put(curSum, i);
            }
        }
        return false;
    }

    // Solution 1: o(n) use hashMap: map consecutive sum from 0- index  - >  index
    public int minSubSequenceLenHasNeg(int[] nums, int target) {
        HashMap<Integer, Integer> indexs = new HashMap<Integer, Integer>();
        int sum = 0;
        for (int i = 0 ; i < nums.length; i ++) {
            sum = sum + nums[i];
            indexs.put(sum, i);
        }

        int minLen = Integer.MAX_VALUE;
        if (indexs.containsKey(target)) {
            minLen = indexs.get(target) + 1;
        }
        for (Map.Entry<Integer, Integer> entry: indexs.entrySet()) {
            int x = entry.getKey();
            int start = entry.getValue();
            int y = x + target;
            if (indexs.containsKey(y)) {
                int end = indexs.get(y);
                if (end - start > 0) {
                    minLen = Math.min(minLen, end-start);
                }
            }
        }
        return minLen;
    }


    // Solution 2: use DP
    // TODO: how to do this in DP ?


    public static void main(String[] args) {
        ConseqSubSeqSum ob = new ConseqSubSeqSum();
        int[] num = new int[]{2,3,4,5,6,7,8};
        int[] negs = new int[]{4,5,-6,1,-2,3,7};
        System.out.println(ob.minSubSequenceLenHasNeg(negs, 9));
    }
}
