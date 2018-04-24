package purestorage;

/**
 * Created by yingtan on 4/7/18.
 */
public class HashTable {
    public int maxsize;
    public Bucket[] buckets;
    public HashTable(int size) {
        maxsize = size;
        buckets = new Bucket[size];
    }
    public int hashcode(int key) {
        return key % maxsize;
    }
    public int get(int key) {
        int index = hashcode(key);
        Bucket bucket = buckets[index];
        if (bucket != null) {
            Integer node = bucket.nodes[key];
            if (node != null) {
                return node.intValue();
            }

        }
        return -1;
    }
    public void put(int key, int val) {
        int index = hashcode(key);
        Bucket bucket = buckets[index];
        if (bucket != null) {
            Integer node = bucket.nodes[key];
            if (node == null) {
                node = val;
                bucket.nodes[key] = node;
            }
            else {
                node = val;
            }
        }
    }
    public void delete(int key) {
        int index = hashcode(key);
        Bucket bucket = buckets[index];
        if (bucket != null) {
            Integer node = bucket.nodes[key];
            if (node != null) {
                bucket.nodes[key] = null;// set to null, delete
            }
        }
    }
    public void clear() {
        maxsize = 0;
        buckets = null;
    }
}
