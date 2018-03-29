package random;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * Created by yingtan on 1/8/18.
 */
public class RandomPermutation {

    public List<Integer> randomPermutation(int n) {
        List<Integer> res = new ArrayList<>(n);
        for (int i = 0; i < n ; i++) {
            res.add(i + 1);
        }
        Random rnd = new Random();
        for (int i = 0; i < n; i ++) {
            // put the random element at index i
            int randIndex = rnd.nextInt(n - i);
            Collections.swap(res, i, i + randIndex);
        }
        return res;
    }
}
