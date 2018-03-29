package facebook.dfs;

/**
 * Created by yingtan on 3/12/18.
 *
 * 784. Letter Case Permutation
 DescriptionHintsSubmissionsDiscussSolution
 Pick One
 Given a string S, we can transform every letter individually to be lowercase or uppercase to create another string.  Return a list of all possible strings we could create.

 Examples:
 Input: S = "a1b2"
 Output: ["a1b2", "a1B2", "A1b2", "A1B2"]

 Input: S = "3z4"
 Output: ["3z4", "3Z4"]

 Input: S = "12345"
 Output: ["12345"]
 */
import java.util.*;

public class LetterCasePermutation {
    public List<String> letterCasePermutation(String S) {
        // return bfs(S);
        List<String> res = new ArrayList<>();
        dfs(S, 0,  res);
        return res;
    }
    public void dfs(String S, int index, List<String> res) {
        System.out.println(S);
        if (index >= S.length()) {
            res.add(S);
            return;
        }
        char ch = S.charAt(index);
        if (ch >= '0' && ch <= '9') {
            // skip this ch
            dfs(S, index + 1, res);
            return;// important !!!!
        }
        char[] chs = S.toCharArray();
        chs[index] = Character.toLowerCase(ch);
        dfs(new String(chs), index + 1, res);

        chs[index] = Character.toUpperCase(ch);
        dfs(new String(chs), index + 1, res);

    }
    public List<String> bfs(String S) {
        List<String> res = new ArrayList<>();
        if (S == null) return res;
        Queue<String> queue = new LinkedList<>();
        queue.offer(S);
        Set<String> vis = new HashSet<>();
        while(! queue.isEmpty()) {
            String cur = queue.poll();
            if (vis.contains(cur)) continue;
            vis.add(cur);
            res.add(cur);
            for (int i = 0 ; i < cur.length(); i ++) {
                char ch = cur.charAt(i);
                if (ch >= 'a' && ch <= 'z') {
                    // this is a letter
                    char[] chs = cur.toCharArray();
                    chs[i] = (char)(ch + 'A' - 'a');
                    queue.offer(new String(chs));
                }
                if (ch >= 'A' && ch <= 'Z') {
                    // this is a letter
                    char[] chs = cur.toCharArray();
                    chs[i] = (char)(ch + 'a' - 'A');
                    queue.offer(new String(chs));
                }
            }
        }
        return res;
    }
    public static void main(String[] args) {
        LetterCasePermutation ob = new LetterCasePermutation();
        ob.letterCasePermutation("a1b2");
    }
}
