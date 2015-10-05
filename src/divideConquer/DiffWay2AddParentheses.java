package divideConquer;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yingtan on 9/27/15.
 */
public class DiffWay2AddParentheses {

    public List<Integer> diffWaysToCompute(String input) {
        String frontStr = "";
        List<Integer> res = new ArrayList<Integer>();
        if ((input == null) || (input.length() == 0)) return res;

        int s = 0; // start position
        int j = 0;
        String value = "";
        boolean returnFlag = true;
        while (s < input.length()) { // find the first operator
            char ch = input.charAt(s);
            if ( (ch == '+') || (ch == '*') || (ch == '-') ) {
                j = s;
                returnFlag = false;
                break;
            }
            else {
                value = value + ch;
            }
            s ++;
        }
        if (returnFlag) { // only one operator
            res.add(Integer.parseInt(input));
            return res;
        }

        int i = 0;
        while ( (i < input.length()) && (j < input.length()) ) {
            frontStr = frontStr + value;
            char op = input.charAt(j);
            String endStr = input.substring(j+1, input.length());

            List<Integer> prevVal = diffWaysToCompute(frontStr);
            List<Integer> endVal = diffWaysToCompute(endStr);

            for (int p = 0 ; p < prevVal.size() ; p ++) {
                for (int q = 0 ; q < endVal.size(); q ++) {
                    if (op == '+')
                        res.add(prevVal.get(p) + endVal.get(q));
                    else if (op == '-')
                        res.add(prevVal.get(p) - endVal.get(q));
                    else
                        res.add(prevVal.get(p) * endVal.get(q));
                }
            }
            frontStr = frontStr  + op;
            i = j + 1;
            value = "";  // To find next operator
            returnFlag = true;
            while (i < input.length()) {
                char ch = input.charAt(i);
                if ( (ch == '+') || (ch == '*') || (ch == '-') ) {
                    j = i;
                    returnFlag = false;
                    break;
                }
                else
                    value = value + ch;
                i++;
            }
            if (returnFlag) { // if it is the last number, should return res directly
                return res;
            }
        }
        return res;
    }
}
