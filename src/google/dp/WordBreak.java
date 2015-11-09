package google.dp;

import java.util.HashSet;
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

    // dp 2-dim
    public boolean wordBreak(String s, Set<String> wordDict) {
        if ((wordDict == null) || (s == null) || (s. length() == 0))
            return true;

        boolean[][] isSeg = new boolean[s.length()][s.length()];

        for (int len = 0; len < s.length(); len ++) { // n
            for (int low = 0 ; low < s.length() - len; low ++) { // n
                int high = len + low;
                String str = s.substring(low, high+1);
                if (wordDict.contains(str)) {
                    isSeg[low][high] = true;
                }
                else {
                    for (int k = low; k < high; k ++) { // k
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

    // dp 1-dim
    public boolean wordBreak_1Dim(String s, Set<String> wordDict) {
        if ((wordDict == null) || (s == null) || (s.length() == 0))
            return true;

        boolean[] isSeg = new boolean[s.length() + 1];
        isSeg[0] = true;

        for (int i = 0 ; i < s.length(); i ++) {
            if (!isSeg[i]) continue;

            for (String word: wordDict) {
                int len = word.length();
                int wordEnd = i + len;
                if (wordEnd > s.length()) continue;
                if (isSeg[wordEnd]) continue;

                String substr = s.substring(i, wordEnd);
                if (substr.equals(word)) {
                    isSeg[wordEnd] = true; // make leet: "c" to be marked
                }
            }
        }
        return isSeg[s.length()];
    }

    public static void main(String[] args) {
        WordBreak ob = new WordBreak();
        String word = "leetcode";
        Set<String> words = new HashSet<>();
        words.add("leet");
        words.add("code");

        ob.wordBreak_1Dim(word, words);
    }

}
