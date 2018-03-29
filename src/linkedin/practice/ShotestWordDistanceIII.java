package linkedin.practice;

/**
 * Created by yingtan on 11/22/17.
 *
 * Given a list of words and two words word1 and word2, return the shortest distance
 * between these two words in the list.

 word1 and word2 may be the same and they represent two individual words in the list.

 For example,
 Assume that words = ["practice", "makes", "perfect", "coding", "makes"].

 Given word1 = “makes”, word2 = “coding”, return 1.
 Given word1 = "makes", word2 = "makes", return 3.
 */
public class ShotestWordDistanceIII {

    // time: o(n)
    public int shortestWordDistance(String[] words, String word1, String word2) {
        if (words == null || words.length == 0) return 0;
        int p1 = -1;
        int p2 = -1;
        int min = Integer.MAX_VALUE;
        boolean isEquals = false;
        if(word1.equals(word2)) {
            isEquals = true;
        }
        for (int i = 0 ; i < words.length; i ++) {
            if(isEquals) {
                if(p1 != -1) {
                    min = Math.min(min, i - p1);
                }
                p1 = i;
            }
            else {
                if (words[i].equals(word1)) {
                    p1 = i;
                }
                else if (words[i].equals(word2)) {
                    p2 = i;
                }
                if (p1 != -1 && p2 != -1) {
                    min = Math.min(min, Math.abs(p1 - p2));
                }
            }
        }
        return min;
    }
}
