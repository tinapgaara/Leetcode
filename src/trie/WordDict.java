package trie;

import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by yingtan on 9/23/15.
 */
public class WordDict {

    Set<String> m_words;

    public WordDict() {
        m_words = new HashSet<String>();
    }

    // Adds a word into the data structure.
    public void addWord(String word) {
        m_words.add(word);
    }

    // Returns if the word is in the data structure. A word could
    // contain the dot character '.' to represent any one letter.
    public boolean search(String word) {
        String pattern = "";
        for (int i = 0 ; i < word.length(); i ++) {
            char ch = word.charAt(i);
            if (ch == '.') {
                pattern = pattern + "([a-z])";
            }
            else
                pattern = pattern + ch;
        }
        Pattern p = Pattern.compile(pattern);
        for (String str : m_words) {
            Matcher m = p.matcher(str);
            if (m.find()) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        WordDict dict = new WordDict();
        dict.addWord("hello");
        System.out.println(dict.search("hele."));
    }
}
