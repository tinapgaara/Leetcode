package facebook.dp;

/**
 * Created by yingtan on 2/11/18.
 *
 * 639. Decode Ways II
 DescriptionHintsSubmissionsDiscussSolution
 Pick One
 A message containing letters from A-Z is being encoded to numbers using the following mapping way:

 'A' -> 1
 'B' -> 2
 ...
 'Z' -> 26
 Beyond that, now the encoded string can also contain the character '*', which can be treated as one of the numbers from 1 to 9.

 Given the encoded message containing digits and the character '*', return the total number of ways to decode it.

 Also, since the answer may be very large, you should return the output mod 109 + 7.

 Example 1:
 Input: "*"
 Output: 9
 Explanation: The encoded message can be decoded to the string: "A", "B", "C", "D", "E", "F", "G", "H", "I".
 Example 2:
 Input: "1*"
 Output: 9 + 9 = 18

 Explanation: The encoded message can be decoded to the string: "A", "B", "C", "D", "E", "F", "G", "H", "I".
 _   _ : encode the last digit:
 1-9 : dp[i] = dp[i-1] + dp[i]         -- case 1
 *   : dp[i] = dp[i] + dp[i-1] * 9    -- case 2

 _     _  : encode last two digits:
 10-26  : dp[i] = dp[i] + dp[i-2]        -- case 3
 1    *  : dp[i] = dp[i] + dp[i-2] * 9    -- case 4
 2    *   : dp[i] = dp[i] + dp[i-2] * 6   -- case 5
 *    *   : dp[i] = dp[i] + dp[i-2] * 15 -- case 6
 *  (0-6) : dp[i] = dp[i] + dp[i-2] * 2  -- case 7
 *  (7-9) : dp[i] = dp[i] + dp[i-2] * 1  -- case 8

 */
public class DecodeWaysII {

    public int numDecodings(String s) {
        if (s == null || s.length() == 0) return 0;
        // important !!! must use long
        long[] dp = new long[s.length() + 1]; // dp_i -> s.charAt(i-1)
        dp[0] = 1;
        // serverl cases: **, 1*, 2*, *(number), (number)(number)
        for (int i = 0 ; i < s.length(); i ++) {
            if (s.charAt(i) == '*') { // case 2, case 4, case 5, case 6
                dp[i+1] = dp[i] * 9; // _*, decode * as _A-_Z  case 2
                if (i > 0) { // encode last two digits
                    if (s.charAt(i-1) == '*') { // **  case 6
                        dp[i+1] = dp[i+1] + dp[i-1] * 15;
                    }
                    else if (s.charAt(i -1) == '1') { // 1* , decode 1* as M-P  case 4
                        dp[i+1] = dp[i+1]  + dp[i-1] * 9;
                    }
                    else if (s.charAt(i-1) == '2') { // 2*, decode 2* as P-Z  case 5
                        dp[i+1] = dp[i+1]  + dp[i-1] * 6;
                    }
                }
            }
            else { // case 1, 3, 7, 8
                if (s.charAt(i) != '0') { // case 1
                    dp[i + 1] = dp[i + 1] + dp[i]; // _(not zero), decode as _(A-Z)
                }
                if (i > 0) { // combine encode the last two digits
                    if (s.charAt(i - 1) == '*') { // case 7, 8
                        dp[i + 1] = dp[i + 1] + dp[i - 1]; // for 1_  case 8
                        if (s.charAt(i) >= '0' && s.charAt(i) <= '6') { // for 2_  case 7
                            dp[i + 1] = dp[i + 1] + dp[i - 1];
                        }
                    }
                    else { // case 3
                        String substr = s.substring(i-1, i+1);
                        int subint = Integer.parseInt(substr);
                        if (subint >= 10 && subint <= 26) {
                            dp[i + 1] = dp[i + 1] + dp[i - 1];
                        }
                    }
                }
            }
            // why doing this ???? very important here
            dp[i+1] %= 1000000007;
        }
        return (int)dp[s.length()];
    }

}
