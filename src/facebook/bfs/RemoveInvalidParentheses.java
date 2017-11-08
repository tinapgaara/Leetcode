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

    public List<String> removeInvalidParentheses(String s) {
        List<String> res = new ArrayList<String>();
        if (s == null || s.length() == 0) {
            res.add("");
            return res;
        }

        Queue<String> queue = new LinkedList<String>();
        Map<String, Boolean> vis = new HashMap<String, Boolean>();

        queue.offer(s);

        while (! queue.isEmpty()) {
            String peek = queue.poll();

            // has visit this one
            if (vis.containsKey(peek)) continue;
            vis.put(peek, true);
            if (isValid(peek)) {
                // Important !!!!! make sure this is min steps
                if (res.isEmpty()) res.add(peek);
                else {
                    String last = res.get(res.size() - 1);
                    if (last.length() > peek.length()) {
                        // peek is not an optional solutions.
                        return res;
                    }
                    else {
                        res.add(peek);
                    }
                }
            }
            for (int i = 0 ; i < peek.length(); i ++) {
                String newStr = "";
                if (i > 0)
                {
                    String prev = peek.substring(0, i);
                    newStr = newStr + prev;

                }
                if (i+1 < peek.length()) {
                    String later = peek.substring(i+1, peek.length());
                    newStr = newStr + later;
                }
                queue.offer(newStr);
            }
        }
        return res;
    }

    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<Character>();
        // Iterate through string until empty
        for(int i = 0; i<s.length(); i++) {
            // Push any open parentheses onto stack
            if(s.charAt(i) == '(' || s.charAt(i) == '[' || s.charAt(i) == '{')
                stack.push(s.charAt(i));
                // Check stack for corresponding closing parentheses, false if not valid
            else if(s.charAt(i) == ')' && !stack.empty() && stack.peek() == '(')
                stack.pop();
            else if(s.charAt(i) == ']' && !stack.empty() && stack.peek() == '[')
                stack.pop();
            else if(s.charAt(i) == '}' && !stack.empty() && stack.peek() == '{')
                stack.pop();
            else if (! Character.isLetter(s.charAt(i)))
                return false;
        }
        // return true if no open parentheses left in stack
        return stack.empty();
    }
}
