package google.palindrome;

/**
 * Created by yingtan on 5/7/17.
 */
public class ValidPalidrome {

    public boolean isPalindrome(String s) {
        if (s == null || s.length() == 0) return true;
        char[] arr = s.toLowerCase().trim().toCharArray();
        int low = 0 ;
        int high = arr.length - 1;

        while (low < high) {
            char lowCh = arr[low];
            char highCh = arr[high];

            if (! isAlp(lowCh)) {
                low ++;
                continue;
            }
            if (! isAlp(highCh)) {
                high --;
                continue;
            }

            if (lowCh != highCh) return false;
            low ++;
            high --;
        }
        return true;
    }

    public boolean isAlp(char ch) {
        if ((ch >= 65 && ch <= 90) || (ch >= 97 && ch <= 122) || (ch >= 48 && ch <= 57)) return true;
        else return false;
    }
}
