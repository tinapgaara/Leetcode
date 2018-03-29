package BitOperation;

/**
 * Created by yingtan on 2/8/18.
 * // only given a random generator that produces zero/one with equal probability,
 * // wants to use this to produce a number between [a,b] with equal probability
 *
 * each call to random generator can be taken as a bit in integer
 * [a, b] -> [0,  b-a]
 *
 * Call i numbers: generate numbers between [0, 2^i - 1]
 * Steps: generates a number call i times, if lies between [0, b-a], return, else, we try again with i new random bits.
 *
 * Finally, get a number, returns number + a since we firstly deduct a.
 */
import java.util.*;
public class GenerateUniformNumber {
    public int uniformRandom(int lower, int upper) {
        upper = upper - lower;
        //lower = 0;
        int result = 0;
        while(result >= upper) {
            // generate number
            for (int i = 0 ; (1 << i) < upper; i ++) {
                int bit = zeroOneRandom();
                result = (result << 1) | bit;
            }
        }
        // important!!!
        return result + lower;
    }

    public int zeroOneRandom() {
        Random r = new Random();
        return r.nextInt(1);
    }
}
