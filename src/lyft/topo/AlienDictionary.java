package lyft.topo;
import java.util.*;
public class AlienDictionary {
    public String alienOrder(String[] words) {
        if (words == null || words.length == 0) return "";
        Map<Character, Set<Character>> graph = new HashMap<>();
        int[] color = new int[26];
        Arrays.fill(color, -1);
        // create the gragh
        for (int i = 0 ; i < words.length; i ++) {
            String word1 = words[i];
            for (char ch: words[i].toCharArray()) {
                color[ch - 'a'] = 0;
            }
            for (int j = i + 1; j < words.length ; j ++) {
                String word2 = words[j];
                for (int k = 0; k < Math.min(word1.length(), word2.length()); k ++) {
                    char ch1 = word1.charAt(k);
                    char ch2 = word2.charAt(k);
                    // build edge from ch1 -> ch2
                    if (ch1 != ch2) { // important !!!
                        if (! graph.containsKey(ch1)) {
                            Set<Character> set = new HashSet<>();
                            graph.put(ch1, set);
                        }
                        graph.get(ch1).add(ch2);
                        break;
                    }
                }
            }
        }
        StringBuilder st = new StringBuilder();
        for (int nodeId = 0 ; nodeId < 26; nodeId ++) {
            if (color[nodeId] == 0) {
                if (! dfs(graph, nodeId, color, st)) {
                    return "";
                }
            }
        }
        return st.reverse().toString();
    }
    public boolean dfs(Map<Character, Set<Character>> map, int nodeId, int[] color, StringBuilder st) {
        color[nodeId] = 1;
        char ch = (char)(nodeId + 'a');
        if (map.containsKey(ch)) {
            for (Character neighbor : map.get(ch)) {
                int neighborId = neighbor - 'a';
                if (color[neighborId] == 0) {
                    if (! dfs(map, neighborId, color, st)) {
                        return false;
                    }
                }
                else if (color[neighborId] == 1) {
                    return false;
                }
            }
        }
        color[nodeId] = 2;
        st.append(ch);
        return true;
    }
    public static void main(String[] args) {
        AlienDictionary ob = new AlienDictionary();
        String[] words = {"ab", "adc"};
        ob.alienOrder(words);
    }
}
