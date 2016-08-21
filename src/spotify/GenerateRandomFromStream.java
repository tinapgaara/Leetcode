package spotify;

import java.util.Iterator;
import java.util.Random;

/**
 * Created by yingtan on 11/19/15.
 */
public class GenerateRandomFromStream {

    public int[] generateKRandomFromStream(Iterator<Integer> stream, int k, int n) {
        int[] res = new int[k];

        for (int i = 0 ; i < k; i ++) {
            res[i] = stream.next();
        }

        Random rand = new Random();
        for (int i = k ; i < n ; i ++) {
            int element = stream.next();
            int randIndex = rand.nextInt(i);

            if (randIndex < k) {
                res[randIndex] = element;
            }
        }
        return res;
    }
}
