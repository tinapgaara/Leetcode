package hashtable;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by yingtan on 12/17/17.
 */
public class LongestSubstringWithAtMostKDistinctCharacters {

    public int lengthOfLongestSubstringKDistinct(String s, int k) {
        if (s == null || s.length() == 0) return 0;
        int start = 0;
        int end = 0;
        int max = Integer.MIN_VALUE;
        int distinct = 0;
        Map<Character, Integer> chToCount = new HashMap<>();
        for (; end < s.length(); end ++) {
            char ch = s.charAt(end);
            if (! chToCount.containsKey(ch)) {
                // increase distinct
                chToCount.put(ch, 1);
                distinct ++;
            }
            else {
                chToCount.put(ch, chToCount.get(ch) + 1);
            }

            while(distinct > k) {
                // smaller window until distinct == k
                char leftch = s.charAt(start);
                if (chToCount.containsKey(leftch)) {
                    if (chToCount.get(leftch) == 1) {
                        chToCount.remove(leftch);
                        distinct --;
                    }
                    else {
                        chToCount.put(leftch, chToCount.get(leftch) - 1);
                    }
                }
                start ++;
            }
            // record when extending righter
            max = Math.max(max, end - start + 1);
        }
        return max;
    }

    public int lengthOfLongestSubstringKDistinct_stream(String s, int k) {
        // eliminate elements automatically when size > k
        LinkedHashMap<Character, Integer> chToLatestIndex = new LinkedHashMap<>(s.length(), 1.0f, true);
        int max = 0;
        int start = 0;
        int distinct = 0;
        for (int i = 0 ; i < s.length(); i ++) {
            char ch = s.charAt(i);
            if (! chToLatestIndex.containsKey(ch)) {
                distinct ++;
            }
            chToLatestIndex.put(ch, i);
            if (distinct > k) {
                // eliminate the oldest entry
                Map.Entry oldest = getFirstEntry(chToLatestIndex);
                chToLatestIndex.remove(oldest.getKey());
                distinct --;
                // important !!!
                start = (Integer)oldest.getValue() + 1;
            }
            max = Math.max(max, i - start + 1);
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
