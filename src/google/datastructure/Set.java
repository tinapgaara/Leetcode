package google.datastructure;

import java.util.*;

/**
 * Created by yingtan on 11/26/15.
 */
/*
* 设计一个Set类实现 insert, remove, getRandom in O(1) 面经题
*
* */
public class Set {

    private List<Integer> list;
    private Map<Integer, Integer> map; // store value -> index in array

    public Set() {
        list = new ArrayList<>();
        map = new HashMap<>();
    }

    public void insert(int element) { // o(1)
        if (map.containsKey(element)) {
            return;
        }
        else {
            int index = list.size();
            map.put(element, index);
            list.add(element);
        }
    }

    public void remove(int element) { // o(1)
        if (map.containsKey(element)) {
            int index = map.get(element);
            list.remove(index);// arraylist: remove index: o(1)
            map.remove(element);
        }
    }

    public int getRandom() { // o(1)
        int size = list.size();
        Random random = new Random();
        int randomIndex = random.nextInt(size);

        return list.get(randomIndex);
    }

}
