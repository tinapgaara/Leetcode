package google.string;

/**
 * Created by yingtan on 7/20/17.
 * 482. License Key Formatting
 * Now you are given a string S, which represents a software license key which we would like to format. The string S is composed of alphanumerical characters and dashes. The dashes split the alphanumerical characters within the string into groups. (i.e. if there are M dashes, the string is split into M+1 groups). The dashes in the given string are possibly misplaced.

 We want each group of characters to be of length K (except for possibly the first group, which could be shorter, but still must contain at least one character). To satisfy this requirement, we will reinsert dashes. Additionally, all the lower case letters in the string must be converted to upper case.

 So, you are given a non-empty string S, representing a license key to format, and an integer K. And you need to return the license key formatted according to the description above.

 Example 1:
 Input: S = "2-4A0r7-4k", K = 4

 Output: "24A0-R74K"

 Explanation: The string S has been split into two parts, each part has 4 characters.
 Example 2:
 Input: S = "2-4A0r7-4k", K = 3

 Output: "24-A0R-74K"

 Explanation: The string S has been split into three parts, each part has 3 characters except the first part as it could be shorter as said above.
 */
public class LicenseKeyFormatting {

    public String licenseKeyFormatting(String S, int K) {
        String res = "";
        if (S == null || S.length() == 0) return res;
        StringBuilder sb = new StringBuilder();

        // 从后往前扫,碰到不为"-"的就检查是否当前构成的字符串是k的倍数,
        // 如果是,则append "-" 再append当前字符 ; 如果不是,则append当前字符
        for (int i = S.length() - 1; i >= 0; i --) {
            if (S.charAt(i) != '-') {
                // must be sb.length() % (k+1) == k instead of sb.length() % k
                // when sb.length() == 0, 0 % k always = 0
                if (sb.length() % (K+1) == K) {
                    sb.append("-");
                }
                sb.append(S.charAt(i));
            }
        }
        // finally reverse
        return sb.reverse().toString().toUpperCase();
    }
}
