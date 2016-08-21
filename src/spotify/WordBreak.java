package spotify;

import java.util.Set;

/**
 * Created by yingtan on 11/18/15.
 */
/*
* Given a string s and a dictionary of words dict, determine if s can be segmented
* into a space-separated sequence of one or more dictionary words.

For example, given
s = "leetcode",
dict = ["leet", "code"].

Return true because "leetcode" can be segmented as "leet code".

s = "leetcodecode",
dict = ["leet", "code"].
return true
*
* */
public class WordBreak {

    public boolean wordBreak(String s, Set<String> wordDict) {
        if ((wordDict == null) || (s == null) || (s.length() == 0))
            return true;

        boolean[] isSeg = new boolean[s.length() + 1];
        isSeg[0] = true;

        for (int i = 0 ; i < s.length(); i ++) {
            if (! isSeg[i]) continue;
            for (String word : wordDict) {

                int last = i + word.length();
                if (last > s.length()) continue;// boundary judgement

                String substring = s.substring(i, i + word.length());
                if (substring.equals(word)) {
                    isSeg[last] = true;
                }
            }
        }
        return isSeg[s.length()];
    }
}
