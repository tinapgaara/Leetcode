package linkedin.practice;

/**
 * Created by yingtan on 11/22/17.
 *
 * For example,
 Assume that words = ["practice", "makes", "perfect", "coding", "makes"].

 Given word1 = “coding”, word2 = “practice”, return 3.
 Given word1 = "makes", word2 = "coding", return 1.
 */
public class ShortestWordDistance {

    // time: o(n)
    public int shortestDistance(String[] words, String word1, String word2) {
        if (words == null || word1 == null || word2 == null) {
            return 0;
        }
        int p1 = -1;
        int p2 = -1;
        int min = Integer.MAX_VALUE;
        for (int i = 0 ; i < words.length; i ++) {
            if (words[i].equals(word1)) {
                p1 = i;
            }
            else if (words[i].equals(word2)) {
                p2 = i;
            }
            if (p1 != -1 && p2 != -1) {
                min = Math.min(min, Math.abs(p1- p2));
            }
        }
        return min;
    }
}
