package bloomberg.phoneBook;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yingtan on 10/26/15.
 */
public class Trie {

    private char ch;
    private boolean isStop;
    private String endValue;

    private List<Trie> tries;

    public Trie(char ch, boolean isStop, String value) {
        this.ch = ch;
        this.isStop = isStop;
        this.endValue = value;

        tries = new ArrayList<>();
    }

    public void addStr(String str, String attribute) {
        recurAddStr(str, attribute, this, 0);

    }
    public void recurAddStr(String str, String attribute, Trie node, int index) {
        char[] chs = str.toCharArray();

        if (index >= str.length()) {
            Trie stopNode = new Trie('$', true, attribute); // stop
            node.tries.add(stopNode);
            return;
        }
        char ch = chs[index];
        List<Trie> nodes = node.tries;
        boolean isCreate = true;
        if (nodes.size() != 0) {
            for (Trie n: nodes) {
                if (n.ch == ch) {
                    isCreate = false;
                    recurAddStr(str, attribute, n, index + 1);
                    break;
                }
            }

        }
        if (isCreate) {
            Trie nextNode = new Trie(ch, false, null);
            node.tries.add(nextNode);
            recurAddStr(str, attribute, nextNode, index + 1);
        }
    }

    public String search(String name) {
        int i = 0;
        char[] chs = name.toCharArray();
        Trie copy = this;
        while (!copy.isStop) {
            List<Trie> nexts = copy.tries;
            if (nexts.size() > 0) {
                if (i >= chs.length) {
                    for (Trie node: nexts) {
                        if (node.isStop) return node.endValue;
                    }
                    return "";// can not find
                }
                else {
                    for (Trie node : nexts) {
                        if (node.ch == chs[i]) {
                            i++;
                            copy = node;
                            break;
                        }
                    }
                }
            }
        }
        return "";// can not find
    }

    public static void main(String[] args) {
        Trie phone = new Trie('#', false, null);
        phone.addStr("franny", "347935");
        phone.addStr("fran", "123");
        phone.addStr("fra", "456");

        Trie name = new Trie('@', false, null);
        name.addStr("347935", "franny");
        name.addStr("123", "fran");
        System.out.println(phone.search("fra"));
        System.out.println(name.search("123"));
    }

}
