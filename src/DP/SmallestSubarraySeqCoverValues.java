package DP;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by yingtan on 4/10/18.
 */
public class SmallestSubarraySeqCoverValues {
    // take two arrays, return start,end indices of the shortest array that seq cover the second array
    public int minSubarrayCoverAllValue(String[] paragraphs, String[] keywords) {
        int[] dpLenEnds = new int[keywords.length];
        int[] latestOccur = new int[keywords.length];
        Map<String, Integer> keywordToIndex = new HashMap<>();
        for (int i = 0 ; i < keywords.length; i ++) {
            keywordToIndex.put(keywords[i], i);
            dpLenEnds[i] = Integer.MAX_VALUE;
        }
        for (int i = 0 ; i < paragraphs.length; i ++) {
            String word = paragraphs[i];
            if (keywordToIndex.containsKey(word)) {
                int index = keywordToIndex.get(word);
                if (index == 0) {
                    dpLenEnds[index] = 1;
                }
                else {
                    if (dpLenEnds[index - 1] != Integer.MAX_VALUE) {
                        int prevIndex = latestOccur[index - 1];
                        int dist = i - prevIndex;
                        dpLenEnds[index] = Math.min(dpLenEnds[index], dpLenEnds[index- 1] + dist);
                    }
                }
                latestOccur[index] = i;
            }
        }
        return dpLenEnds[keywords.length - 1];
    }
}
