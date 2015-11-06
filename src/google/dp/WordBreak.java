package google.dp;

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

    public boolean wordBreak(String s, Set<String> wordDict) {
        if ((wordDict == null) || (s == null) || (s. length() == 0))
            return true;

        boolean[][] isSeg = new boolean[s.length()][s.length()];

        for (int len = 0; len < s.length(); len ++) {
            for (int low = 0 ; low < s.length() - len; low ++) {
                int high = len + low;
                String str = s.substring(low, high+1);
                if (wordDict.contains(str)) {
                    isSeg[low][high] = true;
                }
                else {
                    for (int k = low; k < high; k ++) {
                        if (isSeg[low][k] && isSeg[k+1][high]) {
                            isSeg[low][high] = true;
                            break;
                        }
                    }
                }
            }
        }
        return isSeg[0][s.length()-1];
    }
}
