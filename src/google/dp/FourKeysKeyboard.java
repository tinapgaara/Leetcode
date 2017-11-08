package google.dp;

/**
 * Created by yingtan on 11/5/17.
 *
 * 651. 4 Keys Keyboard
 DescriptionHintsSubmissionsDiscussSolution
 Discuss Pick One
 Imagine you have a special keyboard with the following keys:

 Key 1: (A): Print one 'A' on screen.

 Key 2: (Ctrl-A): Select the whole screen.

 Key 3: (Ctrl-C): Copy selection to buffer.

 Key 4: (Ctrl-V): Print buffer on screen appending it after what has already been printed.

 Now, you can only press the keyboard for N times (with the above four keys), find out the maximum numbers of 'A' you can print on screen.

 Example 1:
 Input: N = 3
 Output: 3
 Explanation:
 We can at most get 3 A's on screen by pressing following key sequence:
 A, A, A
 Example 2:
 Input: N = 7
 Output: 9
 Explanation:
 We can at most get 9 A's on screen by pressing following key sequence:
 A, A, A, Ctrl A, Ctrl C, Ctrl V, Ctrl V
 */
public class FourKeysKeyboard {

    public int maxA(int N) {
        int[] dp = new int[N+1];
        for(int i = 1; i <= N; i++){
            dp[i] = i;
            // j starts with 3 because at least [a, Ctrl-A, Ctrl-C] are before Ctrl-V
            for(int j = 3; j < i; j ++){
                // [i-j] all is Ctrl-V, Ctrl-V
                // [j-1] max a  *
                dp[i] = Math.max(dp[i], dp[j-1] * (i-j));
            }
        }
        return dp[N];
    }
}
