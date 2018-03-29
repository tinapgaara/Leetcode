package array;

import java.util.*;

/**
 * Created by yingtan on 12/20/17.
 */
public class EnumerateAllPrimesToN {

    public List<Integer> allPrimes(int n) {
        List<Integer> res = new ArrayList<>();
        boolean[] isPrime = new boolean[n];
        Arrays.fill(isPrime, true);
        isPrime[0] = false;
        isPrime[1] = false;
        for (int i = 2; i <= n ; i ++) {
            if (isPrime[i]) {
                res.add(i);
                // then set its multiples not be a prime
                for (int j = i; j <= n;j += i) {
                    // i, 2*i, 3*i ,4 *i  (j += i)
                    isPrime[j] = false;
                }
            }
        }
        return res;
    }
}
