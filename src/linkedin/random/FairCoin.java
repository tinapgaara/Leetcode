package linkedin.random;


import java.util.Random;

/**
 * Created by erict on 2017/11/29.
 */
public class FairCoin {

    // n is inclusive
    public int random(int n) {
        if (n == 0) return 0;
        int k = 0;
        int num = 1;
        while (num <= n) {
            num = num << 1;
            k++;
        }

        int result = 0;
        while (true) {
            for (int i = 0; i < k; i++) {
                result = result << 1;
                int r = throwCoin();
                if (r == 1) result += 1;
            }
            if (result <= n) break;
            else result = 0;
        }
        return result;
    }

    private int throwCoin() {
        Random random = new Random();
        return random.nextInt(2); // 2 exclusive
    }

    // m and n are inclusive
    public int random2(int m, int n) {
        return m + random(n - m);
    }

    public static void main(String args[]) {
        FairCoin coin = new FairCoin();
        //*
        for (int i = 0; i < 100; i++) {
            System.out.println("result = [" + coin.random(2) + "]");
        }
        //*/

        /*
        for (int i = 0; i < 100; i++) {
            System.out.println("result = [" + coin.random2(8, 18) + "]");
        }
        //*/
    }

}
