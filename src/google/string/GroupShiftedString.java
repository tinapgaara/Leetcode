package google.string;

import java.util.*;

/**
 * Created by yingtan on 8/5/17.
 */
public class GroupShiftedString {
    public List<List<String>> groupStrings(String[] strings) {
        // group shifted strings
        /*
        Pattern:
        abc  -> (0, 1, 2) : key : "0,1,2"
        aaa

        bcd
        bbb -> (0, 1, 2) : key : "0,1,2"

        xyz
        xxx -> (0, 1, 2) : key : "0,1,2"

        always compare the string's chs with the first ch in the string and form a key
        */
        Map<String, List<String>> map = new HashMap<String, List<String>>();
        for (String str : strings) {
            String key = "";
            char first = str.charAt(0);
            for (char ch : str.toCharArray()) {
                // ba - bb
                if (ch - first < 0) {
                    key = key + (ch - first + 26);
                }
                else {
                    key = key + (ch - first);
                }
                key = key + ",";
            }
            if (map.containsKey(key)) {
                List<String> list = map.get(key);
                list.add(str);
                map.put(key, list);
            }
            else {
                List<String> list = new ArrayList<>();
                list.add(str);
                map.put(key, list);
            }
        }

        for(String key:map.keySet())
            Collections.sort(map.get(key));

        return new ArrayList<List<String>>(map.values());

    }
}
