package google.dp;

import java.util.*;

/**
 * Created by yingtan on 11/7/15.
 */
public class WordBreakII {

    public List<String> wordBreak(String s, Set<String> wordDict) {
        if ((wordDict == null) || (s == null) || (s. length() == 0))
            return null;

        List<String> dp[] = new ArrayList[s.length()+1];
        dp[0] = new ArrayList<>();

        for (int i = 0 ; i < s.length(); i ++) {
            if (dp[i] == null) {
                continue;
            }
            for (String word: wordDict) {
                int wordLen = word.length();
                int wordEnd = i + wordLen;
                if (wordEnd > s.length()) continue;
                String substr = s.substring(i, wordEnd);
                if (substr.equals(word)) {
                    if (dp[wordEnd] == null) {
                        List<String> res = new ArrayList<String>();
                        dp[wordEnd] = res;
                    }
                    dp[wordEnd].add(substr);
                }
            }
        }

        List<String> result = new LinkedList<String>();
        if(dp[s.length()] == null)
            return result;

        ArrayList<String> temp = new ArrayList<String>();
        DFS(dp, s.length(), result, temp);

        return result;
    }

    // Important !!!!!!  ??????????
    // DFS:
    // while () {
    //      add(sth)
    //      DFS
    //      remove(sth)
    // }
    public void DFS(List<String> dp[],int end,List<String> result, ArrayList<String> tmp){
        if(end <= 0){
            String path = tmp.get(tmp.size()-1);
            for(int i=tmp.size()-2; i>=0; i--){
                path += " " + tmp.get(i) ;
            }

            result.add(path);
            return;
        }

        for(String str : dp[end]){
            tmp.add(str); // Very Important !!!!!
            DFS(dp, end - str.length(), result, tmp); // Very Important !!!!!
            tmp.remove(tmp.size()-1); // Very Important !!!!!
        }
    }

    public List<String> wordBreak2(String s, List<String> wordDict) {
        if ( (wordDict == null) || (s == null) || (s.length() == 0) ) return null;
        List<String>[] dp = new ArrayList[s.length() + 1];
        dp[0] = new ArrayList<>();
        for (int i = 0 ; i < s.length(); i ++) {
            if (dp[i] == null) continue;
            for (String word : wordDict) {
                int len = word.length();
                if (i + len > s.length())  continue;
                String subs = s.substring(i, i + len);

                if (word.equals(subs)) {
                    if(dp[i+len] == null) {
                        dp[i+len] = new ArrayList<String>();
                    }
                    dp[i+len].add(word);
                }
            }
        }

        List<String> tmp = new ArrayList<String>();
        List<String> res = new ArrayList<String>();
        dfs(dp, res, tmp, s.length());
        return res;
    }

    public void dfs(List<String>[] dp, List<String> res, List<String> tmp, int dpIndex) {
        if (dpIndex <= 0) {
            String path = tmp.get(tmp.size() - 1);
            for (int i = tmp.size() - 2; i >=0 ; i --) {
                path = path + " " + tmp.get(i);
            }
            res.add(path);

            return;
        }
        for (String word : dp[dpIndex]) {
            tmp.add(word);
            dfs(dp, res, tmp, dpIndex - word.length());
            tmp.remove(tmp.size() - 1) ; // remove word added
        }
    }

    public static void main(String[] args) {
        WordBreakII ob = new WordBreakII();
        String word = "catsanddog";
        Set<String> words = new HashSet<>();
        words.add("cat");
        words.add("cats");
        words.add("and");
        words.add("sand");
        words.add("dog");

        List<String> word2s = new ArrayList<>();
        word2s.add("cat");
        word2s.add("cats");
        word2s.add("and");
        word2s.add("sand");
        word2s.add("dog");

        System.out.println(ob.wordBreak2(word, word2s));
    }
}
