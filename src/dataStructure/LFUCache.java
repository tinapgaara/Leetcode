package dataStructure;

import java.util.*;

/**
 * Created by yingtan on 5/7/17.
 */
public class LFUCache {

    // TLE
    public HashMap<Integer, Integer> cache; // key -> value
    public int maxCapacity;
    public List<Integer> visitKey; // latest visited one is at tail, least visited one is at head
    public HashMap<Integer, Integer> counts; // key -> counts
    public PriorityQueue<Integer> sortedKeys; // sorted key based on frequency and visit time

    public class KeyComparator implements Comparator<Integer> {
        @Override
        public int compare(Integer i1, Integer i2) {
            if (counts.size() == 0) return 0;

            if (counts.get(i1.intValue()) > counts.get(i2.intValue())) return 1;
            else if (counts.get(i1.intValue()) < counts.get(i2.intValue())) return -1;
            else {
                if (visitKey.indexOf(i1) > visitKey.indexOf(i2)) {
                    // i1 is newer than i2
                    return 1;
                }
                else if (visitKey.indexOf(i1) < visitKey.indexOf(i2)) {
                    // i1 is newer than i2
                    return -1;
                }
                else
                    return 0;
            }
        }
    }

    public LFUCache(int capacity) {
        maxCapacity = capacity;

        cache = new HashMap<Integer, Integer>();
        counts = new HashMap<Integer, Integer>();
        visitKey = new ArrayList<Integer>();

        KeyComparator comparator = new KeyComparator();
        sortedKeys = new PriorityQueue<>(comparator);
    }

    public int get(int key) {
        int res = -1;
        if (cache.containsKey(key)) {
            res = cache.get(key);

            int count = counts.get(key);
            counts.put(key, count  + 1);

            // adjust visitKey
            visitKey.remove(new Integer(key));
            visitKey.add(key);

            // adjust sortedKey
            sortedKeys.remove(new Integer(key));
            sortedKeys.add(key);

        }
        return res;
    }

    public void put(int key, int value) {
        if (maxCapacity == 0) return;

        if (cache.containsKey(key)) {
            cache.put(key, value);

            // update the count to visit this key
            int count = counts.get(key);
            counts.put(key, count + 1);

            // update visitKey
            visitKey.remove(new Integer(key));
            visitKey.add(key);

            // update sortedKey
            sortedKeys.remove(new Integer(key));
            sortedKeys.add(key);
        }
        else if (cache.size() < maxCapacity) {
            cache.put(key, value);

            counts.put(key, 1);

            visitKey.add(key);

            sortedKeys.add(key);
        }
        else {
            // eliminate the least freq
            int leastUsedKey = sortedKeys.poll();
            int leastUsedValue = cache.get(leastUsedKey);

            cache.remove(leastUsedKey, leastUsedValue);

            counts.remove(leastUsedKey);
            visitKey.remove(new Integer(leastUsedKey));

            // add new element
            counts.put(key, 1);
            cache.put(key, value);
            visitKey.add(key);
            sortedKeys.add(key);
        }
    }

    public static void main(String[] args) {
        /*
        LFUCache cache = new LFUCache(2);
        cache.put(1, 1);
        cache.put(2, 2);
        cache.get(1);
        cache.put(3, 3);    // evicts key 2
        cache.get(2);       // returns -1 (not found)
        cache.get(3);       // returns 3.
        cache.put(4, 4);    // evicts key 1.
        cache.get(1);       // returns -1 (not found)
        cache.get(3);       // returns 3
        cache.get(4);       // returns 4
        */

        LFUCache cache = new LFUCache(0);
        cache.put(0, 0);
        cache.get(0);
    }
}
