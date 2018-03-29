package uber.design;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by yingtan on 12/3/17.
 *
 * 432. All O`one Data Structure
 DescriptionHintsSubmissionsDiscussSolution
 Discuss Pick One
 Implement a data structure supporting the following operations:

 Inc(Key) - Inserts a new key with value 1. Or increments an existing key by 1. Key is guaranteed to be a non-empty string.
 Dec(Key) - If Key's value is 1, remove it from the data structure. Otherwise decrements an existing key by 1. If the key does not exist, this function does nothing. Key is guaranteed to be a non-empty string.
 GetMaxKey() - Returns one of the keys with maximal value. If no element exists, return an empty string "".
 GetMinKey() - Returns one of the keys with minimal value. If no element exists, return an empty string "".
 Challenge: Perform all these in O(1) time complexity.


 */
public class AllOneDataStructure {

    Map<Integer, Bucket> buckets;
    Map<String, Integer> values;
    Bucket head; // min value
    Bucket tail; // max value

    public class Bucket {
        Bucket prev;
        Bucket next;
        Set<String> keys;
        int val;

        public Bucket(int count) {
            prev = null;
            next = null;
            keys = new HashSet<>();
            this.val = count;
        }
    }

    /** Initialize your data structure here. */
    public AllOneDataStructure() {
        buckets = new HashMap<>();
        values = new HashMap<>();
        head = new Bucket(Integer.MIN_VALUE);
        tail = new Bucket(Integer.MAX_VALUE);
        head.next = tail;
        tail.prev = head;
    }

    /** Inserts a new key <Key> with value 1. Or increments an existing key by 1. */
    public void inc(String key) {
        if (values.containsKey(key)) {
            // find the key with bucket whose value=1
            int previousVal = values.get(key);
            int newVal = previousVal + 1;

            values.put(key, newVal);

            Bucket oldBucket = buckets.get(previousVal);
            Bucket newBucket = buckets.get(newVal);

            removeKeyFromBucket(oldBucket, key);

            if (newBucket == null) {
                // create the new bucket
                newBucket = new Bucket(newVal);
                // insert keys to new bucket
                insertKeyToBucket(newBucket, key);
                addNewBucketAfter(newBucket, oldBucket);

            }
            else {
                insertKeyToBucket(newBucket, key);
                if (oldBucket.keys.size() == 0) {
                    buckets.remove(previousVal);
                    deleteBucketBetween(oldBucket.prev, oldBucket.next);
                }
            }

        }
        else {
            // insert a new bucket whose value is 1
            values.put(key, 1);
            if (head.next.val != 1) {
                //insert a new one
                Bucket newBucket = new Bucket(1);
                newBucket.keys.add(key);
                Bucket next = head.next;
                newBucket.prev = head;
                head.next = newBucket;
                newBucket.next = next;
                next.prev = newBucket;

                buckets.put(1, newBucket);
            }
            else {
                buckets.get(1).keys.add(key);
            }
        }
    }

    /** Decrements an existing key by 1. If Key's value is 1, remove it from the data structure. */
    public void dec(String key) {
        if (values.containsKey(key)) {
            int value = values.get(key);
            int newVal = value - 1;

            if (value == 1) {
                // remove the key from map
                values.remove(key);
                // remove it from bucket whose value is 1
                Bucket bucket = buckets.get(1);
                bucket.keys.remove(key);

                // check if the bucket whose value is 1 has empty keyset, if so remove this bucket
                if (bucket.keys.size() == 0) {
                    deleteBucketBetween(bucket.prev, bucket.next);
                }
            }
            else {
                // decrease the key by 1
                values.put(key, newVal);

                Bucket oldBucket = buckets.get(value);
                Bucket newBucket = buckets.get(newVal);

                removeKeyFromBucket(oldBucket, key);

                if (newBucket == null) {
                    newBucket = new Bucket(newVal);
                    insertKeyToBucket(newBucket, key);
                    addNewBucketBefore(newBucket, oldBucket);
                }
                else {
                    insertKeyToBucket(newBucket, key);
                    if (oldBucket.keys.size() == 0) {
                        buckets.remove(value);
                        deleteBucketBetween(oldBucket.prev, oldBucket.next);
                    }
                }
            }
        }
    }

    /** Returns one of the keys with maximal value. */
    public String getMaxKey() {
        if (tail.prev.keys.size() == 0) return "";
        else return tail.prev.keys.iterator().next();
    }

    /** Returns one of the keys with Minimal value. */
    public String getMinKey() {
        if (head.next.keys.size() == 0) return "";
        else return head.next.keys.iterator().next();
    }

    public void removeKeyFromBucket(Bucket bucket, String key) {
        bucket.keys.remove(key);
    }

    public void insertKeyToBucket(Bucket bucket, String key) {
        bucket.keys.add(key);
    }

    public void addNewBucketAfter(Bucket newBucket, Bucket oldBucket) {
        if (oldBucket.keys.size() == 0) {
            // add new bucket between previous bucket and next bucket
            buckets.remove(oldBucket.val);
            addBucketBetween(oldBucket.prev, oldBucket.next, newBucket);
        }
        else {
            addBucketBetween(oldBucket, oldBucket.next, newBucket);
        }
        buckets.put(newBucket.val, newBucket);
    }

    public void addNewBucketBefore(Bucket newBucket, Bucket oldBucket) {
        if (oldBucket.keys.size() == 0) {
            // add new bucket between previous bucket and next bucket
            buckets.remove(oldBucket.val);
            addBucketBetween(oldBucket.prev, oldBucket.next, newBucket);
        }
        else {
            addBucketBetween(oldBucket.prev, oldBucket, newBucket);
        }
        buckets.put(newBucket.val, newBucket);
    }

    public void addBucketBetween(Bucket prev, Bucket next, Bucket newBucket) {
        if (prev != null) {
            prev.next = newBucket;
        }
        newBucket.prev = prev;
        if (next != null) {
            next.prev = newBucket;
        }
        newBucket.next = next;
    }

    public void deleteBucketBetween(Bucket prev, Bucket next) {
        prev.next = next;
        if (next != null) {
            next.prev = prev;
        }
    }

    public static void main(String[] args) {
        AllOneDataStructure ob = new AllOneDataStructure();
        ob.inc("hello");
        ob.inc("helo");
        ob.inc("hello");
        System.out.println(ob.getMinKey() + ", " + ob.getMaxKey());
        ob.inc("helo");
        System.out.println(ob.getMinKey() + ", " + ob.getMaxKey());
        ob.dec("helo");
        System.out.println(ob.getMinKey() + ", " + ob.getMaxKey());
    }
}
