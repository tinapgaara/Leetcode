package facebook.math;

/**
 * Created by yingtan on 2/19/18.
 *
 * 0. Pow(x, n)
 DescriptionHintsSubmissionsDiscussSolution
 Pick One
 Implement pow(x, n).


 Example 1:

 Input: 2.00000, 10
 Output: 1024.00000
 Example 2:

 Input: 2.10000, 3
 Output: 9.26100
 */
public class PowX {
    public double myPow(double x, int n) {
        if (n < 0) {
            // can not call myPow this function here, since boundary case: -2147483648 , -n still equals to -2147483648,
            // and if you consistently check <0 , and then call myPow, will stackoverflow.
            // return 1/(myPow(x, -n));
            // here, must use another function pow separately!!!!
            return 1/pow(x, -n);
        }
        else {
            return pow(x, n);
        }
    }
    public double pow(double x, int n) {
        if (n == 0) return 1;
        if (n == 1) return x;
        if (n % 2 == 0) {
            double half = pow(x, n/2 );
            return half * half;
        }
        else {
            double half = pow(x, n/2 );
            return half * half * x;
        }
    }
}
