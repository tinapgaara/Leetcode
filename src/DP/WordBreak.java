package DP;

/**
 * Created by yingtan on 1/16/18.
 */
import java.util.*;
public class WordBreak {

    public boolean wordBreak(String s, List<String> wordDict) {
        if (s == null || wordDict == null) return false;
        boolean[] dp = new boolean[s.length() + 1];
        dp[0] = true;
        for (int i = 0; i < s.length(); i++) {
            if (! dp[i]) continue;
            for (String word : wordDict) {
                if (i + word.length() <= s.length()) {
                    int end = i + word.length();
                    if (s.substring(i, end).equals(word)) {
                        dp[end] = true;
                    }
                }
            }
        }
        return dp[s.length()];
    }
    public List<String> wordBreak_oneWay(String s, List<String> wordDict) {
        List<String> res = new ArrayList<>();
        if (s == null || wordDict == null) return res;
        int[] dp = new int[s.length() + 1];
        Arrays.fill(dp, -1);
        dp[0] = 0;
        for (int i = 0; i < s.length(); i++) {
            if (dp[i] == -1) continue;
            for (String word : wordDict) {
                if (i + word.length() <= s.length()) {
                    int end = i + word.length();
                    if (s.substring(i, end).equals(word)) {
                        dp[end] = i;
                    }
                }
            }
        }
        int endindex = s.length();
        if (dp[s.length()] != -1) {
            // has solution
            int startindex = dp[s.length()];
            while(startindex >= 0) {
                res.add(s.substring(startindex, endindex));
                endindex = startindex;
                startindex = dp[endindex];
            }
        }
        Collections.reverse(res);
        return res;
    }
}
