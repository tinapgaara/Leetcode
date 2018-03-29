package facebook.topoSort;

import java.nio.charset.CharacterCodingException;
import java.util.*;

/**
 * Created by yingtan on 5/28/17.
 */
public class AlienDictionary {

    public String alienOrder(String[] words) {
        if (words == null || words.length == 0) {
            return "";
        }
        Map<Character, Set<Character>> graph = new HashMap<>();
        int[] color = new int[26];
        Arrays.fill(color, -1);
        for (int i = 0 ; i < words.length; i ++) {
            String wordi = words[i];
            // very important !!!!!
            for (char ch : wordi.toCharArray()) color[ch - 'a'] = 0;

            for (int j = i + 1; j < words.length; j ++) {
                String wordj = words[j];
                // construct graph, fill adj
                for (int k = 0 ; k < Math.min(wordi.length(), wordj.length()); k ++) {
                    char chi = wordi.charAt(k);
                    char chj = wordj.charAt(k);
                    if (chi != chj) {
                        if (graph.containsKey(chi)) {
                            graph.get(chi).add(chj);
                        }
                        else {
                            HashSet<Character> set = new HashSet<>();
                            set.add(chj);
                            graph.put(chi, set);
                        }
                        break;
                    }
                }
            }
        }
        LinkedList<Character> res = new LinkedList<>();
        for (int i = 0 ; i < 26; i ++) {
            if (color[i] == 0) {
                // not visited
                if(!dfs((char)(i + 'a'), graph, color, res)) {
                    // has cycle
                    return "";
                }
            }
        }
        // find out independent node, combine
        String s  = "";
        for (Character ch : res) {
            s = s + ch;
        }
        return s;
    }
    public boolean dfs(Character cur, Map<Character, Set<Character>> graph, int[] color, LinkedList<Character> res) {
        color[cur - 'a'] = 1;// in visiting
        if (graph.containsKey(cur)) {
            Set<Character> neighbors = graph.get(cur);
            for (Character neighbor : neighbors) {
                if (color[neighbor - 'a'] == 0) {
                    if (!dfs(neighbor, graph, color, res)) {
                        return false;
                    }
                }
                else if (color[neighbor - 'a'] == 1) {
                    // cycle
                    return false;
                }
            }
        }
        res.addFirst(cur);
        color[cur - 'a'] = 2;// finished visited
        return true;
    }

    public static void main(String[] args)  {
        AlienDictionary ob = new AlienDictionary();
        String[] words = new String[]{"z","x"};
        ob.alienOrder(words);
    }
}
