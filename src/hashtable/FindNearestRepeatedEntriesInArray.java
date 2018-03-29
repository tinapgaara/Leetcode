package hashtable;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by yingtan on 12/16/17.
 */
public class FindNearestRepeatedEntriesInArray {

    public int findNearest(String[] words) {
        Map<String, Integer> wordToLatestIndex = new HashMap<>();
        int min = Integer.MAX_VALUE;
        for (int i = 0 ; i < words.length;i ++) {
            String word = words[i];
            if (wordToLatestIndex.containsKey(word)) {
                min = Math.min(min, i - wordToLatestIndex.get(word) + 1);
            }
            wordToLatestIndex.put(word, i);
        }
        return min;
    }
}
