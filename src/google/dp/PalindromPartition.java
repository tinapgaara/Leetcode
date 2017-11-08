package google.dp;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yingtan on 10/12/17.
 *
 * 131. Palindrome Partitioning
 DescriptionHintsSubmissionsDiscussSolution
 Discuss Pick One
 Given a string s, partition s such that every substring of the partition is a palindrome.

 Return all possible palindrome partitioning of s.

 For example, given s = "aab",
 Return

 [
 ["aa","b"],
 ["a","a","b"]
 ]
 */
public class PalindromPartition {

    public List<List<String>> partition_comments(String s) {
        List<List<String>> res = new ArrayList<List<String>>();
        if (s == null || s.length() == 0) return res;

        boolean[][] dp = new boolean[s.length()][s.length()];

        for (int high = 0; high < s.length(); high ++) {
            // important!!! must low <= high
            for (int low = 0; low <= high; low ++) {
                if (s.charAt(high) == s.charAt(low)) {
                    if ((high - low <= 2) || (dp[low+1][high-1])) {
                        dp[low][high] = true;
                    }
                }
            }
        }

        List<String> path = new ArrayList<String>();
        dfs(res,path, s, dp, 0);
        return res;
    }

    public void dfs(List<List<String>> res , List<String> path, String s, boolean[][] dp, int startIndex) {
        if (startIndex == s.length()) {
            // important!!! must new arraylist and copy here
            res.add(new ArrayList<String>(path));
            return;
        }
        for(int end = startIndex; end < s.length(); end++) {
            if(dp[startIndex][end]) {
                path.add(s.substring(startIndex, end+1));
                // important !!! must starting use end+1
                dfs(res, path, s, dp, end+1);
                path.remove(path.size()-1);
            }
        }
    }


    public List<List<String>> partition(String s) {
        List<List<String>> res = new ArrayList<>();
        boolean[][] dp = new boolean[s.length()][s.length()];
        for(int i = 0; i < s.length(); i++) {
            for(int j = 0; j <= i; j++) {
                if(s.charAt(i) == s.charAt(j) && (i - j <= 2 || dp[j+1][i-1])) {
                    dp[j][i] = true;
                }
            }
        }
        helper(res, new ArrayList<>(), dp, s, 0);
        return res;
    }

    private void helper(List<List<String>> res, List<String> path, boolean[][] dp, String s, int pos) {
        if(pos == s.length()) {
            res.add(new ArrayList<>(path));
            return;
        }

        for(int i = pos; i < s.length(); i++) {
            if(dp[pos][i]) {
                path.add(s.substring(pos,i+1));
                helper(res, path, dp, s, i+1);
                path.remove(path.size()-1);
            }
        }
    }
}
