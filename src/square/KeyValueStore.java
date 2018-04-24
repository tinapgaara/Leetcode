package square;

import java.util.HashMap;

/**
 * Created by yingtan on 10/16/17.
 *
 * 题目很straightforward : design key-value store   I am using Java and can use Map data structure.
 * Design APIs like put(K key, V value), get(K key), remove(), isEmpty(), size()
 * After that writing some test-cases and just print out the result
 * The follow-up is : realize another APIToAddPoint : V getTimerValue(K key, Long time), assistant function :
 * System.nanoTimer();
 * Basically, whenever you do put(K,V) function,
 * record the time, and when calling the getTimerValue() function to see if at a specfic time,
 * there is a value put in the map..
 put(1,1)    put(2,2)
 20'            30'
 getTimerValue(1, 10) --> null
 getTimerValue(1, 20) --> 20;

 */
public class KeyValueStore {

    private HashMap<Key, Integer> map;
    public KeyValueStore() {
        map = new HashMap<>();
    }

    private void put(int key, int value, long timestamp) {
        Key newKey = new Key(key, timestamp);
        map.put(newKey, value);
    }

    private Integer getTimerValue(int key, long timestamp) {
        Key k = new Key(key, timestamp);
        if (map.containsKey(k)) {
            return map.get(k);
        }
        else
            return null;
    }

    private class Key {
        int key;
        long timestamp;

        public Key(int key, long timestamp) {
            this.key = key;
            this.timestamp = timestamp;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj == this) return true;
            if (! (obj instanceof Key)) return false;
            Key key = (Key)obj;

            if (key.key == this.key && key.timestamp == this.timestamp) return true;
            else return false;
        }

        @Override
        public int hashCode() {
            return (key + "_" + timestamp).hashCode();
        }
    }

    public static void main(String[] args) {
        KeyValueStore ob = new KeyValueStore();
        ob.put(1, 1, 20);
        ob.put(2, 2, 30);
        System.out.println(ob.getTimerValue(1, 10));
    }

}
