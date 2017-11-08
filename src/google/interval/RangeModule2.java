package google.interval;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

/**
 * Created by erict on 2017/11/7.
 */

/**
 * TreeMap<Integer, Integer>, key is the starting index and
 * value is the ending index of the interval.
 * Maintainence is done to make sure no overlap intervals exist in the Map.
 */
public class RangeModule2 {

    class RangeModule {
        TreeMap<Integer, Integer> m_map;

        public RangeModule() {
            m_map = new TreeMap<>();
        }

        public void addRange(int left, int right) {
            if (right <= left) return;
            Integer start = m_map.floorKey(left); // 返回小于等于给定键的最大键；如果不存在这样的键，则返回 null
            Integer end = m_map.floorKey(right);
            if (start == null && end == null) {
                m_map.put(left, right);
            } else if (start != null && m_map.get(start) >= left) {
                m_map.put(start, Math.max(m_map.get(end), Math.max(m_map.get(start), right)));
            } else {
                m_map.put(left, Math.max(m_map.get(end), right));
            }
            // clean up intermediate intervals
            // left < key <= right
            Map<Integer, Integer> subMap = m_map.subMap(left, false, right, true);
            // 返回此映射的部分视图，其键的范围从 fromKey 到 toKey
            Set<Integer> set = new HashSet(subMap.keySet());
            m_map.keySet().removeAll(set);
        }

        public boolean queryRange(int left, int right) {
            Integer start = m_map.floorKey(left);
            if (start == null) return false;
            return m_map.get(start) >= right;
        }

        public void removeRange(int left, int right) {
            if (right <= left) return;
            Integer start = m_map.floorKey(left);
            Integer end = m_map.floorKey(right);
            if (end != null && m_map.get(end) > right) {
                m_map.put(right, m_map.get(end));
            }
            if (start != null && m_map.get(start) > left) {
                m_map.put(start, left);
            }
            // clean up intermediate intervals
            // left <= key < right
            Map<Integer, Integer> subMap = m_map.subMap(left, true, right, false);
            Set<Integer> set = new HashSet(subMap.keySet());
            m_map.keySet().removeAll(set);

        }
    }
}