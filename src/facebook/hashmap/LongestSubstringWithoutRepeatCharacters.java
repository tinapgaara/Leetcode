package facebook.hashmap;

/**
 * Created by yingtan on 3/17/18.
 */
import java.util.*;
public class LongestSubstringWithoutRepeatCharacters {

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
