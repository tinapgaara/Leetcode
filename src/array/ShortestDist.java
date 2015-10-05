package array;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yingtan on 9/23/15.
 */
public class ShortestDist {

    public int shortestDistance(String[] words, String word1, String word2) {
        if ((words == null) || (words.length == 0))
            return 0;
        List<Integer> pos1 = new ArrayList<Integer>();
        List<Integer> pos2 = new ArrayList<Integer>();

        for (int i = 0 ; i < words.length; i ++) {
            String word = words[i];
            if (word.equals(word1)) {
                pos1.add(i);
            }
            else if (word.equals(word2)) {
                pos2.add(i);
            }
        }
        int min = Integer.MAX_VALUE;
        for (Integer pos_1 : pos1) {
            for (Integer pos_2 : pos2) {
                int dist = Math.abs(pos_1.intValue() - pos_2.intValue());

                min = Math.min(min, dist);
            }
        }
        return min;
    }
}
