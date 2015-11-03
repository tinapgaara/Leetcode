package google.string;

import java.util.Arrays;
import java.util.Comparator;

/**
 * Created by max2 on 11/3/15.
 */
/*
* 给一一个字符串数组,找出这样的字符串对(str1,str2),使得
1,两个字符串不包含一一样的字符
2. ⻓长度乘积最大
*
*  Assume all chars are from a ~ z.
* */
public class MaxMultipleStrLen {

    // Solution 1: each string -> char[26]
    // for each string, scan rest of string to calculte multiple rest
    // finally, find max one

    // O(n^2)
    public int maxMultiple2StrLenNoDup(String[] strs) {
        int len = strs.length;
        int[][] isDup = new int[len][26];

        StringComparator comparator = new StringComparator();
        Arrays.sort(strs, comparator);

        for (int i = 0 ; i < strs.length ; i ++) {
            for (int j = 0 ; j < strs[i].length(); j ++) {
                char ch = strs[i].charAt(j);
                if (isDup[i][ch-'a'] == 0) {
                    isDup[i][ch-'a'] = 1;
                }
            }
        }

        int maxLen = 0;
        int i = 0;
        while (i < strs.length -1) {
            int j = i + 1;
            while (j < strs.length) {

                if (strs[i].length() * strs[j].length() < maxLen)  {
                    break;
                }

                boolean isDupFlag = false;
                for (int k = 0 ; k < 26; k ++) {
                    if ((isDup[i][k] & isDup[j][k]) == 1) {
                        isDupFlag = true;
                        break;
                    }
                }
                if (!isDupFlag) {
                    maxLen = Math.max(maxLen, strs[i].length() * strs[j].length());
                }
                j ++;
            }
            i ++;
        }
        return maxLen;
    }

    public class StringComparator implements Comparator<String> {
        @Override
        public int compare(String s1, String s2) {
            if (s1.length() < s2.length()) {
                return 1;
            }
            else if (s1.length() > s2.length()) {
                return -1;
            }
            return 0;
        }
    }

    // can we do it better < o(n^2) ??
}
