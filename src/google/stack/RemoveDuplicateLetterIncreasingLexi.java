package google.stack;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * Created by yingtan on 11/4/17.
 */
public class RemoveDuplicateLetterIncreasingLexi {

    public String removeDuplicateLetters(String s) {
        if (s == null || s.length() == 0) {
            return "";
        }
        Map<Character, Integer> map = new HashMap<>();
        for (char ch : s.toCharArray()) {
            if (map.containsKey(ch)) {
                map.put(ch, map.get(ch) + 1);
            }
            else {
                map.put(ch, 1);
            }
        }
        Stack<Character> stack = new Stack<>();
        boolean[] inStack = new boolean[26];
        // two condition to put this to stack:
        // 1.  not existed in stack
        // 2. previous top ch < cur ch
        //   || previous top ch is the last letter of this type in string s
        for (char ch : s.toCharArray()) {
            // map stores how many chs we have not used to build result
            // we use this letter, so count --
            map.put(ch, map.get(ch) - 1);
            if (inStack[ch - 'a']) {
                continue;
            }
            while(! stack.isEmpty() && stack.peek() > ch && map.get(stack.peek()) > 0) {
                Character removedCh = stack.pop();
                inStack[removedCh - 'a'] = false;
            }
            stack.push(ch);
            inStack[ch - 'a'] = true;
        }
        StringBuilder sb = new StringBuilder();
        while(! stack.isEmpty()) {
            sb.insert(0, stack.pop());
        }
        return sb.toString();
    }


}
