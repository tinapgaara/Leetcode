package linkedin.array;

/**
 * Created by yingtan on 11/18/17.
 *
 * 243. Shortest Word Distance
 DescriptionHintsSubmissionsDiscussSolution
 Discuss Pick One
 Given a list of words and two words word1 and word2, return the shortest distance between these two words in the list.

 For example,
 Assume that words = ["practice", "makes", "perfect", "coding", "makes"].

 Given word1 = “coding”, word2 = “practice”, return 3.
 Given word1 = "makes", word2 = "coding", return 1.
 */
public class ShortestWordDistance {

    public int shortestDistance(String[] words, String word1, String word2) {
        if(words == null || word1 == null || word2 == null) {
            return 0;
        }
        int p1 = -1;
        int p2 = -1;
        int min = Integer.MAX_VALUE;
        for(int i = 0 ; i < words.length; i ++) {
            if (words[i].equals(word1)) {
                p1 = i;
            }
            else if (words[i].equals(word2)) {
                p2 = i;
            }
            // everytime, calculate the distance
            if (p1 != -1 && p2 != -1) {
                min = Math.min(min, Math.abs(p1 - p2));
            }
        }
        return min;
    }
}
