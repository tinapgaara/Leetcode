package array;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by yingtan on 9/23/15.
 */
public class WordDistance {

    String[] m_words;

    HashMap<String, List<Integer>> m_map;

    public WordDistance(String[] words) {
        m_words = words;
        m_map = new HashMap<String, List<Integer>>();
        for (int i = 0 ; i < words.length; i ++) {
            String word = words[i];
            if (m_map.containsKey(word)) {
                m_map.get(word).add(i);
            }
            else {
                List<Integer> list = new ArrayList<Integer>();
                list.add(i);
                m_map.put(word, list);
            }
        }
    }

    public int shortest(String word1, String word2) {

        int min = Integer.MAX_VALUE;
        List<Integer> list1 = m_map.get(word1);
        List<Integer> list2 = m_map.get(word2);
        if ((list1 == null) || (list2  == null) )
            return min;
        for (int i = 0 ; i < list1.size(); i ++) {
            for (int j = 0 ; j < list2.size(); j ++) {
                min = Math.min(min, Math.abs(list1.get(i) - list2.get(j)));
            }
        }
        return min;
    }

}
