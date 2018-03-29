package linkedin.palindromic;

import java.util.TreeSet;

/**
 * Created by yingtan on 11/28/17.
 */
public class CountDiffPalindromicSubsequence {

    int div=1000000007;
    public int countPalindromicSubsequences(String S) {
        TreeSet[] chIndexs = new TreeSet[26];
        int len = S.length();
        Integer[][] dp = new Integer[len][len];
        for (int i = 0 ; i < 26; i ++) {
            chIndexs[i] = new TreeSet<Integer>();
        }
        for (int i = 0 ; i < S.length(); i ++) {
            int index = S.charAt(i) - 'a';
            chIndexs[index].add(i);
        }
        return dfs(S, chIndexs, dp, 0, len - 1);

    }

    public int dfs(String S, TreeSet<Integer>[] indexs, Integer[][] dp, int start, int end){
        if(start > end) return 0;
        if(dp[start][end] != null) return dp[start][end];
        // Important !!! must use long
        long res = 0;
        // find all subsequences a....a, b...b,....
        for (int i = 0 ; i < 26; i ++) {
            //        a  ..........a
            // chNewStart       chNewEnd
            Integer chNewStart = indexs[i].ceiling(start);
            Integer chNewEnd = indexs[i].floor(end);
            if (chNewStart == null || chNewStart > end) continue;;
            // a (as a single char)
            res ++;
            if (chNewEnd == null) continue;
            if (chNewStart != chNewEnd) {
                //aa
                res ++;
            }
            res = res + dfs(S, indexs, dp, chNewStart + 1, chNewEnd - 1);
        }
        dp[start][end] = (int)(res%div);
        return dp[start][end];
    }
}
