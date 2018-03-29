package facebook.dp;

/**
 * Created by yingtan on 2/19/18.
 * 673. Number of Longest Increasing Subsequence
 Given an unsorted array of integers, find the number of longest increasing subsequence.
 Example 1:
 Input: [1,3,5,4,7]
 Output: 2
 Explanation: The two longest increasing subsequence are [1, 3, 4, 7] and [1, 3, 5, 7].

 */
import java.util.*;
public class NumberOfLongestIncreasingSubsequence {
    public int findNumberOfLIS(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int[] dpLen = new int[nums.length]; // max len ending with ith element
        int[] dpcount = new int[nums.length];// how many subsequences with max len ending with ith element
        Arrays.fill(dpLen, 1);
        Arrays.fill(dpcount, 1);
        for (int i = 1; i < nums.length; i ++) {
            for (int j = 0 ; j < i ; j ++) {
                if (nums[i] > nums[j]) {
                    if (dpLen[j] + 1 > dpLen[i]) {
                        dpLen[i] = dpLen[j] + 1;
                        // very important !!!!!
                        dpcount[i] = dpcount[j];
                    }
                    else if (dpLen[j] + 1 == dpLen[i]) {
                        // very important !!!!!
                        dpcount[i] = dpcount[i] + dpcount[j];
                    }
                }
            }
        }
        int maxlen = 0;
        int num = 0;
        for (int i = 0 ; i < nums.length; i ++) {
            // this is the longest subseq so far
            if (dpLen[i] > maxlen) {
                maxlen = dpLen[i];
                num = dpcount[i];
            }
            else if (dpLen[i] == maxlen) { // we already saw this len subseq before
                num = num + dpcount[i];
            }
        }
        return num;
    }

}
