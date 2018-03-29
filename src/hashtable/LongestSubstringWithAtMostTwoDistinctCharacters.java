package hashtable;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collector;

/**
 * Created by yingtan on 12/17/17.
 */
public class LongestSubstringWithAtMostTwoDistinctCharacters {

    public int lengthOfLongestSubstringTwoDistinct(String s) {
        if (s == null || s.length() == 0) return 0;
        Map<Character, Integer> chToCount = new HashMap<>();
        int start = 0;
        int end = 0;
        int max =  0;
        int distinct = 0;
        for (; end < s.length(); end ++) {
            char ch = s.charAt(end);
            // keep extending righter
            if (! chToCount.containsKey(ch)) {
                chToCount.put(ch, 1);
                distinct ++;
            }
            else {
                chToCount.put(ch, chToCount.get(ch)+1);
            }
            while(distinct > 2) {
                // smaller window until distinct <= 2 , can not work in streaming way because you need to store String s, s.charAt(start)
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
            max = Math.max(max, end - start + 1);
        }
        return max;
    }

    public int lengthOfLongestSubstringTwoDistinct_stream(String s, int k) {
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
            if (distinct > 2) {
                // eliminate the oldest entry
                Map.Entry oldest = getFirstEntry(chToLatestIndex);
                chToLatestIndex.remove(oldest.getKey());
                distinct --;
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

    public static void main(String[] args) {
        LongestSubstringWithAtMostTwoDistinctCharacters ob = new LongestSubstringWithAtMostTwoDistinctCharacters();
        ob.lengthOfLongestSubstringTwoDistinct_stream("eceeba", 2);
    }
}
