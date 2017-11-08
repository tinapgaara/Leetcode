package square;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by yingtan on 10/17/17.
 *
 * A message containing letters from A-Z is being encoded to numbers using the following mapping:

 'A' -> 1
 'B' -> 2
 ...
 'Z' -> 26
 Given an encoded message containing digits, determine the total number of ways to decode it.

 For example,
 Given encoded message "12", it could be decoded as "AB" (1 2) or "L" (12).

 The number of ways decoding "12" is 2.

 // follow up:
 followup是返回所有方式而非多少种方式。
 */
public class DecodeWays {
    public int numDecodings(String s) {
        if (s == null || s.length() == 0) return 0;
        if (s.charAt(0) == '0') return 0;

        int[] dp = new int[s.length() + 1];
        dp[0] = 1;
        dp[1] = 1;

        for (int i = 1; i < s.length(); i ++) {
            char ch = s.charAt(i);
            if (ch - '0' != 0) {
                dp[i + 1] = dp[i];
            }
            if (s.charAt(i-1) != '0') {
                int curTwoDigit = Integer.parseInt(s.substring(i-1, i+1));
                if ( (curTwoDigit > 0) && (curTwoDigit <= 26) ) {
                    dp[i+1] = dp[i+1] + dp[i-1];
                }
            }
        }
        return dp[s.length()];
    }

    public List<String> numDecodings_followup(String s) {
        List<String> res = new ArrayList<>();
        if (s == null || s.length() == 0) return res;
        if (s.charAt(0) == '0') return res;
        Map<Integer, String> map = new HashMap<>();
        for (int i = 'A'; i <= 'Z'; i ++) {
            map.put(i - 'A' + 1, (char)i + "");
        }
        List<String>[] dp = new List[s.length() + 1];
        dp[0] = new ArrayList<>();
        // important !!!
        dp[0].add("");
        char ch = s.charAt(0);
        dp[1] = new ArrayList<>();
        dp[1].add(map.get(ch - '0'));
        for (int i = 1; i < s.length(); i ++) {
            ch = s.charAt(i);
            dp[i + 1] = new ArrayList<>();
            if (ch - '0' != 0) {
                String str = map.get(ch - '0');
                for (String prevstr : dp[i]) {
                    String curStr = prevstr + str;
                    dp[i+1].add(curStr);
                }
            }
            if (s.charAt(i-1) != '0') {
                int curTwoDigit = Integer.parseInt(s.substring(i-1, i+1));
                if ( (curTwoDigit > 0) && (curTwoDigit <= 26) ) {
                    String str = map.get(curTwoDigit);
                    for (String prevstr : dp[i-1]) {
                        String curStr = prevstr + str;
                        dp[i+1].add(curStr);
                    }
                }
            }
        }
        return dp[s.length()];
    }

    public static void main(String[] args) {
        DecodeWays ob = new DecodeWays();
        System.out.println(ob.numDecodings_followup("123"));
    }
}
