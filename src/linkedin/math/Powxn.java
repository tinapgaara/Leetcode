package linkedin.math;

/**
 * Created by yingtan on 11/19/17.
 *
 * 50. Pow(x, n)
 DescriptionHintsSubmissionsDiscussSolution
 Discuss Pick One
 Implement pow(x, n).


 Example 1:

 Input: 2.00000, 10
 Output: 1024.00000
 Example 2:

 Input: 2.10000, 3
 Output: 9.26100
 */
public class Powxn {

    // x^n = x^(n/2) * x^(n/2) * x^(n%2)
    // eg: x^5 = x^2 * x^2 * x
    public double myPow(double x, int n) {
        if (n < 0) {
            return 1/ pow(x, -n);
        }
        else {
            return pow(x, n);
        }

    }

    public double pow(double x, int n) {
        if (n == 0) return 1;
        double half = pow(x, n / 2);
        if (n % 2 == 0) return half * half;
        else return half * half * x; // pow(x, n % 2)  -> x (because any number divide by 2: division is 0 or 1)
    }
}
