package anagram;

import java.util.*;

/**
 * Created by yingtan on 9/26/15.
 */
public class GroupShiftString {

    // Solution 2: rotation each string to make it starts with 'a'
    public List<List<String>> groupStrings(String[] strings) {

        List<List<String>> res = new ArrayList<List<String>>();
        if ( (strings == null) || (strings.length == 0) ) return res;
        Arrays.sort(strings);
        Map<String, List<String>> map = new HashMap<String, List<String>>();

        for (int i = 0 ; i < strings.length ; i ++) {
            String str = shiftStr(strings[i]);
            if (map.containsKey(str)) {
                map.get(str).add(strings[i]);
            }
            else {
                List<String> newGroup = new ArrayList<String>();
                newGroup.add(strings[i]);
                map.put(str, newGroup);
            }
        }

        for (Map.Entry<String, List<String>> entry: map.entrySet()) {
            res.add(entry.getValue());
        }
        return res;
    }

    public String shiftStr(String str) {
        if (str.length() == 0) return str;
        int diff = str.charAt(0) - 'a';
        char[] chs = new char[str.length()];
        chs[0] = 'a';

        for (int i = 1 ; i < str.length() ; i ++) {
            char ch = str.charAt(i);
            if ((ch - diff) < 'a') {
                int highDiff = diff - (ch - 'a');
                chs[i] = (char)('z'- (highDiff - 1));
            }
            else
                chs[i] = (char)(ch - diff);
        }

        String res = new String(chs);
        return res;
    }

    // Solution 1: do not using map to store result, using arraylist
    public List<List<String>> groupStrings_2(String[] strings) {

        List<List<String>> res = new ArrayList<List<String>>();
        if ( (strings == null) || (strings.length == 0) ) return res;

        Arrays.sort(strings);

        List<String> firstGroup = new ArrayList<String>();
        firstGroup.add(strings[0]);
        res.add(firstGroup);

        for (int i = 1; i < strings.length; i ++) {
            String str = strings[i];
            boolean ifCreateNewGroup = true;
            for (List<String> group : res) {
                String lastOne = group.get(group.size()-1);
                if (isSameGroup(str, lastOne)) {
                    group.add(str);
                    ifCreateNewGroup = false;
                    break;
                }
            }
            if (ifCreateNewGroup) {
                List<String> newGroup = new ArrayList<String>();
                newGroup.add(str);
                res.add(newGroup);
            }
        }
        return res;
    }

    public boolean isSameGroup(String str, String anotherStr) {
        if (str.length() != anotherStr.length()) return false;
        int size = str.length();
        if (size == 0) return true;
        char ch_1 = anotherStr.charAt(0);
        char ch_2 = str.charAt(0);
        int diff = 0;

        if (ch_1 > ch_2)
            diff = ('z' - ch_1) + (ch_2 - 'a') + 1;
        else
            diff = (ch_2 - ch_1);

        for (int i = 1; i < size; i++) {
            ch_1 = anotherStr.charAt(i);
            ch_2 = str.charAt(i);
            int newDiff = 0;
            if (ch_1 > ch_2)
                newDiff = ('z' - ch_1) + (ch_2 - 'a') + 1;
            else
                newDiff = ch_2 - ch_1;
            if (newDiff != diff) {
                return false;
            }
            else
                diff = newDiff;
        }
        return true;
    }

    public static void main(String[] args) {
        GroupShiftString ob = new GroupShiftString();
        System.out.println(ob.shiftStr("lpt"));
    }
}
