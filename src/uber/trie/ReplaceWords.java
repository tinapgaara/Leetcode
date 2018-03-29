package uber.trie;

import java.util.List;

/**
 * Created by yingtan on 12/3/17.
 *
 * 648. Replace Words
 DescriptionHintsSubmissionsDiscussSolution
 Discuss Pick One
 In English, we have a concept called root, which can be followed by some other words to form another longer word -
 let's call this word successor. For example, the root an, followed by other, which can form another word another.

 Now, given a dictionary consisting of many roots and a sentence. You need to replace all the successor in the sentence
 with the root forming it. If a successor has many roots can form it, replace it with the root with the shortest length.

 You need to output the sentence after the replacement.

 Example 1:
 Input: dict = ["cat", "bat", "rat"]
 sentence = "the cattle was rattled by the battery"
 Output: "the cat was rat by the bat"
 */
public class ReplaceWords {

    public class Trie {
        Trie[] children;
        boolean isEnd;
        char ch;
        public Trie(char ch) {
            this.ch = ch;
            children = new Trie[26];
        }
    }
    public String replaceWords(List<String> dict, String sentence) {
        if (dict == null || dict.size() == 0 || sentence == null || sentence.length() == 0) return "";
        Trie root = new Trie('#');
        for (String word : dict) {
            Trie cur = root;
            for (char ch : word.toCharArray()) {
                if (cur.children[ch - 'a'] == null) {
                    cur.children[ch - 'a'] = new Trie(ch);
                }
                cur = cur.children[ch - 'a'];
            }
            cur.isEnd = true;
        }
        String[] parts = sentence.split(" ");
        StringBuilder res = new StringBuilder();
        for (String part : parts) {
            Trie cur = root;
            String curStr = "";
            for (char ch : part.toCharArray()) {
                if (cur.isEnd) break;
                else if (cur.children[ch - 'a'] == null) {
                    break;
                }
                else {
                    cur = cur.children[ch - 'a'];
                    curStr = curStr + cur.ch;
                }
            }
            if (cur.isEnd) {
                res.append(curStr);
                res.append(" ");
            }
            else {
                res.append(part);
                res.append(" ");
            }
        }
        res.deleteCharAt(res.length() - 1);
        return res.toString();
    }
}
