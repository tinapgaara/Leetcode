package google.dp;

/**
 * Created by yingtan on 12/14/15.
 */
public class SuperUglyNumber {

    public int nthSuperUglyNumber(int n, int[] primes) {
        int[] dp = new int[n];
        dp[0] = 1;
        int[] primeIndexs = new int[primes.length];
        int i = 1;
        while(i < n) {
            int min = Integer.MAX_VALUE;
            int minPrimeIndex = 0;
            for (int j = 0 ; j < primes.length ; j ++) {
                int val = dp[primeIndexs[j]] * primes[j];
                if (val < min) {
                    min = val;
                    minPrimeIndex = j;
                }
            }
            primeIndexs[minPrimeIndex]++;
            if (min != dp[i-1]) { // very important !!!!
                dp[i] = min;
                i ++;
            }
        }
        return dp[n-1];
    }

    public static void main(String[] args) {
        SuperUglyNumber ob = new SuperUglyNumber();
        int[] primes = new int[]{2,7,13,19};
        System.out.println(ob.nthSuperUglyNumber(12, primes));
    }
}
