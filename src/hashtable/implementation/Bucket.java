package hashtable.implementation;

/**
 * Created by yingtan on 3/16/18.
 */
import java.util.*;
public class Bucket {
    public Bucket next;
    public Bucket prev;
    private String key;
    private int value;
    public Bucket(String key, int val) {
        this.key = key;
        this.value = val;
    }
    public String getKey() {
        return key;
    }
    public int getValue() {
        return value;
    }
    public void setValue(int val) {
        this.value = val;
    }

}
