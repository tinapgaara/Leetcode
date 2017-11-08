package google.trie;

/**
 * Created by yingtan on 8/6/17.
 */
public class Trie {

    public Trie[] children;

    public Trie() {
        children = new Trie[27];
    }

    // implement trie using recur
    public void recurAdd(Trie node, String word, int index) {
        if (index >= word.length()) {
            node.children[26] = new Trie();
            return;
        }

        char ch = word.charAt(index);
        int pos = ch - 'a';
        if (node.children[pos] == null) {
            node.children[pos] = new Trie();
        }
        recurAdd(node.children[pos], word, index + 1);
    }

    public boolean recurSearch(Trie node, String word, int index) {
        if (index >= word.length()) {
            if (node.children[26] != null) return true;
            else return false;
        }

        char ch = word.charAt(index);
        int pos = ch - 'a';
        if (node.children[pos] == null) {
            return false;
        }
        else {
            return recurSearch(node.children[pos], word, index + 1);
        }
    }

    public boolean recurStartsWith(Trie node, String word, int index) {
        if (index >= word.length()) return true;

        char ch = word.charAt(index);
        int pos = ch - 'a';
        if (node.children[pos] == null) return false;
        else {
            return recurStartsWith(node.children[pos], word, index + 1);
        }
    }

    // implement trie using loop
    public void add(Trie node, String word) {
        Trie cur = node;
        for (char ch : word.toCharArray()) {
            if (cur.children[ch - 'a'] == null) {
                // insert
                cur.children[ch - 'a'] = new Trie();
            }
            cur = cur.children[ch - 'a'];
        }
        // ending symbol, important !
        cur.children[26] = new Trie();
    }

    public boolean search(Trie node, String word) {
        Trie cur = node;
        for (char ch : word.toCharArray()) {
            if (cur.children[ch - 'a'] == null) {
                return false;
            }
            cur = cur.children[ch - 'a'];
        }
        // ending important !
        if (cur != null && cur.children[26] != null) return true;
        else return false;
    }

    public boolean startsWith(Trie node, String word) {
        Trie cur = node;
        for (char ch : word.toCharArray()) {
            if (cur.children[ch - 'a'] == null) {
                return false;
            }
            cur = cur.children[ch - 'a'];
        }
        // important !
        return true;
    }
}
