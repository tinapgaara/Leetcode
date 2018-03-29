package linkedin.practice;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by yingtan on 11/22/17.
 *
 *  For example,
 Assume that words = ["practice", "makes", "perfect", "coding", "makes"].

 Given word1 = “coding”, word2 = “practice”, return 3.
 Given word1 = "makes", word2 = "coding", return 1.

 Note:
 You may assume that word1 does not equal to word2, and word1 and word2 are both in the list.
 */
public class ShortestWordDistanceII {

    // time: o(n), space: o(n)
    Map<String, List<Integer>> map = new HashMap<>();
    public ShortestWordDistanceII(String[] words) {
        for (int i = 0 ; i < words.length; i ++) {
            if (map.containsKey(words[i])) {
                map.get(words[i]).add(i);
            }
            else {
                map.put(words[i], new ArrayList<>(i));
            }
        }
    }
    public int shortest(String word1, String word2) {
        int min = Integer.MAX_VALUE;
        List<Integer> indexs1 = map.get(word1);
        List<Integer> indexs2 = map.get(word2);
        int i = 0 ; int j = 0;
        while(i < indexs1.size() && j < indexs2.size()) {
            min = Math.min(min, Math.abs(indexs1.get(i)-indexs2.get(j)));
            if (indexs1.get(i) < indexs2.get(j)) {
                i ++;
            }
            else if (indexs1.get(i) > indexs2.get(j)) {
                j ++;
            }
            else {
                return 0;
            }
        }
        return min;
    }
}
