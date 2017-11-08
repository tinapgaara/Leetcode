package square;

import java.util.Comparator;
import java.util.TreeSet;

/**
 * Created by yingtan on 10/19/17.
 *
 * 是写一个function, drop(double position, double size), 即从高处掉一个方块，左边的x坐标是position，方块的边长是size。然后它会一个个叠起来。
 比如drop(1,4), drop(3,3)的话就会变成这样：
 bbb
 bbb
 bbb
 aaaa
 aaaa
 aaaa. from: 1point3acres.com/bbs
 aaaa

 然后它掉一大堆方块下来之后任何时候想要call一个叫getHeight()的function的话，你就得返回这堆方块里地最高点（但getHeight在面试中没有写，面试官只叫我写了drop）

 我就用map做了，做完后面试官说可以用tree，这样可以更加efficient。我感觉他那个方法还是没太懂。。。有没有大神来解释一下。。。. 鐣欏鐢宠璁哄潧-涓€浜╀笁鍒嗗湴

 */
public class DropSquare {
    private static class Node {
        private int low;
        private int high;
        private int height;

        public Node(int low, int high, int height) {
            this.low = low;
            this.high = high;
            this.height = height;
        }
    }
    private TreeSet treeset;
    private float maxHeight;
    private NodeComparator comparator;

    public DropSquare() {
        comparator = new NodeComparator();
        treeset = new TreeSet(comparator);
        maxHeight = 0;
    }

    public class NodeComparator implements Comparator<Node> {
        public int compare(Node x, Node y) {
            return x.low - y.low;
        }
    }

    public void drop(int position, int size) {
        Node newnode = new Node(position, position + size, size);
        Node left = getLeftBound(newnode);
        Node right = getRightBound(newnode);
        if (left == null && right == null) {
            treeset.add(newnode);
            maxHeight = Math.max(maxHeight, size);
            return;
        }
        int curMaxHeight = left.height;
        Node cur = left;
        while (cur != right) {
            Node tmp = (Node)treeset.higher(cur);
            curMaxHeight = Math.max(curMaxHeight, cur.height);
            treeset.remove(cur);
            cur = tmp;
        }
        curMaxHeight = Math.max(curMaxHeight, right.height);
        treeset.remove(right);
        if (left.low < newnode.low) {
            treeset.add(new Node(left.low, newnode.low, left.height));
        }
        if (newnode.high < right.high) {
            treeset.add(new Node(newnode.high, right.high, right.height));
        }
        treeset.add(new Node(newnode.low, newnode.high, size + curMaxHeight));
        maxHeight = Math.max(maxHeight, size + curMaxHeight);
    }

    public float getHeight() {
        return maxHeight;
    }

    private Node getLeftBound(Node n) {
        Node smaller = (Node)treeset.lower(n);
        if (smaller != null && overlap(smaller, n)) {
            return smaller;
        }
        Node larger = (Node)treeset.ceiling(n);
        if (larger != null && overlap(larger, n)) {
            return larger;
        }
        return null;
    }
    private Node getRightBound(Node n) {
        Node dummy = new Node(n.high, n.high, n.height);
        Node smaller = (Node)treeset.floor(dummy);
        if (smaller != null && overlap(smaller, n)) {
            return smaller;
        }
        return null;
    }
    private boolean overlap(Node x, Node y) {
        return !greater(x.low, y.high) && !less(x.high, y.low);
    }
    private boolean greater(float x, float y) {
        return x - y > 1e-10;
    }
    private boolean less(float x, float y) {
        return x - y < -1e-10;
    }
    public static void main(String args) {
        // TODO Auto-generated method stub
        DropSquare test = new DropSquare();
        test.drop(1,4);
        System.out.println(test.getHeight());
        test.drop(3,3);
        /*
        System.out.println(test.getHeight()); test.drop(-1,3);
        System.out.println(test.getHeight()); test.drop(5.5f, 3);
        System.out.println(test.getHeight()); test.drop(10, 5);
        System.out.println(test.getHeight()); test.drop(-1, 6.5f);
        System.out.println(test.getHeight());
        */
    }

}
