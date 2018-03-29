package hashtable;

/**
 * Created by yingtan on 3/4/18.
 *
 * 734. Sentence Similarity
 DescriptionHintsSubmissionsDiscussSolution
 Pick One
 Given two sentences words1, words2 (each represented as an array of strings), and a list of similar word pairs pairs, determine if two sentences are similar.

 For example, "great acting skills" and "fine drama talent" are similar, if the similar word pairs are pairs = [["great", "fine"], ["acting","drama"], ["skills","talent"]].

 Note that the similarity relation is not transitive. For example, if "great" and "fine" are similar, and "fine" and "good" are similar, "great" and "good" are not necessarily similar.

 However, similarity is symmetric. For example, "great" and "fine" being similar is the same as "fine" and "great" being similar.

 Also, a word is always similar with itself. For example, the sentences words1 = ["great"], words2 = ["great"], pairs = [] are similar, even though there are no specified similar word pairs.

 Finally, sentences can only be similar if they have the same number of words. So a sentence like words1 = ["great"] can never be similar to words2 = ["doubleplus","good"].

 Note:

 The length of words1 and words2 will not exceed 1000.
 The length of pairs will not exceed 2000.
 The length of each pairs[i] will be 2.
 The length of each words[i] and pairs[i][j] will be in the range [1, 20].

 one word can map to multiple words
 Map<String, Set<String>>
 */
import java.util.*;
public class SentenceSimilartity {

    public boolean areSentencesSimilar(String[] words1, String[] words2, String[][] pairs) {
        if (words1 == null || words2 == null || pairs == null) return false;
        Map<String, Set<String>> map = new HashMap<>();
        for (String[]  p : pairs) {
            if (map.containsKey(p[0])) {
                Set<String> set = map.get(p[0]);
                set.add(p[1]);
                map.put(p[0], set);
            }
            else {
                Set<String> set = new HashSet<>();
                set.add(p[1]);
                map.put(p[0], set);
            }
        }
        if (words1.length  != words2.length) return false;
        for (int i = 0 ; i < words1.length; i ++) {
            String word1 = words1[i];
            String word2 = words2[i];
            if (word1.equals(word2)) continue;
            if (map.containsKey(word1) && map.get(word1).contains(word2)) continue;
            if (map.containsKey(word2) && map.get(word2).contains(word1)) continue;
            return false;
        }
        return true;
    }
}
