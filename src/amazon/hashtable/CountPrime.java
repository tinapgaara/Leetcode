package amazon.hashtable;

/**
 * Created by yingtan on 4/2/18.
 *
 * 204. Count Primes
 DescriptionHintsSubmissionsDiscussSolution
 Pick One
 Description:

 Count the number of prime numbers less than a non-negative number, n.

 Credits:
 Special thanks to @mithmatt for adding this problem and creating all test cases.


 */
public class CountPrime {
    public int countPrimes(int n) {
        if (n == 0) return 0;
        boolean[] noPrime = new boolean[n];
        int count = 0;
        for (int i = 2; i < n ; i ++) {
            if (! noPrime[i]) {
                count ++;
                for (int j = 2; j * i < n ; j ++) {
                    noPrime[j *i] = true;
                }
            }
        }
        return count;
    }
}
