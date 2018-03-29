package facebook.string;

/**
 * Created by yingtan on 2/13/18.
 */
public class ValidPalindromeII {

    public boolean validPalindrome(String s) {
        if (s == null || s.length() == 0) return true;
        int low = 0;
        int high = s.length() - 1;
        s = s.trim();
        while(low < high) {
            if (s.charAt(low) == s.charAt(high)) {
                low ++;
                high --;
            }
            else {
                int newlow = low + 1; // delete low ch
                if (isValid(s, newlow, high)) return true;
                int newhigh = high - 1;// delete high ch
                if (isValid(s, low, newhigh)) return true;

                return false;
            }
        }
        return true;
    }
    public boolean isValid(String s, int low, int high) {
        while(low <= high) {
            if (s.charAt(low) != s.charAt(high)) return false;
            low ++;
            high --;
        }
        return true;
    }

}
