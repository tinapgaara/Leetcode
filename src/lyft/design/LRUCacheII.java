package lyft.design;
import java.util.*;
public class LRUCacheII {
    public class Node {
        Node prev;
        Node next;
        int val;
        int key;
        public Node(int key, int val) {
            this.key = key;
            this.val = val;
        }
    }
    Map<Integer, Node> hashmap = new HashMap<>();
    Node head;
    Node tail;
    int maxsize;
    public LRUCacheII(int capacity) {
        hashmap = new HashMap<>();
        head = new Node(-1, -1);
        tail = new Node(-1, -1);
        this.maxsize = capacity;
        head.next = tail;
        tail.prev = head; // important!!!
    }
    public int get(int key) {
        if (hashmap.containsKey(key)) {
            Node node = hashmap.get(key);
            removeNode(node);
            moveToTail(node);
            return node.val;
        }
        else {
            return -1;
        }
    }
    public void put(int key, int value) {
        if (hashmap.containsKey(key)) {
            hashmap.get(key).val = value; // update the value
            get(key); // update loc
        }
        else if (hashmap.size() + 1 <= maxsize) {
            // put new node
            Node newNode = new Node(key, value);
            moveToTail(newNode);
            hashmap.put(key, newNode);
        }
        else {
            // out of capcity
            // remove oldest one
            Node removedNode = head.next;
            if (removedNode != null) {
                hashmap.remove(removedNode.key);
                removeNode(removedNode);
            }
            // insert new node
            Node newNode = new Node(key, value);
            moveToTail(newNode);
            hashmap.put(key, newNode);
        }
    }
    public void removeNode(Node cur) {
        Node prev = cur.prev;
        Node next = cur.next;
        prev.next = next;
        next.prev = prev;
    }
    public void moveToTail(Node cur) {
        Node beforeTail = tail.prev;
        cur.next = tail;
        tail.prev = cur;
        beforeTail.next = cur;
        cur.prev = beforeTail;
    }
}
