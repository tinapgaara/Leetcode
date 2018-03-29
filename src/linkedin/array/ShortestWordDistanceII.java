package linkedin.array;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by yingtan on 11/18/17.
 *
 * 244. Shortest Word Distance II
 DescriptionHintsSubmissionsDiscussSolution
 Discuss Pick One
 This is a follow up of Shortest Word Distance.
 The only difference is now you are given the list of words and your method
 will be called repeatedly many times with different parameters. How would you optimize it?

 Design a class which receives a list of words in the constructor, and implements a method
 that takes two words word1 and word2 and return the shortest distance between
 these two words in the list.

 For example,
 Assume that words = ["practice", "makes", "perfect", "coding", "makes"].

 Given word1 = “coding”, word2 = “practice”, return 3.
 Given word1 = "makes", word2 = "coding", return 1.

 Note:
 You may assume that word1 does not equal to word2, and word1 and word2 are both in the list.
 */
public class ShortestWordDistanceII {

    Map<String, List<Integer>> map = new HashMap<>();
    public ShortestWordDistanceII(String[] words) {
        if(words == null) return;
        for (int i = 0 ; i < words.length; i ++) {
            if (map.containsKey(words[i])) {
                map.get(words[i]).add(i);
            }
            else {
                List<Integer> indexs = new ArrayList<>();
                indexs.add(i);
                map.put(words[i], indexs);
            }
        }
    }

    public int shortest(String word1, String word2) {
        int min = Integer.MAX_VALUE;
        if(map.containsKey(word1) && map.containsKey(word2)) {
            List<Integer> index1s = map.get(word1);
            List<Integer> index2s = map.get(word2);
            // o(n)
            int p1 = 0;
            int p2 = 0;
            while(p1 < index1s.size() && p2 < index2s.size()) {
                if (index1s.get(p1) < index2s.get(p2)) {
                    min = Math.min(min, index2s.get(p2) - index1s.get(p1));
                    p1 ++;
                }
                else if (index1s.get(p1) > index2s.get(p2)) {
                    min = Math.min(min, index1s.get(p1) - index2s.get(p2));
                    p2 ++;
                }
                else {
                    // equals
                    min = 0;
                    return min;
                }
            }
        }
        return min;
    }
}
