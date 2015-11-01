package bloomberg;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by yingtan on 10/25/15.
 */
/*
* ：“给你一个完全由大写和小写字母组成的String，按照出现频率返回新的String，区分大小
写。举个例子，ABCAabcABa >
AAAaaBBbcC，AAA在最前面因为它的出现频率最高。如果频率一样的话我不在乎谁先谁
后，当然如果能按lexicographical order返回更好。
* */
public class ReRankStringByCountChs {

    public class Tuple {
        Character ch;
        int count;

        public Tuple(Character str, int count) {
            this.ch = str;
            this.count = count;
        }
    }
    // Solution 1: use self-defined class , rewrite comparable
    public String rankStr(String str) {
        if ( (str == null) || (str.length() == 0) ) return null;

        HashMap<Character, Integer> map = new HashMap<>();
        for (int i = 0 ; i < str.length(); i ++) {
            Character ch = str.charAt(i);
            if (map.containsKey(ch)) {
                int count = map.get(ch);
                map.put(ch, count + 1);
            } else {
                map.put(ch, 1);
            }
        }
        Tuple[] tuples = new Tuple[map.size()];
        int i = 0;
        for (Map.Entry<Character, Integer> entry: map.entrySet()) {
            char key = entry.getKey();
            int value = entry.getValue();

            Tuple tuple = new Tuple(key, value);
            tuples[i] = tuple;
            i ++;
        }
        TupleComparator comparator = new TupleComparator();
        Arrays.sort(tuples, comparator);

        String res  = "";
        for (i = 0 ; i < tuples.length; i ++) {
            int count = tuples[i].count;
            char ch = tuples[i].ch;

            for (int j = 0; j < count ; j ++) {
                res = res  + ch;
            }
        }
        return res;
    }

    public class TupleComparator implements Comparator<Tuple> {
        @Override
        public int compare(Tuple tuple_1, Tuple tuple_2) {
            int count_1 = tuple_1.count;
            int count_2 = tuple_2.count;

            if (count_1 < count_2) return 1;
            else if (count_1 > count_2) return -1;
            else {
                if (tuple_1.ch < tuple_2.ch) return 1;
                else if (tuple_1.ch > tuple_2.ch) return -1;
            }
            return 0;
        }
    }

    // return first char with highest frequency
    // no extra space, no sorting
    // 找到一个数组中，最先，他的frequency最大的char
    public char find1stHighFrequencyChar(char[] chs) {
        HashMap<Character, Integer> map = new HashMap<>();
        for (int i = 0 ; i < chs.length ;  i ++) {
            if (map.containsKey(chs[i])) {
                int count = map.get(chs[i]);
                map.put(chs[i], count + 1);
            }
            else {
                map.put(chs[i], 1);
            }
        }

        int maxCount = 0;
        Character maxCh = null;
        for (int i = 0 ;i < chs.length ; i ++) {
            char ch = chs[i];
            int count = map.get(ch);
            if (count > maxCount) {
                maxCount = count;
                maxCh = ch;
            }
        }
        return maxCh.charValue();
    }

    // 找到一个数组中，最先，只出现一次的char
    public char find1stOnceChar(char[] chs) {

        HashMap<Character, Integer> map = new HashMap<>();
        for (int i = 0 ; i < chs.length ;  i ++) {
            if (map.containsKey(chs[i])) {
                int count = map.get(chs[i]);
                map.put(chs[i], count + 1);
            }
            else {
                map.put(chs[i], 1);
            }
        }

        for (int i = 0 ;i < chs.length ; i ++) {
            char ch = chs[i];
            int count = map.get(ch);
            if (count == 1) return ch;
        }
        return 'e';
    }


    public static void main(String[] args) {
        ReRankStringByCountChs ob = new ReRankStringByCountChs();
        char[] chs = "ABCAabcABaaa".toCharArray();
        System.out.println(ob.rankStr("ABCAabcABaaa"));
        System.out.println(ob.find1stOnceChar(chs));
    }
}
