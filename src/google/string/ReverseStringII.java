package google.string;

/**
 * Created by yingtan on 9/17/17.
 *
 * 541. Reverse String II
 DescriptionHintsSubmissionsDiscussSolution
 Discuss Pick One
 Given a string and an integer k, you need to reverse the first k characters for every 2k characters counting from the start of the string. If there are less than k characters left, reverse all of them. If there are less than 2k but greater than or equal to k characters, then reverse the first k characters and left the other as original.
 Example:
 Input: s = "abcdefg", k = 2
 Output: "bacdfeg"

 就是每2k个字符来遍历原字符串s，然后进行翻转，翻转的结尾位置是取i+k和末尾位置之间的较小值，感觉很叼
 */

public class ReverseStringII {

    public String reverseStr(String s, int k) {
        char[] chs = s.toCharArray();
        for (int i = 0; i < s.length(); i += 2 * k) {
            // reverse string starting from index i and min(i+k, s.len)
            reverse(i, Math.min(i + k -1, s.length() -1), chs);
        }
        return new String(chs);
    }

    public void reverse(int start, int end, char[] chs) {
        //System.out.println(chs[start] + "," + end);
        while (start < end) {
            char tmp = chs[start];
            chs[start] = chs[end];
            chs[end] = tmp;

            start ++;
            end --;
        }
    }

    public String reverseStr_2(String s, int k) {
        if (s == null || s.length() == 0) {
            return s;
        }

        StringBuilder str = new StringBuilder();
        int len = s.length();
        int i = 0;
        while(i < len) {
            int end = i + k * 2 - 1;
            int minEnd = Math.min(end, len - 1);
            int mid = i + k - 1;
            //If there are less than 2k but greater than or equal to k characters, then reverse the first k characters and left the other as origina
            if (mid < len) {
                for (int j = mid; j >= i; j --) {
                    str.append(s.charAt(j));
                }
                for (int j = mid + 1; j <= minEnd; j ++) {
                    str.append(s.charAt(j));
                }
            }
            else {
                for (int j = len -1; j >= i; j --) { //if there are less than k characters left, reverse all of them
                    str.append(s.charAt(j));
                }
            }
            i = end + 1;
        }
        return str.toString();
    }
}

