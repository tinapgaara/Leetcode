package facebook.string;

/**
 * Created by yingtan on 2/13/18.
 */
public class ValidPalindrome {
    public boolean isPalindrome(String s) {
        if (s == null || s.length() == 0) return true;
        s = s.trim();
        int low = 0;
        int high = s.length() - 1;
        while(low < high) {
            char lowch = s.charAt(low);
            char highch = s.charAt(high);
            boolean lowValid = Character.isLetterOrDigit(lowch);
            boolean highValid = Character.isLetterOrDigit(highch);
            if (lowValid && highValid) {
                if (Character.toLowerCase(lowch) != Character.toLowerCase(highch)) {
                    return false;
                }
                else {
                    low ++;
                    high --;
                }
            }
            else if (lowValid) {
                // but high not valid
                high --;
            }
            else if (highValid) {
                // but low not valid
                low ++;
            }
            else {
                // both not valid
                low ++;
                high --;
            }
        }
        return true;
    }

}
