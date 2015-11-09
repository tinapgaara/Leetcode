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

    // Important !!!!!!
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

    public static void main(String[] args) {
        WordBreakII ob = new WordBreakII();
        String word = "catsanddog";
        Set<String> words = new HashSet<>();
        words.add("cat");
        words.add("cats");
        words.add("and");
        words.add("sand");
        words.add("dog");

        System.out.println(ob.wordBreak(word, words));
    }
}
