package apple.hashmap;
import java.util.*;
public class LongestSubstringWithoutRepeatCharacters {
    public int lengthOfLongestSubstring(String s) {
        if (s == null || s.length() == 0) return 0;
        Map<Character, Integer> chToIndex = new HashMap<>();
        int start = 0;
        int end = 0;
        int max = 0;
        while(end < s.length()) {
            char ch = s.charAt(end);
            if (start <= end && chToIndex.containsKey(ch)) {
                if (chToIndex.get(ch) >= start) { // very important !!!
                    max = Math.max(max, end - start);
                    start = chToIndex.get(ch) + 1;
                }
            }
            chToIndex.put(ch, end);
            end ++;
        }
        max = Math.max(max, end - start);
        return max;
    }
}
