package facebook.hashmap;

import java.util.LinkedHashMap;

/**
 * Created by yingtan on 3/17/18.
 *
 * Given a string, find the length of the longest substring T that contains at most 2 distinct characters.

 For example, Given s = “eceba”,

 T is "ece" which its length is 3.
 */
import java.util.*;
public class LongestSubstringAtMostKDistinctCharacters {
    public int lengthOfLongestSubstringKDistinct(String s, int k) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        LinkedHashMap<Character, Integer> chToLatestIndex = new LinkedHashMap<>(s.length(), 1.0f, true);
        int max = 0;
        int start = 0;
        for (int i = 0 ; i < s.length(); i ++) {
            char ch = s.charAt(i);
            chToLatestIndex.put(ch, i);
            if (chToLatestIndex.size() > k) {
                // full, need to remove oldest one
                Map.Entry<Character, Integer> firstEntry = getHeadEntry(chToLatestIndex);
                int oldestIndex = firstEntry.getValue();
                start = oldestIndex + 1;
                chToLatestIndex.remove(firstEntry.getKey());
            }
            max = Math.max(max, i - start + 1);
        }
        max = Math.max(max, s.length() - getHeadEntry(chToLatestIndex).getValue());
        return max;
    }
    public Map.Entry<Character, Integer> getHeadEntry(LinkedHashMap<Character, Integer> linkedlist) {
        for (Map.Entry<Character, Integer> entry : linkedlist.entrySet()) {
            return entry;
        }
        return null;
    }
    public int lengthOfLongestSubstringTwoDistinct(String s) {
        LinkedHashMap<Character, Integer> chToLatestIndex = new LinkedHashMap<>(s.length(), 1.0f, true);
        int max = 0;
        int start = 0;
        int distinct = 0;
        for (int i = 0 ; i < s.length(); i ++) {
            char ch = s.charAt(i);
            max = Math.max(max, i - start);
            if (! chToLatestIndex.containsKey(ch)) {
                distinct ++;
            }
            chToLatestIndex.put(ch, i);
            if (distinct > 2) {
                // eliminate the oldest entry
                Map.Entry oldest = getFirstEntry(chToLatestIndex);
                chToLatestIndex.remove(oldest.getKey());
                distinct --;
                start = (Integer)oldest.getValue() + 1;
            }
        }
        max = Math.max(max, s.length() - start);
        return max;
    }

    public Map.Entry getFirstEntry(LinkedHashMap<Character, Integer> chToLatestIndex) {
        for (Map.Entry<Character, Integer> entry : chToLatestIndex.entrySet()) {
            return entry;
        }
        return null;
    }

}
