package hashtable;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by yingtan on 4/6/18.
 */
public class TextQueries {
    public void textqueries(String[] sentences, String[] queries) {
        if (sentences == null || sentences.length == 0 || queries == null || queries.length == 0) return;
        for (String query : queries) {
            String[] qWords = query.split(" ");
            boolean appearInAnySentence = false;
            Map<String, Integer> qMap = new HashMap<>();
            for (String word : qWords) {
                if (qMap.containsKey(word)) {
                    qMap.put(word, qMap.get(word) + 1);
                } else {
                    qMap.put(word, 1);
                }
            }
            StringBuilder st = new StringBuilder();
            for (int i = 0; i < sentences.length; i++) {
                String sentence = sentences[i];
                String[] sWords = sentence.split(" ");
                Map<String, Integer> map = new HashMap<>();
                for (String word : sWords) {
                    if (map.containsKey(word)) {
                        map.put(word, map.get(word) + 1);
                    } else {
                        map.put(word, 1);
                    }
                }
                int min = Integer.MAX_VALUE;
                boolean localAppear = true;
                for (String key : qMap.keySet()) {
                    if (map.containsKey(key)) {
                        min = Math.min(min, map.get(key) / qMap.get(key));
                    } else {
                        localAppear = false;
                        break;
                    }
                }
                if (localAppear) {
                    appearInAnySentence = true;// there is sentence contains this query.
                    for (int k = 0; k < min; k++) {
                        st.append(i + " ");
                    }
                }
            }
            if (! appearInAnySentence) {
                st.append("-1 ");
            }
            System.out.println(st.toString());
        }
    }
    public static void main(String[] args) {
        TextQueries ob = new TextQueries();
        String[] queries = new String[]{"s c m", "bob bob slice"};
        String[] sentences = new String[]{"B", "bob bob bob bob slice", "bob slice bob slice bob bob"};
        ob.textqueries(sentences, queries);
    }
}
