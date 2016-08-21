package vmware;

import java.util.HashMap;

/**
 * Created by yingtan on 10/28/15.
 */
public class FirstUniqueInArray {

    // Solution 1: use hashmap
    public int findFirstUniqueIntInUnsortedArr(int[] num) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0 ; i < num.length ; i ++) {
            if (map.containsKey(num[i])) {
                int count = map.get(num[i]);
                map.put(num[i], count + 1);
            }
            else {
                map.put(num[i], 1);
            }
        }

        for (int i = 0 ; i < num.length ; i ++) {
            if (map.containsKey(num[i])) {
                int count = map.get(num[i]);
                if (count == 1) return num[i];
            }
        }
        return 0;
    }

    // Solution 2: how to do when requiring scanning array just once ?
    // scan string once
    // scan formed count array once
    public char findFirstUniqueChar(char[] num) {
        int[] index = new int[26];
        for (int i = 0 ; i < num.length; i ++) {
            char ch = num[i];
            if (index[ch - 'a'] == 0) { // has never appear
                index[ch - 'a'] = i + 1;
            } else if (index[ch - 'a'] > 0) { // appear once
                index[ch - 'a'] = -1;
            } else if (index[ch - 'a'] < 0) { // appear more times
                continue;
            }
        }

        int min = Integer.MAX_VALUE;
        char minChar = ' ';
        for (int i = 0 ; i < index.length; i ++) { // o(cst) : 26
            if (index[i] <= 0) continue;
            else {
                if (index[i] < min) {
                    min = index[i];
                    minChar = (char)(i + 97);
                }
            }
        }
        return minChar;
    }

    public static void main(String[] args) {
        FirstUniqueInArray ob = new FirstUniqueInArray();
        int[] num = new int[]{0,1,1,3,3,6,2,2,4,7};
        System.out.println(ob.findFirstUniqueIntInUnsortedArr(num));

        char[] chs = new char[]{'d','f','a','b','c','f','d'};
        System.out.println(ob.findFirstUniqueChar(chs));
    }
}
