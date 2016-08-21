package google.datastructure.redblack;

/**
 * Created by yingtan on 11/24/15.
 */
public class RedBlackTreeNode {

    private RedBlackTreeNode left;
    private RedBlackTreeNode right;

    private int element;
    private int color; // black: 0;   red:1

    public RedBlackTreeNode(int element) {
        this.element = element;
    }

    public RedBlackTreeNode(RedBlackTreeNode left, RedBlackTreeNode right, int element) {
        this.element = element;
        this.left = left;
        this.right = right;
        color = 1;
    }
}
