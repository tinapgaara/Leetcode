package facebook.trie;

/**
 * Created by yingtan on 12/21/17.
 *
 * 745. Prefix and Suffix Search
 DescriptionHintsSubmissionsDiscussSolution
 DiscussPick One
 Given many words, words[i] has weight i.

 Design a class WordFilter that supports one function, WordFilter.f(String prefix, String suffix). It will return the word with given prefix and suffix with maximum weight. If no word exists, return -1.

 Examples:
 Input:
 WordFilter(["apple"])
 WordFilter.f("a", "e") // returns 0
 WordFilter.f("b", "") // returns -1
 */
import java.util.*;
public class PrefixAndSuffixSearch {

    public class Trie {
        char val;
        Map<Character, Trie> children;
        List<Integer> matchedWordIndexs;
        boolean isEnd;
        public Trie(char val) {
            this.val = val;
            this.children = new HashMap<>();
            matchedWordIndexs = new ArrayList<>();
            this.isEnd = false;
        }
    }
    Trie prefixRoot;
    Trie suffixRoot;
    /*
    Important !!
    1. index  == weight, so the inserted word latter must be the one with larger weight
    2. prefix, suffix has length <=10, so we don't need to build a complete trie with full word length. Just need to pick previous 10 chs and insert that to trie.
    */
    public PrefixAndSuffixSearch(String[] words) {
        prefixRoot = new Trie('#');
        suffixRoot = new Trie('#');
        for (int j = 0; j < words.length ; j ++) {
            String word = words[j];
            prefixRoot.matchedWordIndexs.add(j);
            suffixRoot.matchedWordIndexs.add(j);
            // insert the word to prefix and suffix tree
            // prefix
            Trie prefixCur = prefixRoot;
            for (int i = 0 ; i < Math.min(word.length(), 10); i ++) { // important !!! Math.min(word.length(), 10)
                char ch = word.charAt(i);
                if (! prefixCur.children.containsKey(ch)) {
                    Trie child = new Trie(ch);
                    prefixCur.children.put(ch, child);
                }
                prefixCur = prefixCur.children.get(ch);
                prefixCur.matchedWordIndexs.add(j);

            }
            // suffix
            Trie suffixCur = suffixRoot;
            for (int i = word.length() -1 ; i >= Math.max(0, word.length() - 10); i --) { // important !!! Math.max(0, word.length() - 10)
                char ch = word.charAt(i);
                if (! suffixCur.children.containsKey(ch)) {
                    Trie child = new Trie(ch);
                    suffixCur.children.put(ch, child);
                }
                suffixCur = suffixCur.children.get(ch);
                suffixCur.matchedWordIndexs.add(j);
            }
        }
    }
    // Time complexity: o(word.length())
    public int f(String prefix, String suffix) {
        if (prefix == null || suffix == null) return -1;
        Trie prefixCur = prefixRoot;
        for (char ch : prefix.toCharArray()) {
            if (! prefixCur.children.containsKey(ch)) {
                return -1;
            }
            else {
                prefixCur = prefixCur.children.get(ch);
            }
        }
        Trie suffixCur = suffixRoot;
        // must match from tail to head
        for (int i = suffix.length() -1; i >= 0; i --) {
            char ch = suffix.charAt(i);
            if (! suffixCur.children.containsKey(ch)) {
                return -1;
            }
            else {
                suffixCur = suffixCur.children.get(ch);
            }
        }
        List<Integer> prefixIndexs = prefixCur.matchedWordIndexs;
        List<Integer> suffixIndexs = suffixCur.matchedWordIndexs;
        int i = prefixIndexs.size() - 1;
        int j = suffixIndexs.size() - 1;
        while(i >= 0 && j >= 0) {
            // don't use == on Integer, use == on Integer.intValue()
            if (prefixIndexs.get(i).intValue() == suffixIndexs.get(j).intValue()) {
                //  this is the common word with highest weight
                System.out.println(prefixIndexs.get(i));
                return prefixIndexs.get(i);
            }
            else if (prefixIndexs.get(i) > suffixIndexs.get(j)) {
                i --;
            }
            else {
                j --;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        String[] words = {"pop"};
        PrefixAndSuffixSearch ob = new PrefixAndSuffixSearch(words);
        System.out.println(ob.f("", "op"));
    }
}
