package hashtable;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by yingtan on 12/17/17.
 *
 * Given a string, find the length of the longest substring without repeating characters.

 Examples:

 Given "abcabcbb", the answer is "abc", which the length is 3.

 Given "bbbbb", the answer is "b", with the length of 1.

 Given "pwwkew", the answer is "wke", with the length of 3. Note that the answer must be a substring, "pwke" is a subsequence
 */
public class LongestSubstringWithoutRepeatingCharacters {

    // also works in streaming way, do not need to keep s in memory, just need to keep start, end and max
    public int lengthOfLongestSubstring(String s) {
        if (s == null || s.length() == 0) return 0;
        int start = 0;
        int end = 0;
        int max = 0;
        Map<Character, Integer> chToLatestIndex = new HashMap<>();
        for (; end < s.length(); end ++) {
            char ch = s.charAt(end);
            if (chToLatestIndex.containsKey(ch)) {
                int dupIndex = chToLatestIndex.get(ch);
                // in the window
                // Important !!!!
                if (dupIndex >= start) {
                    // ignore the current end . Important !!!!
                    max = Math.max(max, end - start);
                    start = dupIndex + 1;
                }
            }
            chToLatestIndex.put(ch, end);
        }
        //Important !!!!
        max = Math.max(max, end - start);
        return max;
    }
}
