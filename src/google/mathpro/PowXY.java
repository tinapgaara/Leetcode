package google.mathpro;

/**
 * Created by yingtan on 11/27/15.
 */
/*
*  x^n = x^(n/2) * x^(n/2) * x^(n%2)
* */
public class PowXY {

    public double myPow(double x, int n) {
        if (n < 0) {
            return 1/ pow(x, -n);
        }
        else
            return pow(x, n);
    }

    public double pow(double x, int n) {
        if (n == 0) return 1;
        double half = pow(x, n / 2);

        if (n % 2 == 0) {
            return half * half ;
        }
        else {
            return half * half * x; // pow(x, n % 2)  -> x (because any number divide by 2: division is 0 or 1)
        }

    }



}
