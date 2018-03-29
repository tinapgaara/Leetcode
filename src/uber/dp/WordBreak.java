package uber.dp;

import java.util.List;

/**
 * Created by yingtan on 12/3/17.
 */
public class WordBreak {

    public boolean wordBreak(String s, List<String> wordDict) {
        if (s == null || wordDict == null) return false;
        boolean[] dp = new boolean[s.length()  +1];
        dp[0] = true;
        for (int i = 0 ; i < s.length(); i ++) {
            if (! dp[i]) continue;
            for (String word : wordDict) {
                int end = i + word.length();
                if (end <= s.length()) {
                    if (s.substring(i, end).equals(word)) {
                        dp[end] = true;
                    }
                }
            }
        }
        return dp[s.length()];
    }
}
