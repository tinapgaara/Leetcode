package amazon;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.*;

public class MostCommonWord {
    public List<String> commonWords(String paragraph, List<String> banned) {
        List<String> res = new ArrayList<>();
        if (paragraph == null || paragraph.length() == 0) return res;
        Set<String> banSet = new HashSet<>();
        for (String word : banned) {
            banSet.add(word.toLowerCase());
        }
        String[] words = paragraph.toLowerCase().split("\\W+");
        int max = Integer.MIN_VALUE;
        Map<String, Integer> wordToCount = new HashMap<>();
        for (String word : words) {
            if(! banned(banSet, word)) {
                if (wordToCount.containsKey(word)) {
                    wordToCount.put(word, wordToCount.get(word) + 1);
                }
                else {
                    wordToCount.put(word, 1);
                }
                int count = wordToCount.get(word);
                if (count > max) {
                    res.clear();
                    res.add(word);
                    max = count;
                }
                else if (count == max) {
                    res.add(word);
                }
            }
        }
        return res;
    }
    public boolean banned(Set<String> banSet, String word) {
        if (banSet.contains(word)) {
            return true;
        }
        else {
            return false;
        }
    }
    public static void main(String[] args) {
        MostCommonWord ob = new MostCommonWord();
        String p1 = "Rose is a flower red rose are flower ";
        List<String> e1 = Arrays.asList(new String[]{"is", "are", "a"});
        String p2 = "Jack and Jill went to the market to buy breaad and cheese. Cheese is ";
        List<String> e2 = Arrays.asList(new String[]{"and", "he", "the", "to", "is"});
        String p3 = "Jack and Jill went to the market to buy breaad and cheese. Cheese is Jack's and Jill's favorite food";
        List<String> e3 = Arrays.asList(new String[]{"and", "he", "the", "to", "is"});
        System.out.println(ob.commonWords(p1, e1));
        System.out.println(ob.commonWords(p2, e2));
        System.out.println(ob.commonWords(p3, e3));
    }
}
