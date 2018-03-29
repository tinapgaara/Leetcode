package linkedin.impl;

import java.util.LinkedList;
import java.util.Map;

/**
 * Created by yingtan on 11/30/17.
 */
public class HashTable {

    int maxSize;
    Slot[] slots;

    private static final int LARGEST_PRIME = 19;

    public HashTable(int size) {
        this.maxSize = size;
        slots = new Slot[size];
    }

    public int hash(int key)
    {
        return key % LARGEST_PRIME;
    }

    public void put(int key, int value) {
        int hashcode = hash(key);
        if (slots[hashcode] == null) {
            Slot slot = new Slot();
            slot.list.add(new Entry(key, value));
        }
        else {
            Slot slot = slots[hashcode];
            LinkedList<Entry> list = slot.list;
            for (Entry entry : list) {
                if (entry.key == key) {
                    entry.value = value;
                    return;
                }
            }
            // collision
            slot.list.add(new Entry(key, value));
        }
    }

    public boolean contains(int key) {
        int hashcode = hash(key);
        if(slots[hashcode] == null) return false;
        else return true;
    }

    public int get(int key) {
        if(contains(key)) {
            int hashcode = hash(key);
            LinkedList<Entry> res = slots[hashcode].list;
            for (Entry entry : res) {
                if (entry.key == key) {
                    return entry.value;
                }
            }
        }
        return -1;
    }

    public int remove(int key) {
        if(contains(key)) {
            int hashcode = hash(key);
            LinkedList<Entry> res = slots[hashcode].list;
            for (int i = 0 ; i < res.size(); i ++) {
                if (res.get(i).key == key) {
                    return res.remove(i).value; // o(1) for double linkedlist
                }
            }
        }
        return -1;
    }

}
