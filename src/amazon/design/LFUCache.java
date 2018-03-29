package amazon.design;
import java.util.*;
public class LFUCache {
    public class Node {
        Node prev;
        Node next;
        int key;
        public Node(int key) {
            this.key = key;
        }
    }
    KeyCpmparator comp = new KeyCpmparator();
    Map<Integer, Integer> keyToValue = new HashMap<>();
    // from high freq -> low freq; in the same bucket, from recently used -> less recently used
    SortedMap<Integer, Node> freqToMultipleKeys = new TreeMap<>(comp); // less frequent -> more frequent
    Map<Integer, Integer> keyToFrequency = new HashMap<>();
    int maxsize;
    public LFUCache(int capacity) {
        maxsize = capacity;
        keyToValue = new HashMap<>();
        keyToFrequency = new HashMap<>();
        freqToMultipleKeys = new TreeMap<>();
    }

    public int get(int key) {
        if (keyToValue.containsKey(key)) {
            int res = keyToValue.get(key);
            // add freq
            int oldfreq = 0;
            if (keyToFrequency.containsKey(key)) {
                oldfreq = keyToFrequency.get(key);
            }
            int newfreq = oldfreq + 1;
            keyToFrequency.put(key, newfreq);
            // update the treemap
            // removefrom oldfreq bucket
            Node head = freqToMultipleKeys.get(oldfreq);
            Node cur = head;

            while (cur != null) {
                if (cur.key == key) {
                    // remove key from this freq bucket
                    Node prev = cur.prev;
                    Node next = cur.next;
                    if (prev != null && next != null) {
                        prev.next = next;
                        next.prev = prev;
                    }
                    else if (prev != null){
                        prev.next = next;
                    }
                    else if (next != null) {
                        next.prev = prev;
                        head = next;
                    }
                    else {
                        // only one node here
                        freqToMultipleKeys.remove(oldfreq);
                        break;
                    }
                    freqToMultipleKeys.put(oldfreq, head);
                }
                cur = cur.next;
            }
            // add to newfreq bucket
            Node newhead = new Node(key);
            if (freqToMultipleKeys.containsKey(newfreq)) {
                Node curhead = freqToMultipleKeys.get(newfreq);
                newhead.next = curhead;
                if (curhead != null) {
                    curhead.prev = newhead;
                }
            }
            freqToMultipleKeys.put(newfreq, newhead);
            return res;
        }
        return -1;
    }

    public void put(int key, int value) {
        if (keyToValue.containsKey(key)) {
            // update keyToValue
            keyToValue.put(key, value);
            get(key);
        }
        else {
            if (keyToValue.size() < maxsize) {
                // can put new one to it
                // update keyToValue
                keyToValue.put(key, value);
                // update keyToFrequency
                keyToFrequency.put(key, 1);
                // update freqToMultipleKeys
                Node head = freqToMultipleKeys.get(1);
                Node newhead = new Node(key);
                newhead.next = head;
                if (head != null)  head.prev = newhead;
                freqToMultipleKeys.put(1, newhead);
            }
            else {
                if (maxsize == 0) return;
                // eliminate the less frequent, and add new one
                // get the least frequent
                Integer leastFrequency = freqToMultipleKeys.firstKey();
                Node leastNode = freqToMultipleKeys.get(leastFrequency);
                while(leastNode.next != null) {
                    leastNode = leastNode.next;
                }
                Integer leastKey = leastNode.key;
                // update keyToValue
                keyToValue.remove(leastKey);
                // update keyToFrequency
                keyToFrequency.remove(leastKey);
                // update freqToMultipleKeys, if there are multiple ones, remove the tail(least recently used)
                while(leastNode.next != null) {
                    leastNode = leastNode.next;
                }
                if (leastNode.prev != null) {
                    leastNode.prev.next = null;
                }
                else {
                    // there is only one node in this bucket, remove all
                    freqToMultipleKeys.remove(leastFrequency);
                }
                // add a new one
                // update keyToValue
                keyToValue.put(key, value);
                // update keyToFrequency
                keyToFrequency.put(key, 1);
                // update freqToMultipleKeys
                Node head = freqToMultipleKeys.get(1);
                Node newhead = new Node(key);
                newhead.next = head;
                if (head != null)  head.prev = newhead;
                freqToMultipleKeys.put(1, newhead);
            }
        }
    }
    public class KeyCpmparator implements Comparator<Integer> {
        public int compare(Integer i1, Integer i2) {
            return i2 - i1;
        }
    }
    public static void main(String[] args) {
        LFUCache cache = new LFUCache(2);
        cache.put(0, 0);
        cache.put(2, 2);
        System.out.println(cache.get(1));       // returns 1
        cache.put(3, 3);    // evicts key 2
        System.out.println(cache.get(2));       // returns -1 (not found)
        System.out.println(cache.get(3));       // returns 3.
        cache.put(4, 4);    // evicts key 1.
        System.out.println(cache.get(1));       // returns -1 (not found)
        System.out.println(cache.get(3));       // returns 3
        System.out.println(cache.get(4));       // returns 4
    }
}
