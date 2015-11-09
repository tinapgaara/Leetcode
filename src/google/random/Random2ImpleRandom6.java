package google.random;

import java.util.Random;

/**
 * Created by yingtan on 11/7/15.
 */
public class Random2ImpleRandom6 {
    // rand6 - > rand2
    // rand2 -> rand(>=6) -> rand6
    // need to generate 0,1,2,3,4,5

    // Solution 1:
    // each bit: 0/1
    // rand5:  101
    // generate each digit using rand(2)
    public int generateRandom5 () {
        int sum = 0;
        Random random = new Random();
        /*
        while (true) {
            for (int i = 0; i < 2; i++) {
                int randomBit = random.nextInt(2); // generate 0  1
                sum = sum + randomBit * (int) Math.pow(2.0, i);
            }
            if (sum <= 5) return sum;
        }
        */

        while(true) {
            sum = 4 * random.nextInt(2) + 2 * random.nextInt(2) + random.nextInt(2);
            if (sum <= 5) break;
        }
        return 0;
    }
}