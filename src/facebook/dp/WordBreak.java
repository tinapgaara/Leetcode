package facebook.dp;

import java.util.List;

/**
 * Created by yingtan on 5/20/17.
 *
 * 139. Word Break Add to List
 DescriptionHintsSubmissionsSolutions
 Total Accepted: 142267
 Total Submissions: 486164
 Difficulty: Medium
 Contributor: LeetCode
 Given a non-empty string s and a dictionary wordDict containing a list of non-empty words, determine if s can be segmented into a space-separated sequence of one or more dictionary words. You may assume the dictionary does not contain duplicate words.

 For example, given
 s = "leetcode",
 dict = ["leet", "code"].

 Return true because "leetcode" can be segmented as "leet code".

 UPDATE (2017/1/4):
 The wordDict parameter had been changed to a list of strings (instead of a set of strings). Please reload the code definition to get the latest changes.
 */
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
}
