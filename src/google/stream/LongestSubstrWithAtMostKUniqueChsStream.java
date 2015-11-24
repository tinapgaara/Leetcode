package google.stream;

import java.util.Map;

/**
 * Created by yingtan on 11/24/15.
 */
/*
* ，find the longestsubstring with at most m distinct characters in a stream that cannot fit intomemory， 用moving
window和LRUcache的思路解决
*
* */
public class LongestSubstrWithAtMostKUniqueChsStream {

    private int windowSize;
    private int windowLow;
    private int windowHigh;

    private int[] count;
    private int distinct;
    private int maxLen;

    private Map<Integer, ChObject> map; // map current a's index (0, -> <a, consectiveCounts(a)>)

    public LongestSubstrWithAtMostKUniqueChsStream(int k) {
        windowSize = k;
        windowLow = 0;
        windowHigh = 0;
        count = new int[256];
        distinct = 0;
        maxLen = 0;
    }

    public class ChObject {
        private Character ch;
        private int consecCounts;

        public ChObject(char ch, int counts) {
            this.ch = ch;
            consecCounts = counts;
        }
    }

    public int lengthOfLongestSubstringKDistinct(String stream) {

        for (int i = 0 ; i < stream.length(); i ++) {
            char ch = stream.charAt(i);
            store2Map(ch, windowHigh);
            windowHigh ++;
            if (count[ch] == 0) {
                distinct ++;
            }
            count[ch] ++; // operate on map

            while (distinct > windowSize) {
                char lowCh = stream.charAt(windowLow);
                count[lowCh] --;
                windowLow ++;
                if (count[lowCh] == 0) // operate on map
                    distinct --;
            }
            maxLen = Math.max(maxLen, windowHigh - windowLow);
        }
        return maxLen;
    }

    public void store2Map(char curCh, int prevChIndex) {
        ChObject prev = map.get(prevChIndex);
        if (prev == null) {
            // no consective
        }
    }

}