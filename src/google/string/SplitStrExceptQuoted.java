package google.string;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yingtan on 11/5/15.
 */
/*
*
* Split a String at spaces except when quoted.
举例⼦子 "This is \"an example\" string." -> {"This", "is", "an
example", "string."}
注意被quote的不⽤用split
*/
public class SplitStrExceptQuoted {

    public List<String> split(String str) {
        int prev = 0;
        int cur = 0;
        List<String> res = new ArrayList<>();
        boolean isCurIsQuota = false;
        while (cur < str.length()) {
            char ch = str.charAt(cur);
            if (ch == ' ') {
                if (!isCurIsQuota) {
                    if (prev < cur) {
                        String substr = str.substring(prev, cur);
                        res.add(substr);
                    }
                    prev = cur + 1;
                    cur = prev;
                }
                else {
                    cur ++;
                }
            }
            else if (ch == '\\') {
                if (isCurIsQuota) {
                    if (prev + 1 < cur) {
                        String substr = str.substring(prev + 1, cur);
                        res.add(substr);
                    }
                    prev = cur + 2;
                    cur = prev;
                    isCurIsQuota = false;
                }
                else {
                    isCurIsQuota = true;
                    cur ++;
                    // Question ??? if do this ?
                    prev = cur;
                }
            }
            else {
                cur ++;
            }
        }

        if (prev < cur) {
            String substr = str.substring(prev, cur);
            res.add(substr);
        }
        return res;
    }

    public static void main(String[] args) {
        SplitStrExceptQuoted ob = new SplitStrExceptQuoted();
        System.out.println(ob.split("This is \\\"an example\\\" string."));
    }
}
