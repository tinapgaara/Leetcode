package google.dp;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by yingtan on 11/6/15.
 */
/*
* Given a string s and a dictionary of words dict, determine if s
* can be segmented into a space-separated sequence of one or more dictionary words.

For example, given
s = "leetcode",
dict = ["leet", "code"].

Return true because "leetcode" can be segmented as "leet code".
*
* */
public class WordBreak {

    public boolean wordBreak(String s, List<String> wordDict) {
        if (s == null || wordDict.size() == 0) return false;
        int start = 0;
        boolean[] dp = new boolean[s.length() + 1];
        dp[0] = true;

        while (start < s.length()) {
            if (dp[start]) {
                for (String word : wordDict) {
                    int end = start + word.length();
                    if (end <= s.length()) {
                        String subword = s.substring(start, end);
                        if ( (subword != null) && (subword.equals(word)) ) {
                            dp[end] = true;
                         }
                    }
                }
            }
            start ++;
        }
        return dp[s.length()];
    }

    public boolean wordBreak_normalDP(String s, List<String> wordDict) {
        if (s == null || wordDict.size() == 0) return false;
        int start = 0;
        boolean[] dp = new boolean[s.length() + 1];
        dp[0] = true;

        // normal
        for (int i = 1 ; i <= s.length(); i ++) {
            for (int j = 0 ; j < i ; j ++) {
                if (dp[j] && wordDict.contains(s.substring(j, i))) {
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[s.length()];
    }


    public static void main(String[] args) {
        WordBreak ob = new WordBreak();
        String word = "leetcodecode";
        Set<String> words = new HashSet<>();
        HashMap<String, Object> runtimeParameters = new HashMap<>(null);
    }

}
