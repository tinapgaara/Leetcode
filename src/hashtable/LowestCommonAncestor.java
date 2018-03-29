package hashtable;

import java.util.HashSet;

/**
 * Created by yingtan on 12/16/17.
 */
public class LowestCommonAncestor {

    public class Node {
        int val;
        Node parent;

        public Node(int val) {
            this.val = val;
        }
    }

    public Node LCA(Node n1, Node n2) {
        HashSet<Node> allancestors = new HashSet<>();

        while(n1 != null || n2 != null) {
            if (n1 != null) {
                if (allancestors.contains(n1)) {
                    return n1;
                }
                else {
                    allancestors.add(n1);
                }
                n1 = n1.parent;
            }
            if (n2 != null) {
                if (allancestors.contains(n2)) {
                    return n2;
                }
                else {
                    allancestors.add(n2);
                }
                n2 = n2.parent;
            }
        }
        return null;
    }
}
