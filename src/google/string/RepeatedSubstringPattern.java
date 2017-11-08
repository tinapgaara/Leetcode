package google.string;

/**
 * Created by yingtan on 9/11/17.
 *
 * 459. Repeated Substring Pattern
 DescriptionHintsSubmissionsDiscussSolution
 Discuss Pick One
 Given a non-empty string check if it can be constructed by taking a substring of it and appending multiple copies of the substring together. You may assume the given string consists of lowercase English letters only and its length will not exceed 10000.

 Example 1:
 Input: "abab"

 Output: True

 Explanation: It's the substring "ab" twice.
 Example 2:
 Input: "aba"

 Output: False
 Example 3:
 Input: "abcabcabcabc"

 Output: True

 Explanation: It's the substring "abc" four times. (And the substring "abcabc" twice.)

 这道题给了我们一个字符串，问其是否能拆成n个重复的子串。那么既然能拆分成多个子串，那么每个子串的长度肯定不能大于原字符串长度的一半，那么我们可以从原字符串长度的一半遍历到1，如果当前长度能被总长度整除，说明可以分成若干个子字符串，我们将这些子字符串拼接起来看跟原字符串是否相等。 如果拆完了都不相等，返回false。
 */
public class RepeatedSubstringPattern {

    public boolean repeatedSubstringPattern(String s) {
        if (s == null || s.length() == 0) return false;

        for (int len = (s.length() / 2); len > 0 ; len --) {
            if (s.length() % len == 0) {
                // s can be divided into several lens
                int count = s.length() / len;
                String pattern = s.substring(0,len);
                StringBuilder builder = new StringBuilder();
                for (int i = 0 ; i < count; i ++) {
                    builder.append(pattern);
                }
                if (builder.toString().equals(s)) {
                    return true;
                }
            }
        }
        return false;
    }
}
