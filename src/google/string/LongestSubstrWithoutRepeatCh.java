package google.string;

/**
 * Created by yingtan on 12/22/15.
 */
import java.util.*;
public class LongestSubstrWithoutRepeatCh {
    public int lengthOfLongestSubstring(String s) {
        if ((s == null) || (s.length() == 0))
            return 0;
        Map<Character, Integer> chToLastesIndex = new HashMap<>();
        int start = 0;
        int end = 0;
        int max = 0;
        // when doing streaming
        for (; end < s.length(); end ++) {
            char ch = s.charAt(end);
            if (chToLastesIndex.containsKey(ch)) {
                int dupIndex = chToLastesIndex.get(ch);
                if (dupIndex > start) {
                    // valid one
                    max = Math.max(max, end - start); // end is dup one, ignore
                    start = dupIndex + 1;
                }
            }
            chToLastesIndex.put(ch, end);
        }
        // all distinct
        max = Math.max(max, end - start);
        return max;
    }
    public int lengthOfLongestSubstring_stream(Iterator<Character> iter) {
        Map<Character, Integer> chToLastesIndex = new HashMap<>();
        int start = 0;
        int end = 0;
        int max = 0;
        // when doing streaming
        while(iter.hasNext()) {
            char ch = iter.next();
            if (chToLastesIndex.containsKey(ch)) {
                int dupIndex = chToLastesIndex.get(ch);
                if (dupIndex > start) {
                    // valid one
                    max = Math.max(max, end - start); // end is dup one, ignore
                    start = dupIndex + 1;
                }
            }
            chToLastesIndex.put(ch, end);
            end ++;
        }
        // all distinct
        max = Math.max(max, end - start);
        return max;
    }
}
