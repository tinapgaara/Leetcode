package trie;


/**
 * Created by yingtan on 9/23/15.
 */
public class WordDictionary {
    TrieNode root;

    public class TrieNode {
        char ch;
        TrieNode[] childrens;
        boolean isStop;

        public TrieNode(char ch) {
            this.ch = ch;
            childrens = new TrieNode[27]; // Important !!! use 27 length
            isStop = false;
        }
    }

    public WordDictionary() {
        root = new TrieNode('0'); // start
    }

    // Adds a word into the data structure.
    public void addWord(String word) {
        if ((word == null) || (word.length() == 0)) return;
        TrieNode copy = root;
        while (!copy.isStop) {
            int pos = word.charAt(0) - 'a';
            if (copy.childrens[pos] == null) {
                TrieNode newnode = new TrieNode(word.charAt(0));
                copy.childrens[pos] = newnode;
            }
            word = word.substring(1);
            copy = copy.childrens[pos];
            if (word.length() == 0) {
                // Important !!!!!  add $ as terminator : last node
                TrieNode terminator = new TrieNode('$');
                terminator.isStop = true;
                copy.childrens[26] = terminator;

                break;
            }
        }
    }

    // Returns if the word is in the data structure. A word could
    // contain the dot character '.' to represent any one letter.
    public boolean search(String word) {
        if ((word == null) || (word.length() == 0)) return false;

        return recurSearch(root, (word+"$"), 0); // Important !!! word + $
    }

    public boolean recurSearch(TrieNode cur, String word, int index) {
        if (index >= word.length()) return false;
        if (word.charAt(index) == '$') {
            // Important : judge $ here
            if ((cur.childrens[26] != null) && (cur.childrens[26].ch == '$')) return true;
            else return false; // Important return false here, or out of boundary
        }
        TrieNode copy = cur;
        char ch = word.charAt(index);
        if (ch == '.') {
            for (int i = 0; i < copy.childrens.length; i++) {
                if ((copy.childrens[i] != null) && (!copy.isStop)) {
                    if (recurSearch(copy.childrens[i], word, index + 1))
                        return true;
                }
            }
            return false;
        }
        else {
            int pos = ch - 'a';
            if ((!copy.isStop) && (copy.childrens[pos] != null)) {
                copy = copy.childrens[pos];
                return recurSearch(copy, word, index + 1);
            } else {
                return false;
            }
        }
    }



    public static void main(String[] args) {
        WordDictionary dict = new WordDictionary();
        dict.addWord("at");
        dict.addWord("and");
        dict.addWord("an");
        dict.addWord("add");

        System.out.println(dict.search("a"));
        System.out.println(dict.search(".at"));
        dict.addWord("bat");

        System.out.println(dict.search(".at"));
        System.out.println(dict.search("an."));
        System.out.println(dict.search("a.d."));
        System.out.println(dict.search("b."));
        System.out.println(dict.search("a.d"));
        System.out.println(dict.search("."));

    }
}
