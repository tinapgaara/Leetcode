package linkedin.practice;

/**
 * Created by yingtan on 11/22/17.
 */
public class Powx {
    // x^n = x^n/2
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
    public static void main(String[] args) {
        Powx ob = new Powx();
        ob.myPow(1.0, -2147483648);
    }
}
