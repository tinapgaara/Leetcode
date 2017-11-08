package google.array;

/**
 * Created by yingtan on 5/4/17.
 *
 * 38. Count and Say
 *
 * The count-and-say sequence is the sequence of integers beginning as follows:
 1, 11, 21, 1211, 111221, ...

 1 is read off as "one 1" or 11.
 11 is read off as "two 1s" or 21.
 21 is read off as "one 2, then one 1" or 1211.
 Given an integer n, generate the nth sequence.

 Note: The sequence of integers will be represented as a string.

 */
public class CountAndSay {

    public String countAndSay(int n) {
        if (n == 1) return "1";

        String prevStr = "1";
        for(int i = 1 ; i < n ; i++) {

            char[] chs = prevStr.toCharArray();
            int count = 1;
            String curStr = "";
            char prev = '*'; //  sth like NULL
            for (int j = 0 ; j < chs.length ; j ++) {
                char cur = chs[j];
                if (cur == prev) {
                    count ++;
                }
                else if (prev != '*') { // if this is not the first ch in arr
                    curStr = curStr + count + "" + prev ;
                    count = 1;
                }
                prev = cur;
            }
            if (count > 0) {
                curStr = curStr + count + "" + chs[chs.length - 1];
            }

            prevStr = curStr;
        }
        return prevStr;
    }
}
