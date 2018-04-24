package purestorage;

/**
 * Created by yingtan on 4/7/18.
 */
public class Node {
    Node prev;
    Node next;
    int val;
    boolean isDelete;
    public Node(int val) {
        this.val = val;
        isDelete = false;
    }
}
