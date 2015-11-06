package google.hashset;

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
public class LongestSubstrAtMostMDiffChars {

    // Important !!! use hashset !!!
    public int lengthOfLongestSubstringMDistinct(String s, int m) {
        if (s.length() <= m) return s.length();

        int low = 0;
        int high = 1;
        int maxLen = 1;

        HashSet<Character> chs = new HashSet<>();

        chs.add(s.charAt(0));

        while (high < s.length()) {
            char ch = s.charAt(high);
            if ( !chs.contains(ch)) {
                chs.add(ch);
            }

            if (chs.size() > m) {
                maxLen = Math.max(maxLen, high - low);
                chs = new HashSet<>(); // important !!! clear
                low ++;
                high = low + 1;
                chs.add(s.charAt(low)); // impoartnt!!! add new low char
            }
            else {
                high ++;
            }
        }
        // important !!! rest elements.
        if (chs.size() > 0) {
            maxLen = Math.max(maxLen, high - low);
        }
        return maxLen;
    }
}
