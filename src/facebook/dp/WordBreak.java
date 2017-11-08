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
}
