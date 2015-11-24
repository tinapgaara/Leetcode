package google.parenthesis;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by yingtan on 11/17/15.
 */
public class InvalidParenthesesClearer {

    public static void main(String[] args) {

        InvalidParenthesesClearer solution = new InvalidParenthesesClearer();

        String str;
        List<String> result; 

		/*
		str = "()())()";
		//*/
        //*
        str = "(a)())()";
        //*/
		/*
		str = ")(";
		//*/


        result = solution.removeInvalidParentheses(str);
        if (result == null) {
            System.out.println("result = [NULL]");
        }
        else {
            for (String res : result) {
                System.out.println("result = [" + res + "]");
            }
        }
    }

    public List<String> removeInvalidParentheses(String str) {
        int count = 0, openN = 0, closeN = 0;

        // calculate the total numbers of opening and closing parentheses
        // that need to be removed in the final solution
        for (char ch : str.toCharArray()) {
            if (ch == '(') {
                count++;
            }
            else if (ch == ')') {
                if (count == 0) closeN++;
                else count--;
            }
        }
        openN = count; // left parens which are left
        count = 0;

        if (openN == 0 && closeN == 0) return Arrays.asList(str);

        List<String> result = new ArrayList<>();
        StringBuilder sb = new StringBuilder();

        dfs(str.toCharArray(), 0, count, openN, closeN, result, sb);

        return result;
    }

    // StringBuilder sb: substring of char[] s before pth location, with some removal of ( or )
    private void dfs(char[] s, int p, int count, int openN, int closeN, List<String> result, StringBuilder sb) { // closetN: right parens which are left
        if (count < 0) return; // the parentheses is invalid

        if (p == s.length) {
            if (openN == 0 && closeN == 0) { // the minimum number of invalid parentheses have been removed
                result.add(sb.toString());
            }
            return;
        }

        if (s[p] != '(' && s[p] != ')') {
            sb.append(s[p]); // when scan to p, the valid string is stored in stringbuffer

            // System.out.println("AAA-000 p = [" + p + "], sb = [" + sb.toString() + "]");
            dfs(s, p + 1, count, openN, closeN, result, sb);
            // System.out.println("AAA-111 p = [" + p + "], sb = [" + sb.toString() + "]");
            sb.deleteCharAt(sb.length() - 1);
            // System.out.println("AAA-222 p = [" + p + "], sb = [" + sb.toString() + "]");
        }
        else if (s[p] == '(') {
            int i = 1;
            while (p + i < s.length && s[p + i] == '(') i++; // scan until to a not valid ( parenthesis. use while loop to avoid duplicate result in DFS, instead of using HashSet
            sb.append(s, p, i);
            // System.out.println("BBB-000 p = [" + p + "], sb = [" + sb.toString() + "]");
            dfs(s, p + i, count + i, openN, closeN, result, sb); // has got count + i  left parenthesis
            // System.out.println("BBB-111 p = [" + p + "], sb = [" + sb.toString() + "]");
            sb.delete(sb.length() - i, sb.length()); // recover
            // System.out.println("BBB-222 p = [" + p + "], sb = [" + sb.toString() + "]");

            if (openN > 0) { // (((
                // remove the current opening parenthesis
                // count: not plus one, because不期待有右括号跟我匹配,直接扔掉左括号
                dfs(s, p + 1, count, openN - 1, closeN, result, sb);
            }
        }
        else {
            int i = 1;
            while (p + i < s.length && s[p + i] == ')') i++; // use while loop to avoid duplicate result in DFS, instead of using HashSet
            sb.append(s, p, i); // append all consecutive right parens
            // System.out.println("CCC-000 p = [" + p + "], sb = [" + sb.toString() + "]");
            dfs(s, p + i, count - i, openN, closeN, result, sb);
            // System.out.println("CCC-111 p = [" + p + "], sb = [" + sb.toString() + "]");
            sb.delete(sb.length() - i, sb.length()); // recover
            // System.out.println("CCC-222 p = [" + p + "], sb = [" + sb.toString() + "]");

            if (closeN > 0) {
                // remove the current closing parenthesis
                dfs(s, p + 1, count, openN, closeN - 1, result, sb);
            }
        }
    }
}
