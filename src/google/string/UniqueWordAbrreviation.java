package google.string;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by yingtan on 7/24/17.
 */
public class UniqueWordAbrreviation {

    HashMap<String, Set<String>> map;

    public UniqueWordAbrreviation(String[] dictionary) {
        // d2r -> (deer, door)
        map = new HashMap<String, Set<String>>();

        for (String dic : dictionary) {
            String converted = convert(dic);
            if (map.containsKey(converted)) {
                map.get(converted).add(dic);
            }
            else {
                HashSet<String> set = new HashSet<String>();
                set.add(dic);
                map.put(converted, set);
            }
        }
    }

    public boolean isUnique(String word) {
        String converted = convert(word);
        // boundary case
        /*
        ["ValidWordAbbr","isUnique","isUnique","isUnique","isUnique"]
        [[["deer","door","cake","card"]],["dear"],["door"],["cart"],["cake"]]

         ["ValidWordAbbr","isUnique"]
         ["Hello", "Hello"]
        */
        if (map.containsKey(converted)) {
            // if only have one word can be translated to converted one, if that word is exactly same as compared word
            // this word is still unique [eg: Hello Hello]
            if (map.get(converted).contains(word) && map.get(converted).size() == 1) {
                return true;
            }
            else
                return false;
        }
        else return true;
    }

    public String convert(String word) {
        int len = word.length();
        if (len > 2) {
            return word.charAt(0) + "" + (len - 2) + word.charAt(len - 1);
        }
        return word;
    }
}
