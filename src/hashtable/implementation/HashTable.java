package hashtable.implementation;

/**
 * Created by yingtan on 3/16/18.
 */
import java.util.*;
public class HashTable {
    private Bucket[] lists;
    private int max_size = 1000;
    public HashTable() {
        lists = new Bucket[max_size];
    }
    public int hashcode(String key) {
        int sum = 0;
        for (char ch : key.toCharArray()) {
            sum = sum * 31 + (int)ch;
            sum = sum % max_size;
        }
        return sum;
    }
    public int get(String key) {
        int index = hashcode(key) % max_size;
        Bucket bucket = lists[index];
        while(bucket != null) {
            if (bucket.getKey().equals(key)) {
                return bucket.getValue();
            }
            else {
                bucket = bucket.next;
            }
        }
        return -1;
    }
    public boolean put(String key, int val) {
        int index = hashcode(key) % max_size;
        if (lists[index] == null) {
            Bucket bucket = new Bucket(key, val);
            lists[index] = bucket;
            return true;
        }
        Bucket bucket = lists[index];
        Bucket prev = null;
        // find the place to insert the current key
        while(bucket != null) {
            if (bucket.getKey().equals(key)) {
                bucket.setValue(val);
                return true;
            }
            prev = bucket;
            bucket = bucket.next;
        }
        // add to the end to chain
        prev.next = new Bucket(key, val);
        return true;
    }
    public int delete(String key) {
        int index = hashcode(key) % max_size;
        Bucket bucket = lists[index];
        while(bucket != null) {
            if (bucket.getKey().equals(key)) {
                // one we want to delete
                Bucket next = bucket.next;
                Bucket prev = bucket.prev;
                prev.next = next;
                next.prev = prev;
                return bucket.getValue();
            }
        }
        return -1;
    }

}
