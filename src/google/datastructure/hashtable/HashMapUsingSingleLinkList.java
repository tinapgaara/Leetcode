package google.datastructure.hashtable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yingtan on 6/25/17.
 *
 * http://robl.co/implement-your-own-hashmap/
 *
 *
 * Important Notes:
 * 1. buckets are indexed using key's hashcode
 * 2. everytime when hashcode matches, does not mean that key is the same. In collision case, the hashcode is the same, but the key is diff
 * 3. we store the Node which contains key and value, everytime when do put, and get, need to compare the key even with matched hashcode
 */
public class HashMapUsingSingleLinkList {

    private Node buckets[];
    private int BUCKET_SIZE = 256;

    // use singleLinkedList, only can find next
    private class Node {
        Node next;
        String key;
        int val;

        public Node(String key, int val) {
            this.key = key;
            this.val = val;
            next = null;
        }
    }

    public HashMapUsingSingleLinkList() {
        buckets = new Node[BUCKET_SIZE];
    }

    // worst: o(n), best : o(1)
    public void put(String key, int value) {
        int hashCode = (key.hashCode() % BUCKET_SIZE);
        // no collision
        if (buckets[hashCode] == null) {
            buckets[hashCode] = new Node(key, value);
        }
        // has collision
         /* Collision detected. We must place the node at the end of the linked list. */
        /*  Or, this key already exists and we need to update its value */
        else {
            Node cur = buckets[hashCode];
            while (cur.next != null) {
                // Important !!! need to update !!!
                /*
                * eg 1:
                * hashcode 1001:  ["3", 1] -> ["20", 2]
                * put("3", 2)
                * after updating:
                * hashcode 1001:  ["3", 2] -> ["20", 2]
                * */
                if (cur.key.equals(key)) {
                    cur.val = value;
                    return;
                }
                cur = cur.next;
            }

            /*
            * eg 2:
            * hashcode 1001:  ["3", 1] -> ["20", 2]
            * put("20", 3)
            * after updating:
            * hashcode 1001:  ["3", 1] -> ["20", 3]
            * */
            if (cur.key.equals(key)) {
                cur.val = value;
                return;
            }
            /*
            * eg 3:
              hashcode 1001: ["3", 1] -> ["20", 2]
              put("42", 5)
              after updating:
              hashcode 1001: ["3", 1] -> ["20", 2] -> ["42", 5]
            * */
            cur.next = new Node(key,value);
        }
    }

    // worst case : o(n), best case : o(1)
    public Integer get(String key) {
        int hashcode = (key.hashCode() % BUCKET_SIZE);
        if (buckets[hashcode] != null) {
            // can't just return this one, even hashcode match, the key may not match
            Node cur = buckets[hashcode];
            while (cur != null) {
                // Important !!! : must match the key, if does not match the key
                if (cur.key.equals(key)) {
                    return cur.val;
                }
                // does not match the key, keep searching
                else {
                    cur = cur.next;
                }
            }
        }
        return null;
    }

    // worst: o(n), best : o(1)
    public Integer remove(String key) {
        int hashcode = (key.hashCode() % BUCKET_SIZE);
        if (buckets[hashcode] != null) {
            Node cur = buckets[hashcode];
            Node prev = null;
            while (cur != null) {
                if (cur.key.equals(key)) {
                    // this is the node we want to remove
                    if (prev != null) {
                        prev.next = cur.next;
                    }
                    else {
                        // point head to cur.next
                        buckets[hashcode] = cur.next;
                    }
                    return cur.val;
                }
                prev = cur;
                cur = cur.next;
            }
        }
        return null;
    }


}
