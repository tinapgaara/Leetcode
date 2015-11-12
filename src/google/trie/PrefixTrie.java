package google.trie;

/**
 * Created by yingtan on 11/11/15.
 */
public class PrefixTrie {

    class TrieNode {
        boolean isStop;
        TrieNode[] neighbors;
        // Initialize your data structure here.
        public TrieNode() {
            isStop = false;
            neighbors = new TrieNode[27];
        }
    }

    public class Trie {
        private TrieNode root;

        public Trie() {
            root = new TrieNode();
        }

        // Inserts a word into the trie.
        public void insert(String word) {
            recurInsert(word, 0, root);
        }

        public void recurInsert(String word, int index, TrieNode curNode) {
            if (index >= word.length()) { // Pay attention !!! >=
                TrieNode end = new TrieNode();
                end.isStop = true;
                curNode.neighbors[26] = end;
                return;
            }
            char ch = word.charAt(index);
            int pos = ch - 'a';
            if (curNode.neighbors[pos] == null) {
                TrieNode node = new TrieNode();
                curNode.neighbors[pos] = node;
            }
            recurInsert(word, index +1, curNode.neighbors[pos]);
        }

        // Returns if the word is in the trie.
        public boolean search(String word) {
            return recurSearch(word, 0, root);
        }

        public boolean recurSearch(String word, int index, TrieNode curNode) {
            if (index >= word.length()) { // Pay attention !!! >=
                TrieNode end = curNode.neighbors[26];
                if ((end != null) && (end.isStop)) return true;
                else return false;
            }
            int pos = word.charAt(index) - 'a';
            if (curNode.neighbors[pos] != null) {
                return recurSearch(word, index +1, curNode.neighbors[pos]);
            }
            else
                return false;
        }

        // Returns if there is any word in the trie
        // that starts with the given prefix.
        public boolean startsWith(String prefix) {
            return recurStartsWith(prefix, 0, root);
        }

        public boolean recurStartsWith(String prefix, int index, TrieNode curNode) {
            if (index >= prefix.length()) return true;  // Pay attention !!! >=  contaisn =
            int pos = prefix.charAt(index) - 'a';
            if (curNode.neighbors[pos] != null) {
                return recurStartsWith(prefix, index+1, curNode.neighbors[pos]);
            }
            else return false;
        }
    }

// Your Trie object will be instantiated and called as such:
// Trie trie = new Trie();
// trie.insert("somestring");
// trie.search("key");
}
