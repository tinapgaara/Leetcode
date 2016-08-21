package google.string;

import java.util.HashSet;

/**
 * Created by yingtan on 11/6/15.
 */
/*
* Given a string, find the length of the longest substring T
* that contains at most 2 distinct characters.

For example, Given s = “eceba”,

T is "ece" which its length is 3.
* */
public class LongestSubstrAtMost2DiffChars {

    // Important !!! use hashset !!!
    public int lengthOfLongestSubstringTwoDistinct(String s) {
        if (s.length() <= 2) return s.length();

        return lengthOfLongestSubstringKDistinct(s, 2);
    }

    public int lengthOfLongestSubstringKDistinct(String s, int k) {

        int[] count = new int[256];
        int low = 0;
        int high = 0;
        int maxLen = 0;
        int distinct = 0;

        for (int i = 0 ; i < s.length(); i ++) {
            char ch = s.charAt(i);
            high ++;
            if (count[ch] == 0) {
                distinct ++;
            }
            count[ch] ++;

            while (distinct > k) {
                char lowCh = s.charAt(low);
                count[lowCh] --;
                low ++;
                if (count[lowCh] == 0)
                    distinct --;
            }
            maxLen = Math.max(maxLen, high - low);
        }
        return maxLen;
    }
}
