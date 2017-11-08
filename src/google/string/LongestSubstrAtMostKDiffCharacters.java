package google.string;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by yingtan on 7/19/17.
 */
public class LongestSubstrAtMostKDiffCharacters {
    public int lengthOfLongestSubstringKDistinct(String s, int k) {
        if (s == null || s.length() == 0) return 0;
        int start = 0;
        int maxLen = 0;
        // Key: letter; value: the number of occurrences.
        Map<Character, Integer> map = new HashMap<Character, Integer>();
        for (int i = 0 ; i < s.length() ; i ++) {
            char c = s.charAt(i);
            if (map.containsKey(c)) {
                map.put(c, map.get(c)+1);
            }
            else {
                map.put(c, 1);

                // move start
                while (map.size() > k) {
                    char startCh = s.charAt(start);
                    start ++;
                    int count = map.get(startCh);
                    if (count > 1) {
                        map.put(startCh, count - 1);
                    }
                    else {
                        map.remove(startCh);
                    }
                }
            }

            // then record the window
            maxLen = Math.max(maxLen, i - start + 1);
        }
        return maxLen;
    }
}
