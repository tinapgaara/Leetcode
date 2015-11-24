package google.backtrack;

import java.util.*;

/**
 * Created by yingtan on 11/22/15.
 */
public class WordBreakII {

    public List<String> wordBreak(String s, Set<String> wordDict) {
        if ((wordDict == null) || (s == null) || (s.length() == 0))
            return null;

        List<String>[] dp = new ArrayList[s.length()+1];
        dp[0] = new ArrayList<>();
        for (int dpIndex = 0; dpIndex < s.length() + 1; dpIndex ++) {
            if (dp[dpIndex] == null) continue;
            int charIndex = dpIndex;

            for (String word: wordDict) {
                int strEndIndex = charIndex + word.length();
                if (strEndIndex > s.length()) continue; // Important !!!!
                String substring = s.substring(charIndex, strEndIndex);
                if (substring.equals(word)) {
                    if (dp[strEndIndex] != null) {
                        dp[strEndIndex].add(word);
                    }
                    else {
                        List<String> list = new ArrayList<>();
                        list.add(word);
                        dp[strEndIndex] = list;
                    }
                }
            }
        }
        // Important !!!!!
        List<String> result = new ArrayList<>();
        if(dp[s.length()] == null)
            return result;
        //
        return traceBack(dp, dp.length-1, "");
    }

    public List<String> traceBack(List<String>[] dp, int index, String str) {

        List<String> level = dp[index];
        List<String> res = new ArrayList<>();

        if (index <= 0 ) {
            res.add(str);
            return res;
        }

        for (String cur : level) {
            String newstr = "";
            if (str.length() > 0) // Important !!!! need to judge the str.length to avoid the final word add " "
                newstr = cur + " " + str;
            else
                newstr = cur;
            int newIndex = index - cur.length();
            List<String> next = traceBack(dp, newIndex, newstr);
            res.addAll(next);
        }
        return res;
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
