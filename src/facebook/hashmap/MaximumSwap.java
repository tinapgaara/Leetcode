package facebook.hashmap;

import java.util.Arrays;

/**
 * Created by yingtan on 2/13/18.
 *
 * 670. Maximum Swap
 DescriptionHintsSubmissionsDiscussSolution
 Pick One
 Given a non-negative integer, you could swap two digits at most once to get the maximum valued number. Return the maximum valued number you could get.

 Example 1:
 Input: 2736
 Output: 7236
 Explanation: Swap the number 2 and the number 7.
 Example 2:
 Input: 9973
 Output: 9973
 Explanation: No swap.
 */
public class MaximumSwap {

    public int maximumSwap(int num) {
        int[] bucket = new int[10];
        Arrays.fill(bucket, -1);
        String str = num + "";
        for (int i = 0 ; i < str.length(); i ++) {
            char ch = str.charAt(i);
            bucket[ch - '0'] = i;
        }
        char[] chs = str.toCharArray();
        for (int i = 0 ; i < chs.length; i ++) {
            int digit = chs[i] - '0';
            // important !!! use greedy, so make sure the swapped number must be > digit
            for (int j = 9; j > digit ; j --) {
                if (bucket[j] != -1) {
                    int index = bucket[j];
                    if (index > i) {
                        char tmp = chs[index];
                        chs[index] = chs[i];
                        chs[i] = tmp;
                        // greedy: choose the largest digit and swap with the eariest ith digit
                        return Integer.parseInt(new String(chs));
                    }
                }
            }
        }
        // can not form larger number, return itself
        return num;
    }
}
