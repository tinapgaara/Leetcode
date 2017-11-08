package google.string;

/**
 * Created by yingtan on 5/30/17.
 */
public class ReverseVowelString {

    public boolean isVow(char ch) {
        if (ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u' ||
                ch == 'A' || ch == 'E' || ch == 'I' || ch == 'O' || ch == 'U') return true;
        else return false;
    }

    public String reverseVowels(String s) {
        if (s == null || s.length() == 0) return "";
        int start = 0;
        int end = s.length() -1;
        char[] chs = s.toCharArray();
        while (start < end) {
            char start_ch = chs[start];
            char end_ch = chs[end];

            boolean startVow = isVow(start_ch);
            boolean endVow = isVow(end_ch);

            if ( (startVow) && (endVow) ) {
                chs[start] = end_ch;
                chs[end] = start_ch;
                start ++;
                end --;
            }
            else if ( (startVow) && (! endVow)) {
                end --;
            }
            else if ( (endVow) && ( ! startVow)) {
                start ++;
            }
            else {
                start ++;
                end --;
            }
        }
        return new String(chs);
    }

    public static void main(String[] args) {
        ReverseVowelString ob = new ReverseVowelString();
        ob.reverseVowels("hello");
    }
}
