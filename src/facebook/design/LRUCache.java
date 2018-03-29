package facebook.design;

import java.util.*;

/**
 * Created by yingtan on 12/20/17.
 *
 * 146. LRU Cache
 DescriptionHintsSubmissionsDiscussSolution
 DiscussPick One
 Design and implement a data structure for Least Recently Used (LRU) cache. It should support the following operations: get and put.

 get(key) - Get the value (will always be positive) of the key if the key exists in the cache, otherwise return -1.
 put(key, value) - Set or insert the value if the key is not already present. When the cache reached its capacity, it should invalidate the least recently used item before inserting a new item.

 Follow up:
 Could you do both operations in O(1) time complexity?

 Example:

 LRUCache cache = new LRUCache( 2 /* capacity */

public class LRUCache {

    LinkedHashMap<Integer, Integer> map;
    int capacity;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        map = new LinkedHashMap<Integer, Integer>(capacity, 1.0f, true) {
            protected boolean removeEldestEntry(Map.Entry<Integer, Integer> e) {
                return this.size() > capacity;
            }
        };
    }

    public int get(int key) {
        if (map.containsKey(key)) {
            return map.get(key);
        }
        return -1;
    }

    public void put(int key, int value) {
        map.put(key, value);
    }
    // implement the double linkedlist myself
    public class LRUCacheBetter {
        private int size;
        private Map<Integer, Node> cache;
        private Node head;
        private Node tail;

        public LRUCacheBetter(int capacity) {
            size = capacity;
            cache = new HashMap<Integer, Node>();
            head = new Node(-1, -1);
            tail = new Node(-1, -1);
            // important !!!
            head.next = tail;
            tail.prev = head;
        }

        public int get(int key) {
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

        public void remove(Node node) {
            Node prev = node.prev;
            Node next = node.next;
            prev.next = next;
            next.prev = prev;
        }

        public void moveToTail(Node node) {
            Node beforeTail = tail.prev;
            node.next = tail;
            tail.prev = node;
            beforeTail.next = node;
            node.prev = beforeTail;
        }

        public void put(int key, int value) {
            if (cache.containsKey(key)) {
                // reset this key in map
                Node node = cache.get(key);
                node.val = value;
                // mark latest visited
                remove(node);
                moveToTail(node);
            } else if (cache.size() < size) {
                // add new key to cache
                Node insert = new Node(key, value);
                cache.put(key, insert);
                moveToTail(insert);
            } else {
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
}
