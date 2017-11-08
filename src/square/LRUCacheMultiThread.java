package square;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by yingtan on 10/16/17.
 *
 * 一个是 LRUCache LRU cache 然后又讨论了下怎么实现thread safe(synchronization, read/write lock之类的）
 */
public class LRUCacheMultiThread {

    private int size;
    private Map<Integer, Node> cache;
    private Node head;
    private Node tail;

    public LRUCacheMultiThread(int capacity) {
        size = capacity;
        cache = new HashMap<Integer, Node>();
        head = new Node(-1, -1);
        tail = new Node(-1, -1);
        // important !!!
        head.next = tail;
        tail.prev = head;
    }

    public synchronized int get(int key) {
        int res = -1;
        if (cache.containsKey(key)) {
            Node node = cache.get(key);
            res = node.val;

            // remove the current node at the current pos
            remove(node);
            moveToTail(node);
        }
        return res;
    }

    public synchronized void remove(Node node) {
        Node prev = node.prev;
        Node next = node.next;
        prev.next = next;
        next.prev = prev;
    }

    public synchronized void moveToTail(Node node) {
        Node beforeTail = tail.prev;
        node.next = tail;
        tail.prev = node;
        beforeTail.next = node;
        node.prev = beforeTail;
    }

    public synchronized void put(int key, int value) {
        if (cache.containsKey(key)) {
            // reset this key in map
            Node node = cache.get(key);
            node.val = value;
            // mark latest visited
            remove(node);
            moveToTail(node);
        }
        else if (cache.size() < size) {
            // add new key to cache
            Node insert = new Node(key, value);
            cache.put(key, insert);
            moveToTail(insert);
        }
        else {
            // out of capacity
            // 1. remove the oldest key which is the head of list
            Node removed = head.next;
            head.next = removed.next;
            head.next.prev = head;

            // 2. remove this (oldestKey, oldestValue) from cache
            cache.remove(removed.key);

            // 3. add new key and value
            Node insert = new Node(key, value);
            cache.put(key, insert);

            // 4. update the visitKey
            moveToTail(insert);
        }
    }

    // use double linkedlist
    public class Node {
        int key;
        int val;
        Node prev;
        Node next;

        public Node(int key, int val) {
            this.key = key;
            this.val = val;
            this.prev = null;
            this.next = null;
        }
    }

}
