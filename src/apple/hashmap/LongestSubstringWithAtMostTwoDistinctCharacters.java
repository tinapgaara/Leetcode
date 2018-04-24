package apple.hashmap;
import java.util.*;
public class LongestSubstringWithAtMostTwoDistinctCharacters {
    public int lengthOfLongestSubstringTwoDistinct(String s) {
        if (s == null || s.length() == 0) return 0;
        return lengthOfLongestSubstringKDistinct(s, 2);
    }
    public int lengthOfLongestSubstringKDistinct(String s, int k) {
        LinkedHashMap<Character, Integer> chToLatestIndex = new LinkedHashMap<>(s.length(), 0.1f, true);
        int start = 0;
        int end = 0;
        int max = 0;
        while(end < s.length()) {
            char ch = s.charAt(end);
            chToLatestIndex.put(ch, end);
            if (chToLatestIndex.size() > k) {
                // eliminate oldest
                Map.Entry<Character, Integer> firstEntry = getHeadEntry(chToLatestIndex);
                int oldestIndex = firstEntry.getValue();
                start = oldestIndex + 1;
                chToLatestIndex.remove(firstEntry.getKey());
            }
            max = Math.max(max, end - start + 1);
            end ++;
        }
        max = Math.max(max, end - getHeadEntry(chToLatestIndex).getValue());
        return max;
    }
    public Map.Entry<Character, Integer> getHeadEntry(LinkedHashMap<Character, Integer> linkedlist) {
        for (Map.Entry<Character, Integer> entry : linkedlist.entrySet()) {
            return entry;
        }
        return null;
    }
    public static void main(String[] args) {
        LongestSubstringWithAtMostTwoDistinctCharacters ob = new LongestSubstringWithAtMostTwoDistinctCharacters();
        ob.lengthOfLongestSubstringKDistinct("eceba",2);
    }
}
