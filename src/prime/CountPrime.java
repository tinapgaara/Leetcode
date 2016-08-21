package prime;

/**
 * Created by yingtan on 9/19/15.
 */
public class CountPrime {

    // Solution 1: quicker
    public int countPrimes_sieveOfEratosthenes(int n) {
        boolean[] crossedFlag = new boolean[n];

        if ((n == 1) || (n == 2)){
            return 0;
        }
        else if ((n == 3)) {
            return 1;
        }
        int count  = 0 ;

        for (int i = 2; i < n; i ++) {
            if ( ! crossedFlag[i]) {
                count ++;
                for (int j = 2; i * j < n; j ++) {
                    int crossedIndex = i * j;
                    crossedFlag[crossedIndex] = true;
                }
            }
        }
        return count;
    }

    // Solution 2: for ends square_n
    public int countPrimes_squareN(int n) {
        int count = 0;
        for (int i = 1 ; i < n; i ++) {
            if (isPrime(n)) {
                count++;
            }
        }
        return count;
    }
    public boolean isPrime(int num) {
        if (num == 1){
            return false;
        }
        else if ((num == 2) || (num == 3)) {
            return true;
        }
        else if ( (num % 2 == 0) || (num % 3 == 0) ) {
            return false;
        }
        else {
            for (int i = 2 ; i * i < num ; i ++) {
                if (num % i == 0) {
                    return false;
                }
            }
            return true;
        }
    }
}
