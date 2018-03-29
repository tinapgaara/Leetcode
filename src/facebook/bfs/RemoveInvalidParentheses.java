package facebook.bfs;

import java.util.*;

/**
 * Created by yingtan on 5/13/17.
 *
 * 301. Remove Invalid Parentheses Add to List
 DescriptionHintsSubmissionsSolutions
 Total Accepted: 38290
 Total Submissions: 109638
 Difficulty: Hard
 Contributor: LeetCode
 Remove the minimum number of invalid parentheses in order to make the input string valid. Return all possible results.

 Note: The input string may contain letters other than the parentheses ( and ).

 Examples:
 "()())()" -> ["()()()", "(())()"]
 "(a)())()" -> ["(a)()()", "(a())()"]
 ")(" -> [""]


 */
public class RemoveInvalidParentheses {

    public List<String> removeInvalidParentheses_bfs(String s) {
        List<String> res = new ArrayList<String>();
        if (s == null || s.length() == 0) {
            res.add("");
            return res;
        }
        Queue<String> queue = new LinkedList<>();
        queue.offer(s);
        Set<String> vis = new HashSet<>();
        vis.add(s);
        while(! queue.isEmpty()) {
            String str = queue.poll();
            if (valid(str)) {
                if (res.size() == 0) {
                    res.add(str);
                    continue;
                }
                else {
                    String shortestStr = res.get(res.size() - 1);
                    if (str.length() == shortestStr.length()) {
                        res.add(str);
                        continue;
                    }
                    else if (str.length() > shortestStr.length()) {
                        return res;
                    }
                }
            }
            for (int i = 0 ; i < str.length(); i ++) {
                // remove the same ch, continue;
                if (i != 0 && str.charAt(i) == str.charAt(i-1)) continue;
                // we only try to remove left or right paren
                if (str.charAt(i) != '(' && str.charAt(i) != ')') continue;
                String newstr = str.substring(0, i) + str.substring(i + 1);
                if (! vis.contains(newstr)) {
                    vis.add(newstr);
                    queue.offer(newstr);
                }
            }
        }
        return res;
    }
    public boolean valid(String s) {
        int left = 0;
        int right = 0;
        for (char ch : s.toCharArray()) {
            if (ch == '(') {
                left ++;
            }
            else if (ch == ')') {
                right ++;
            }
            if (left < right) {
                return false;
            }
        }
        return left == right;
    }

    int max = 0;
    public List<String> removeInvalidParentheses_dfs(String s) {
        List<String> res= new ArrayList<>();
        if (s == null) return res;
        int leftcount = 0;
        int rightcount = 0;
        for (char ch : s.toCharArray()) {
            if (ch == '(') {
                leftcount ++;
            }
            else if (ch == ')') {
                rightcount ++;
            }
        }
        recurRemove(s, 0, leftcount, rightcount, res);
        return res;
    }
    public void recurRemove(String s, int index, int leftcount, int rightcount, List<String> res) {
        if (leftcount < 0 || rightcount < 0) return;
        // make sure the count is larger
        if (leftcount == rightcount) {
            if (valid(s)) {
                if (leftcount > max) {
                    res.clear();
                    max = leftcount;
                    res.add(s);
                } else if (leftcount == max) {
                    res.add(s);
                }
                return;
            }
        }
// important !!!! speed up, if this candidate is already smaller than the result, don't need to calculate it
        else if (Math.min(leftcount, rightcount) < max) return;
        for (int i = index; i < s.length(); i++) {
            if (s.charAt(i) != '(' && s.charAt(i) != ')') continue;
            // for case ())() ())
            if (i != index && s.charAt(i - 1) == s.charAt(i)) continue;
            ;
            String newstr = s.substring(0, i) + s.substring(i + 1);
            if (s.charAt(i) == '(') {
                // very important !!! to use i because we already processed the removing [0 ... i] chs
                recurRemove(newstr, i, leftcount - 1, rightcount, res);
            }
            if (s.charAt(i) == ')') {
                recurRemove(newstr, i, leftcount, rightcount - 1, res);
            }
        }
    }


    public static void main(String[] args) {
        RemoveInvalidParentheses ob = new RemoveInvalidParentheses();
        ob.removeInvalidParentheses_bfs("))");
    }
}
