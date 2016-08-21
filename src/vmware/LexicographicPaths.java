package vmware;

import java.util.Scanner;

/**
 * Created by yingtan on 10/24/15.
 */
public class LexicographicPaths {

    static String recurFormPath(int n, int k, int numV, int numH) {
        if (n == 1) {
            if ((numV > 0) && (numH == 0)) return "V";
            else return "H";
        }
        // choose H:
        int newNumH = numH - 1;
        int nextHGroupNum = 0;
        if (newNumH > 0) {
            nextHGroupNum = calCombinations(n - 1, newNumH); // choose one which is not zero
        } else {
            nextHGroupNum = calCombinations(n - 1, numV); // choose one which is not zero
        }

        if (k >= nextHGroupNum) {
            k = k - nextHGroupNum;
            numV --;
            return "V"+ recurFormPath(n-1, k, numV, numH);
        }
        else {
            numH --;
            return "H" + recurFormPath(n-1, k, numV, numH);
        }
    }

    static int calCombinations(int n, int k) {
        if (n == k) {
            return 1;
        }
        return (factorial(n-k+1,n) / (factorial(1, k)));
    }

    static int factorial(int low, int high) {
        int res  = 1;
        for (int i = low; i <= high; i ++) {
            res = res  * i;
        }
        return res;
    }

    public static void main(String[] args) {
        LexicographicPaths ob = new LexicographicPaths();
        Scanner scanner = new Scanner(System.in);
        int numLine = Integer.parseInt(scanner.nextLine());
        for (int i = 0 ; i < numLine; i ++) {
            String[] strs = scanner.nextLine().split(" ");
            int numH = Integer.parseInt(strs[0]);
            int numV = Integer.parseInt(strs[1]);
            int k = Integer.parseInt(strs[2]);

            int n = numV + numH;
            System.out.println(recurFormPath(n, k, numV, numH));
        }
    }
}
