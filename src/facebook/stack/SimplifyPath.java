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
        if(path == null||path.length()==0)
            return path;

        Stack<String> stack = new Stack<String>();
        String[] list = path.split("/");

        for(int i=0; i<list.length; i++){
            if(list[i].equals(".")||list[i].length()==0)
                continue;
            else if(list[i].equals("..")) {
                if(!stack.isEmpty())
                    stack.pop();
            }
            else{
                stack.push(list[i]);
            }
        }

        StringBuilder res = new StringBuilder();

        // important !!!!
        // reverse orders in stack
        Stack<String> temp = new Stack<String>();
        while(!stack.isEmpty())  {
            temp.push(stack.pop());
        }

        while(!temp.isEmpty())
            res.append("/"+temp.pop());

        if(res.length()==0)
            res.append("/");

        return res.toString();
    }
}
