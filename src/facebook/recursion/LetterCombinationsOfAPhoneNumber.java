package facebook.recursion;
import java.util.*;
public class LetterCombinationsOfAPhoneNumber {
    public List<String> letterCombinations(String digits) {
        String[] LETTERS = {"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
        return bfs(LETTERS, digits);
    }
    public List<String> bfs(String[] LETTERS, String digits) {
        Queue<String> queue = new LinkedList<>();
        List<String> res = new ArrayList<>();
        if (digits == null || digits.length() == 0) return res;
        int first = digits.charAt(0) - '0';
        for (char ch : LETTERS[first].toCharArray()) {
            queue.offer(ch + "");
        }
        int depth = 1;
        while(! queue.isEmpty()) {
            if (depth == digits.length()) {
                while(! queue.isEmpty()) {
                    res.add(queue.poll());
                }
                return res;
            }
            int size = queue.size();
            int digit = digits.charAt(depth) - '0';
            for (int i = 0 ; i < size; i ++) {
                String cur = queue.poll();
                String str = LETTERS[digit];
                for (char ch : str.toCharArray()) {
                    String newstr = cur + ch;
                    queue.offer(newstr);
                }
            }
            depth ++;
        }
        return res;
    }
    public List<String> dfs(String[] LETTERS, String digits) {
        List<String> res = new ArrayList<>();
        if (digits == null || digits.length() == 0) return res;
        recurCombine(digits, 0, LETTERS, "", res);
        return res;
    }
    public void recurCombine(String digits, int index, String[] letters, String path, List<String> res) {
        if (index >= digits.length()) {
            res.add(path);
            return;
        }
        char ch = digits.charAt(index);
        int num = ch - '0';
        String letter = letters[num];
        for (char c : letter.toCharArray()) {
            recurCombine(digits, index + 1, letters, path + c, res);
        }
    }
}
