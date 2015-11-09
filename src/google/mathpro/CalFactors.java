package google.mathpro;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yingtan on 11/7/15.
 */
public class CalFactors {

    // 36:
    // 1 2 3 4 6 9 12 18 36
    // if (a < b)
    // if (a < square(n)) then (b > square(n))
    //  or a == b == square(n)
    public List<Integer> calFactors(int n) {
        List<Integer> factors = new ArrayList<>();
        for (int i = 1; i * i <= n ; i ++) {
            if (n % i == 0) {
                factors.add(i);

                if (i != Math.sqrt(n)) {
                    factors.add(n / i); // avoid duplicate 6
                }
            }
        }
        return factors;
    }
}
