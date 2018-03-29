package facebook.stack;

import java.util.Stack;

/**
 * Created by yingtan on 5/21/17.
 *
 *
 * 71. Simplify Path Add to List
 DescriptionHintsSubmissionsSolutions
 Total Accepted: 83396
 Total Submissions: 336514
 Difficulty: Medium
 Contributor: LeetCode
 Given an absolute path for a file (Unix-style), simplify it.

 For example,
 path = "/home/", => "/home"
 path = "/a/./b/../../c/", => "/c"
 click to show corner cases.

 Corner Cases:
 Did you consider the case where path = "/../"?
 In this case, you should return "/".
 Another corner case is the path might contain multiple slashes '/' together, such as "/home//foo/".
 In this case, you should ignore redundant slashes and return "/home/foo".


 */
public class SimplifyPath {
    public String simplifyPath(String path) {
        if (path == null || path.length() == 0) return path;
        Stack<String> stack = new Stack<>();
        String[] parts = path.split("/");
        for (String part : parts) {
            if (part.length() == 0) continue; // /home//foo/
            if (part.equals(".")) continue;
            if (part.equals("..")) {
                // important !!!! should firstly check is stack.isEmpty()
                if (! stack.isEmpty()) stack.pop();
            }
            else {
                stack.push(part);
            }
        }
        //  very important !!!!!
        String res = "";
        if (stack.isEmpty()) {
            return "/";
        }
        else {
            while(! stack.isEmpty()) {
                res = "/" + stack.pop() + res;
            }
        }
        /*
        if (! stack.isEmpty()) {
            res = stack.pop(); // the first part
        }
        while(! stack.isEmpty()) {
            res = stack.pop() + "/" + res;
        }
        res = "/" + res; // for  case  "/"
        */
        return res;
    }
}
