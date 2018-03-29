package linkedin.tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Created by erict on 2017/11/29.
 */
public class ReshapeTree {

    public class Tree {
        public Node m_root;
        public Tree(Node root) { m_root = root; }
    }

    public class Node {
        public String m_name;
        public List<Node> m_children;
        public Node(String name) {
            m_name = name;
            m_children = null;
        }
        public int addChild(Node node) {
            if (m_children == null) m_children = new ArrayList<>();
            m_children.add(node);
            return m_children.size();
        }
        public void clearChildren() {
            m_children.clear();
        }
    }

    public Tree reshape(Tree inTree, int k) throws Exception {
        if ( (inTree == null) || (inTree.m_root == null) ) return null;
        if (k <= 0) throw new Exception("Illegal param");
        List<Node> nodeList = traverseBFS(inTree.m_root);
        Node newRoot = null;
        Queue<Node> queue = new LinkedList<>();
        Node curNode = null;
        for (Node node : nodeList) {
            node.clearChildren();
            queue.offer(curNode);
            if (curNode == null) {
                curNode = node;
                newRoot = curNode;
            }
            else {
                int numChildren = curNode.addChild(node);
                if (numChildren >= k) curNode = queue.poll();
            }
        }
        return new Tree(newRoot);
    }

    private List<Node> traverseBFS(Node node) {
        // TODO: ...
        return null;
    }
}
