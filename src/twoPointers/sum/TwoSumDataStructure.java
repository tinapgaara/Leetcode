package twoPointers.sum;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by yingtan on 9/25/15.
 */
public class TwoSumDataStructure {

    HashMap<Integer, Integer> m_map;
    int m_key;

    public TwoSumDataStructure() {
        m_map = new HashMap<Integer, Integer>();
        m_key = 0;
    }

    // Add the number to an internal data structure.
    public void add(int number) {
        if (m_map.containsKey(number)) {
            int count = m_map.get(number) + 1;
            m_map.put(number, count);
        }
        else
            m_map.put(number,  1);
    }

    // Find if there exists any pair of numbers which twoPointers.sum is equal to the value.
    public boolean find(int value) {
        for (Map.Entry<Integer, Integer> entry : m_map.entrySet()) {
            int count = entry.getValue().intValue();
            int num = entry.getKey().intValue();
            int anotherNum = value - num;
            if (anotherNum == num) {
                if (count >= 2)
                    return true;
            }
            else {
                if (m_map.containsKey(anotherNum))
                    return true;
            }

        }
        return false;
    }

// Your TwoSum object will be instantiated and called as such:
// TwoSum TwoSum = new TwoSum();
// TwoSum.add(number);
// TwoSum.find(value);
}
