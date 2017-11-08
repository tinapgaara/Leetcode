package google.design;

import java.util.HashMap;
import java.util.LinkedHashSet;

/**
 * Created by yingtan on 8/23/17.
 *
 * 460. LFU Cache
 DescriptionHintsSubmissionsDiscussSolution
 Discuss Pick One
 Design and implement a data structure for Least Frequently Used (LFU) cache. It should support the following operations: get and put.

 get(key) - Get the value (will always be positive) of the key if the key exists in the cache, otherwise return -1.
 put(key, value) - Set or insert the value if the key is not already present. When the cache reaches its capacity, it should invalidate the least frequently used item before inserting a new item. For the purpose of this problem, when there is a tie (i.e., two or more keys that have the same frequency), the least recently used key would be evicted.

 Follow up:
 Could you do both operations in O(1) time complexity?

 Example:

 LFUCache cache = new LFUCache( 2  capacity  );

        cache.put(1, 1);
        cache.put(2, 2);
        cache.get(1);       // returns 1
        cache.put(3, 3);    // evicts key 2
        cache.get(2);       // returns -1 (not found)
        cache.get(3);       // returns 3.
        cache.put(4, 4);    // evicts key 1.
        cache.get(1);       // returns -1 (not found)
        cache.get(3);       // returns 3
        cache.get(4);       // returns 4
 */


public class LFUCache {

    public HashMap<Integer, Integer> cache; // key -> value
    public HashMap<Integer, LinkedHashSet<Integer>> lists; // count -> list of vals which is this count
    public HashMap<Integer, Integer> counts; // key -> counts

    public int maxCapacity;
    public int minCount; // less frequent visited count


    public LFUCache(int capacity) {
        maxCapacity = capacity;

        cache = new HashMap<Integer, Integer>();
        counts = new HashMap<Integer, Integer>();
        lists = new HashMap<>();
        lists.put(1, new LinkedHashSet<Integer>());
        minCount = -1;
    }

    public int get(int key) {
        if (! cache.containsKey(key)) return -1;
        int res = cache.get(key);
        int count = counts.get(key);
        // visit here one more time, so counts ++
        counts.put(key, count + 1);

        // Remove this key from lists with var 'count', since its count has increased one
        lists.get(count).remove(key);

        // Update min count
        // in lists, no element has this count, min count becomes min + 1 because counts.put(key, count + 1);
        if (count == minCount && lists.get(count).size() == 0) {
            minCount++;
        }

        // Update the list to include val for count+1
        if ( ! lists.containsKey(count+1)) {
            lists.put(count+1, new LinkedHashSet<>());
        }
        lists.get(count+1).add(key);
        return res;
    }

    public void put(int key, int value) {
        if (maxCapacity == 0) return;
        // this value already here , no need to update maxCap
        if (cache.containsKey(key)) {
            cache.put(key, value);
            // update vis frequency == get function
            get(key);
        }
        else { // value is not here , need to judge if +1 > maxCap
            if (cache.size() >= maxCapacity) {
                // need to eliminate
                // Find evit by using minCount, first element corresponding to the minCount
                int evit = lists.get(minCount).iterator().next();
                // remove this evit from lists
                lists.get(minCount).remove(evit);
                // remove this evet from cache
                cache.remove(evit);
                counts.remove(evit);
            }
            // put new one to cache, counts and lists
            cache.put(key, value);
            counts.put(key, 1);
            lists.get(1).add(key);
            // update minCount, become to 1 since key appears only once
            minCount = 1;
        }
    }
}
