package mathmatics;

/**
 * Created by yingtan on 9/2/15.
 */
public class prime {

    public static boolean ifPrime(int n) {
        if (n == 1) {
            return false;
        }
        else if ((n == 2) || (n ==3) ) {
            return true;
        }
        else if ((n % 2 == 0) || (n % 3 == 0)) {
            return false;
        }

        for (int i = 2; i * i < n; i ++) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;

    }

    public static void main(String[] args) {
        System.out.println(prime.ifPrime(9));
    }
}
