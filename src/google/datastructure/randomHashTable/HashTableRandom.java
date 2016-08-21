package google.datastructure.randomHashTable;

import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * Created by yingtan on 11/26/15.
 */
/*
* Implement HashTable with get,set,delete,getRandom
functions in O(1).
这题之前地⾥里有
*
* */
public class HashTableRandom {

    private List<Integer> list; // key
    private Map<Integer, Integer> map; // key - value
    private Map<Integer, Integer> indexs; // key -> index in list

    public int get(int index) {
        int key = list.get(index);
        return map.get(key);
    }

    public void set(int key, int value) {
        if ( !map.containsKey(key)) {
            indexs.put(key, list.size());
            list.add(key);
        }
        map.put(key, value);
    }

    // o (1)
    public void delete(int key) {
        if (map.containsKey(key)) {
            int index = indexs.get(key);
            list.remove(index); //o (n)
            map.remove(key);
        }
    }

    // o(1)
    public int getRandom() {
        int size = list.size();
        Random random = new Random();
        int index =  random.nextInt(size);
        int key = list.get(index);
        return map.get(key);
    }
}
