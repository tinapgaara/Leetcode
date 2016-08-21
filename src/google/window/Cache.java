package google.window;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * Created by yingtan on 11/26/15.
 */
/*
* implement一个cache, 这个 cache要求实现get(id), set(i), remove(i),
* cache的基本特性就是要能够保证out of date 的 cache要不停的被kick out
* */
public class Cache { // LRUCache

    private Map<Integer, Integer> map;
    private LinkedList<Integer> cache;
    private int MAX_NUM;

    public Cache(int maxnum) {
        map = new HashMap<>();
        cache = new LinkedList<>();
        MAX_NUM = maxnum;
    }

    public int get(int id) {
        int res  = -1;
        if (map.containsKey(id)) {
            res = map.get(id);

            cache.remove(new Integer(id));
            cache.add(id);
        }
        return res;
    }

    public void set(int key, int value) {
        if (map.containsKey(key)) {
            map.put(key, value);
            cache.remove(new Integer(key));
            cache.add(key);
        }
        else {
            // add a new element
            if (map.size() < MAX_NUM) {
                map.put(key, value);
                cache.add(key);
            }
            else {
                // remove head element from cache
                int removedNumIndex = cache.removeFirst();

                // remove this element from map
                int removedNum = map.get(removedNumIndex);
                map.remove(removedNumIndex, removedNum);

                // add new element
                cache.add(key);
                map.put(key, value);
            }
        }
    }

}
