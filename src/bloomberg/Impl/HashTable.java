package bloomberg.Impl;

import dataStructure.hastable.Slot;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yingtan on 11/16/15.
 */
public class HashTable {

    private Slot[] slots;
    private int size;
    private int MAX_SIZE = 20;
    private int LARGEST_PRIME = 19;

    public HashTable() {
        slots = new Slot[MAX_SIZE];
        size = 0;
    }

    public int hashFunction(int key) {
        return key % LARGEST_PRIME;
    }

    public int hashFunctionForStr(String str) {
        char[] chs = str.toCharArray();

        int sum = 0;
        for (int i = 0 ; i< chs.length; i ++) {
            sum = sum + chs[i];
        }
        return sum % MAX_SIZE;
    }

    public int openAddressLinearHashFunction(int key, int index) {
        int remain = key % MAX_SIZE;
        return (remain + index) % MAX_SIZE;
    }

    public void put(Integer key, Integer value) {
        int index = hashFunction(key);

        if (slots[index] != null) {
            slots[index].getList().add(value);
        }
        else {
            ArrayList<Integer> newlist = new ArrayList<>();
            newlist.add(value);
            Slot newslot = new Slot(newlist);

            slots[index] = newslot;
        }
        size ++;
    }

    public void putWithoutConflict(Integer key, Integer value) {
        for (int i = 0 ; i < MAX_SIZE; i ++) {
            int index = openAddressLinearHashFunction(key, i);
            if (slots[index] == null) {
                ArrayList<Integer> newlist = new ArrayList<>();
                newlist.add(value);
                Slot newslot = new Slot(newlist);

                slots[index] = newslot;

                size ++;
            }
        }
    }

    public int get(Integer key) {
        int index = hashFunction(key);

        if (slots[index] != null) {
            return slots[index].getList().get(0);
        }
        else
            return 0;
    }

    public boolean isContainsKey(Integer key) {
        return !(slots[hashFunction(key)] == null);
    }

    public boolean isEmpty() {
        return size == 0;
    }
}
