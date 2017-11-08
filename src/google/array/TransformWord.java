package google.array;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by yingtan on 11/5/17.
 *
 * 第一轮：中国老哥：一个字典（String的Array），一个input word，
 * 可以删除input word中的任意个字符，问是否能进行这样的操作使得变成字典中的一个word。
 上来直接trie加dfs...老哥没有反应过来，然后想了另外一种方法，类似two pointer；
 分析了一下两种方法的时间复杂度，然后把这两个都写出来，讨论什么时候用哪一种合适

 */
public class TransformWord {

    public boolean isTransform(Set<String> dict, String word) {
        for (String dictWord : dict) {
            if (canTransfrom(dictWord, word)) return true;
        }
        return false;
    }
    public boolean canTransfrom(String dictWord, String word) {
        // a b e e c d
        // a b c d
        if (word.length() < dictWord.length()) return false;
        int i = 0;
        int j = 0; // j must be smaller than i
        while(i < word.length() && j < dictWord.length()) {
            char ch = word.charAt(i);
            char dictCh = dictWord.charAt(j);
            if (ch == dictCh) {
                i ++;
                j ++;
            }
            else {
                i ++;
            }
        }
        return j == dictWord.length();
    }

    public static void main(String[] args) {
        TransformWord ob = new TransformWord();
        Set<String> dict = new HashSet<>();
        dict.add("abcd");
        dict.add("hello");
        String word = "abedchellssssossss";
        System.out.println(ob.isTransform(dict, word));
    }
}
