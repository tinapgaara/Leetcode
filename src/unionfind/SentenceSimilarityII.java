package unionfind;

/**
 * Created by yingtan on 3/4/18.
 *
 * 737. Sentence Similarity II
 DescriptionHintsSubmissionsDiscussSolution
 Pick One
 Given two sentences words1, words2 (each represented as an array of strings), and a list of similar word pairs pairs, determine if two sentences are similar.

 For example, words1 = ["great", "acting", "skills"] and words2 = ["fine", "drama", "talent"] are similar, if the similar word pairs are pairs = [["great", "good"], ["fine", "good"], ["acting","drama"], ["skills","talent"]].

 Note that the similarity relation is transitive. For example, if "great" and "good" are similar, and "fine" and "good" are similar, then "great" and "fine" are similar.

 Similarity is also symmetric. For example, "great" and "fine" being similar is the same as "fine" and "great" being similar.

 Also, a word is always similar with itself. For example, the sentences words1 = ["great"], words2 = ["great"], pairs = [] are similar, even though there are no specified similar word pairs.

 Finally, sentences can only be similar if they have the same number of words. So a sentence like words1 = ["great"] can never be similar to words2 = ["doubleplus","good"].

 Note:

 The length of words1 and words2 will not exceed 1000.
 The length of pairs will not exceed 2000.
 The length of each pairs[i] will be 2.
 The length of each words[i] and pairs[i][j] will be in the range [1, 20].

 word are transitive:  union find

 */

import java.util.HashMap;
import java.util.Map;

public class SentenceSimilarityII {
    public boolean areSentencesSimilarTwo(String[] words1, String[] words2, String[][] pairs) {
        if (words1 == null || words2 == null || pairs == null) return false;
        Map<String, String> parents = new HashMap<>();
        for (String[] p : pairs) {
            parents.put(p[0], p[0]);
            parents.put(p[1], p[1]);
        }
        for (String[] p : pairs) {
            String child = p[0];
            String tmpParent = p[1];
            String childP = findParent(parents, child);
            String parent = findParent(parents, tmpParent);
            if (childP != null && parent != null && ! childP.equals(parent)) {
                parents.put(childP, parent);// union
            }
        }
        if(words1.length != words2.length) return false;
        for (int i = 0 ; i < words1.length; i ++) {
            String word1 = words1[i];
            String word2 = words2[i];
            String word1Parent = findParent(parents, word1);
            String word2Parent = findParent(parents, word2);
            if (word1.equals(word2)) continue;
            if (word1Parent != null && word2Parent != null && word1Parent.equals(word2Parent)) continue;
            return false;
        }
        return true;
    }
    public String findParent(Map<String, String> parents, String cur) {
        if (parents.containsKey(cur)) {
            if (! parents.get(cur).equals(cur)) {
                // this is not root
                parents.put(cur, findParent(parents, parents.get(cur)));
            }
            return parents.get(cur);
        }
        return null;
    }
}
