package google.Imple;

/**
 * Created by yingtan on 11/8/15.
 */
public class Trie {

    public Trie[] neighbors;
    public boolean isStop;

    public Trie() {
        this.isStop = false;
        this.neighbors = new Trie[257];
    }

    public void insert(String str) {
        recurInsert(str, this, 0);
    }

    public void recurInsert(String str, Trie node, int index) {
        if (index >= str.length()) {
            Trie stopNode = new Trie();
            stopNode.isStop = true;
            node.neighbors[256]  = stopNode;
            return;
        }
        char ch = str.charAt(index);
        if (node.neighbors[ch] == null) {
            Trie newNode = new Trie();
            node.neighbors[ch] = newNode;
        }
        recurInsert(str, node.neighbors[ch], index+1);
    }

    public boolean search(String word) {
        return recurSearch(word, this, 0);
    }

    public boolean recurSearch(String str, Trie trie, int index) {
        if (index >= str.length()) {
            Trie endNode = trie.neighbors[256];
            if ((endNode != null) && (endNode.isStop)) return true;
            else return false;
        }

        char ch = str.charAt(index);
        if (trie.neighbors[ch] != null) {
            return recurSearch(str, trie.neighbors[ch], index + 1);
        }
        else {
            return false;
        }
    }

    public boolean startsWith(String prefix) {
        return recurSearch(prefix, this, 0);
    }

    public boolean recurStartsWith(String prefix, Trie node, int index) {
        if (index >= prefix.length()) {
            return true;
        }
        char ch = prefix.charAt(index);
        if (node.neighbors[ch] != null) {
            return recurSearch(prefix, node.neighbors[ch], index+1);
        }
        else
            return true;
    }

    public static void main(String[] args) {
        Trie trie = new Trie();
        trie.insert("oath");
        trie.insert("ped");
        trie.insert("eat");
        trie.insert("raih");

        System.out.println(trie.search("eatt"));
    }

}
