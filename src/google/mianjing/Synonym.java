package google.mianjing;

import java.util.*;

/**
 * Created by erict on 2017/11/9.
 */
public class Synonym {
    public class Pair {
        String m_word1, m_word2;
    }
    public boolean sameSemantic(List<String> sentence1, List<String> sentence2, List<Pair> synonyms) {
        if ((sentence1 == null) && (sentence2 == null)) return true;
        if ((sentence1 == null) && sentence2.isEmpty()) return true;  // 可以问面试官 这种情况是要返回true 还是 false
        if ((sentence2 == null) && sentence1.isEmpty()) return true;  // 可以问面试官 这种情况是要返回true 还是 false
        if (sentence1.size() != sentence2.size()) return false;

        int lastSynonymId = 0;
        Map<String, Integer> synonymDict = new HashMap<>();
        Map<Integer, Set<String>> reverseDict = new HashMap<>();
        for (Pair pair : synonyms) {
            Integer id1 = synonymDict.get(pair.m_word1);
            Integer id2 = synonymDict.get(pair.m_word2);
            if ((id1 == null) && (id2 == null)) {
                lastSynonymId++;
                synonymDict.put(pair.m_word1, lastSynonymId);
                synonymDict.put(pair.m_word2, lastSynonymId);
                fillReverseDict(reverseDict, lastSynonymId, pair.m_word1, pair.m_word2);
            }
            else if ((id1 == null) && (id2 != null)) {
                synonymDict.put(pair.m_word1, id2);
                fillReverseDict(reverseDict, id2, pair.m_word1, null);
            }
            else if ((id1 != null) && (id2 == null)) {
                synonymDict.put(pair.m_word2, id1);
                fillReverseDict(reverseDict, id1, pair.m_word2, null);
            }
            else if (id1 != id2) {
                Set<String> synonym1 = reverseDict.get(id1);
                Set<String> synonym2 = reverseDict.get(id2);
                assert (synonym1 != null) && (synonym2 != null);
                if (! synonym2.isEmpty()) {
                    for (String word : synonym2)
                        synonymDict.put(word, id1);
                    synonym1.addAll(synonym2);
                    synonym2.clear();
                    reverseDict.remove(id2);
                }
            }
        }
        for (int i = 0; i < sentence1.size(); i++) {
            Integer id1 = synonymDict.get(sentence1.get(i));
            Integer id2 = synonymDict.get(sentence2.get(i));
            if ((id1 == null) || (id2 == null)) return false;
            if (id1 != id2) return false;
        }
        return true;
    }

    private void fillReverseDict(Map<Integer, Set<String>> reverseDict, int synonymId, String word1, String word2) {
        Set<String> synonymSet = reverseDict.get(synonymId);
        if (synonymSet == null) {
            synonymSet = new HashSet<>();
            reverseDict.put(synonymId, synonymSet);
        }
        if (word1 != null)
            synonymSet.add(word1);
        if (word2 != null)
            synonymSet.add(word2);
    }
}
