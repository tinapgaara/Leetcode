package hashtable;

import java.util.*;

/**
 * Created by yingtan on 12/17/17.
 */
public class ComputeAllSubstingDecompositions {

    public List<Integer> findAllSubstrings(String s, List<String> words) {
        int len = 0;
        Map<String, Boolean> vis = new HashMap<>();
        for (String word : words) {
            len = len + word.length();
            vis.put(word, false);
        }
        List<Integer> indices = new ArrayList<>();
        for (int i = 0 ; i < s.length(); i ++) {
            if (recurMatch(s, i, words, vis)) {
                indices.add(i);
                System.out.println(i + "," + s.charAt(i));
            }
        }
        return indices;
    }
    public boolean recurMatch(String s, int indice, List<String> words, Map<String, Boolean> vis) {
        // base case
        boolean isCover = true;
        for (String word : words) {
            if (! vis.get(word)) {
                isCover = false;
            }
        }
        if (isCover && indice <= s.length()) return true;
        else if (indice >= s.length()) return false;
        for (String word : words) {
            if (! vis.get(word)) {
                int len = word.length();
                if (indice + len <= s.length()) {
                    String sWord = s.substring(indice, indice + len);
                    if (sWord.equals(word)) {
                        // matched
                        vis.put(word, true);
                        boolean isMatch = recurMatch(s, indice + len, words, vis);
                        // important !!! find all matched, no matter what, have to reset this back to false.
                        vis.put(word, false);
                        return isMatch;
                    }
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {
        List<String> words = new ArrayList<>();
        words.add("can");
        words.add("apl");
        words.add("ana");
        ComputeAllSubstingDecompositions ob = new ComputeAllSubstingDecompositions();
        ob.findAllSubstrings("amanaplanacanal", words);
    }
}
