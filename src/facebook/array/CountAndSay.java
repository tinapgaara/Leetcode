package facebook.array;

/**
 * Created by yingtan on 12/21/17.
 *
 * 38. Count and Say
 DescriptionHintsSubmissionsDiscussSolution
 DiscussPick One
 The count-and-say sequence is the sequence of integers with the first five terms as following:

 1.     1
 2.     11
 3.     21
 4.     1211
 5.     111221
 1 is read off as "one 1" or 11.
 11 is read off as "two 1s" or 21.
 21 is read off as "one 2, then one 1" or 1211.
 Given an integer n, generate the nth term of the count-and-say sequence.

 Note: Each term of the sequence of integers will be represented as a string.

 Example 1:

 Input: 1
 Output: "1"
 Example 2:

 Input: 4
 Output: "1211"
 */
public class CountAndSay {

    public String countAndSay(int n) {
        String str = "1";
        for (int i = 2; i <= n; i ++) {
            int prev = -1;
            String newstr = "";
            for (int j = 0 ; j < str.length(); j ++) {
                if (prev != -1) {
                    if (str.charAt(j) != str.charAt(prev)) {
                        newstr = newstr + (j - prev) + "" + str.charAt(prev);
                        prev = j;
                    }
                }
                else {
                    prev = 0;
                }
            }
            newstr = newstr + (str.length() - prev) + "" + str.charAt(prev);
            str = newstr;
        }
        return str;
    }
}
