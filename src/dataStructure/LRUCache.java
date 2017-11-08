package dataStructure;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by yingtan on 10/13/15.
 */
/*
* Leetcode: LRU Cache
*
* Design and implement a data structure for Least Recently Used (LRU) cache. It should support the following operations: get and set.

    get(key) - Get the value (will always be positive) of the key if the key exists in the cache, otherwise return -1.
    set(key, value) - Set or insert the value if the key is not already present. When the cache reached its capacity, it should invalidate the least recently used item before inserting a new item.
    *
*
* Idea:
* Keep a Map to store cache
* Keep a list to store the key's of elements which are visited as increasing time:
*   new element is added at tail, old one is at head.
*
* When out of capacity:
*   1) find the oldest one in key list (get head element)
*   2) delete the corresponding value of this key in cache
*   3) Update the key's list: delete old one, and insert new one at tail.
*
* */
public class LRUCache {

    public List<Integer> m_visitedKeys; // oldest element is at head of the lists
    public Map<Integer, Integer> m_cache; // elements stored in cache
    public int m_maxCapacity;

    public LRUCache(int capacity) {
        m_maxCapacity = capacity;
        m_visitedKeys = new ArrayList<Integer>();
        m_cache = new HashMap<Integer, Integer>();
    }

    public int get(int key) {
        if (m_cache.containsKey(key)) {
            int res = m_cache.get(key); // got element from cache
            m_visitedKeys.remove(new Integer(key)); // remove the current key because it is visited
            // add this element to head: shows that this is been visited recently
            m_visitedKeys.add(key);

            return res;
        }
        else
            return -1;
    }

    public void set(int key, int value) {
        // if m_cache contains key
        if (m_cache.containsKey(key)) {
            m_cache.put(key, value);
            m_visitedKeys.remove(new Integer(key)); // remove the current key because it is visited
            // add this element to head: shows that this is been visited recently
            m_visitedKeys.add(key);
        } // if not:
        else if (m_cache.size() + 1 <= m_maxCapacity) {
            // 1) in capacity  do not need to remove it from visitedList.
            m_cache.put(key, value);
            m_visitedKeys.add(key);
        } else { // 2) out of capacity
            // Get the oldest key from list
            int lastUnUsedKey = m_visitedKeys.get(0);
            // Remove this key from list
            m_visitedKeys.remove(0);

            // Get the oldest key's corresponding value in cache
            int lastUnUsedVal = m_cache.get(lastUnUsedKey);
            // Remove this value from cache
             /*
            * PAY ATTENTION !!!!1 Must use remove (key, value) at the same time,
            *
            * remove(new Integer(key)): can not remove the existed Object key in cache.
            *
            * */
            m_cache.remove(lastUnUsedKey, lastUnUsedVal);

            // Finally, insert the new element to cache
            m_cache.put(key, value);
            // Finally, insert the new element to head of list.
            m_visitedKeys.add(key);
        }
    }

    public static void main(String[] args) {
        LRUCache ob = new LRUCache(10);
        ob.set(10,13);
        ob.set(3,17);


       ob.set(6,11);
        ob.set(10,5);
        ob.set(9,10);
        ob.get(13);
        ob.set(2,19);
        ob.get(2);
        ob.get(3);
        ob.set(5,25);
        ob.get(8);
        ob.set(9,22);
        ob.set(5,5);
        ob.set(1,30);
        ob.get(11);
        ob.set(9,12);
        ob.get(7);
        ob.get(5);
        ob.get(8);
        ob.get(9);
        ob.set(4,30);
        ob.set(9,3);
        ob.get(9);
        ob.get(10);
        ob.get(10);
        ob.set(6,14);
        ob.set(3,1);
        ob.get(3);ob.set(10,11);ob.get(8);
        ob.set(2,14);
        ob.get(1);
        ob.get(5);
        ob.get(4);ob.set(11,4);ob.set(12,24);
        ob.set(5,18);
        ob.get(13);
        ob.set(7,23);
        ob.get(8);ob.get(12);
        ob.set(3,27);
        ob.set(2,12);ob.get(5);ob.set(2,9);
        ob.set(13,4);
        ob.set(8,18);ob.set(1,7);ob.get(6);ob.set(9,29);ob.set(8,21);ob.get(5);ob.set(6,30);ob.set(1,12);ob.get(10);ob.set(4,15);ob.set(7,22);
        ob.set(11,26);

        ob.set(8,17);ob.set(9,29);ob.get(5);ob.set(3,4);ob.set(11,30);ob.get(12);ob.set(4, 29);ob.get(3);ob.get(9);

        ob.get(6);ob.set(3,4);ob.get(1);
        ob.get(10);ob.set(3, 29);ob.set(10, 28);ob.set(1, 20);ob.set(11, 13);ob.get(3);ob.set(3,12);ob.set(3,8);
        ob.set(10,9);
        ob.set(3,26);
        ob.get(8);ob.get(7);ob.get(5);
        ob.set(13, 17);ob.set(2, 27);ob.set(11,15);ob.get(12);ob.set(9, 19);ob.set(2, 15);ob.set(3,16);ob.get(1);ob.set(12,17);
        ob.set(9,1);
        ob.set(6,19);
        ob.get(4);
        ob.get(5);ob.get(5);ob.set(8,1);ob.set(11,7);ob.set(5,2);ob.set(9,28);ob.get(1);ob.set(2, 2);
        ob.set(7, 4);
        ob.set(4,22);ob.set(7,24);ob.set(9,26);ob.set(13,28);ob.set(11,26);
    }
}
