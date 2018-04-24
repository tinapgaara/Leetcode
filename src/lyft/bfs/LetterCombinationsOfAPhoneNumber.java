package lyft.bfs;

import lyft.bfs.multithread.LetterCombineThread;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class LetterCombinationsOfAPhoneNumber {
    public List<String> letterCombinations(String digits) {
        String[] LETTERS = {"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
        return dfs(LETTERS, digits);
        //return bfs(LETTERS, digits);

    }
    public List<String> dfs(String[] letters, String digits) {
        List<String> res = new ArrayList<>();
        if (digits == null || digits.length() == 0) return res;
        recurCombine(digits, 0, letters, "", res);
        return res;
    }
    public void recurCombine(String digits, int index, String[] letters, String cur, List<String> res) {
        // base case
        if (index >= digits.length()) {
            res.add(cur);
            return;
        }
        int digit = digits.charAt(index) - '0';
        String str = letters[digit];
        for (char ch : str.toCharArray()) {
            recurCombine(digits, index + 1, letters, cur + ch, res);
        }
    }
    public List<String> bfs(String[] letters, String digits) {
        List<String> res = new ArrayList<>();
        if (digits == null || digits.length() == 0) return res;
        int index = digits.charAt(0) - '0';
        Queue<String> queue = new LinkedList<>();
        for (char ch : letters[index].toCharArray()) {
            queue.offer(ch + "");
        }
        int level = 1;
        while(! queue.isEmpty()) {
            int size = queue.size();
            if (level == digits.length()) {
                while(! queue.isEmpty()) {
                    res.add(queue.poll());
                }
                return res;
            }
            int curIndex = digits.charAt(level) - '0';

            String curStr = letters[curIndex];
            for (int i = 0 ; i < size; i ++) {
                String str = queue.poll();
                for (char ch : curStr.toCharArray()) {
                    queue.offer(str + ch);
                }
            }
            level ++;
        }
        return res;
    }
    public void letterCombinations_multithread(String digits) {
        String[] LETTERS = {"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
        bfs_multithread(LETTERS, digits);
    }
    // follow up:  how to do this parallely?
    // have many queues running concurrently
    public void bfs_multithread(String[] letters, String digits) {
        List<String> res = new ArrayList<>();
        if (digits == null || digits.length() == 0) return;
        int index = digits.charAt(0) - '0';
        BlockingQueue<String> queue = new LinkedBlockingQueue<String>(100);
        for (char ch : letters[index].toCharArray()) {
            queue.offer(ch + "");
        }
        int threadNum = 10;
        for (int i = 0 ; i < threadNum; i ++) {
            LetterCombineThread thread = new LetterCombineThread(queue, letters, digits);
            thread.start();
        }
    }
    public static void main(String[] args) {
        LetterCombinationsOfAPhoneNumber ob = new LetterCombinationsOfAPhoneNumber();
        ob.letterCombinations_multithread("23");
    }
}
