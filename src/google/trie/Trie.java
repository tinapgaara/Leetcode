package google.trie;

/**
 * Created by yingtan on 11/8/15.
 */
public class Trie {

    public String val;
    public Trie[] neighbors;
    public boolean isStop;

    public Trie(String val) {
        this.neighbors = new Trie[257]; // last one store end
        this.val = val;
        this.isStop = false;
    }

    public void insert(String str) {
        recurInsert(str, this, 0);
    }

    public void recurInsert(String str, Trie trie, int index) {
        if (index >= str.length()) {
            Trie end = new Trie(str);
            end.isStop = true;
            trie.neighbors[256] = end;

            return;
        }

        char ch = str.charAt(index);
        int pos = ch;

        if (trie.neighbors[pos] == null) {
            Trie newnode = new Trie(ch + "");
            trie.neighbors[pos] = newnode;
            System.out.println(trie.neighbors[pos].val);

        }

        recurInsert(str, trie.neighbors[pos], index + 1);
    }

    public boolean search(String str, Trie trie, int index) {

        if (index >= str.length()) {
            Trie end = trie.neighbors[256];
            if (end != null) {
                if (end.isStop) {
                    return true;
                }
            }
            return false;
        }

        int pos = str.charAt(index);
        if (trie.neighbors[pos] != null) { // cur appears in trie's neighbors
            return search(str, trie.neighbors[pos], index + 1);
        }
        else
            return false;
    }

    public static void main(String[] args) {
        Trie trie = new Trie("#");
        trie.insert("oath");
        trie.insert("ped");
        trie.insert("eat");
        trie.insert("raih");

        System.out.println(trie.search("raihh", trie, 0));
    }

}
