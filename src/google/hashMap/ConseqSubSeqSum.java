package google.hashMap;

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
