package string;

/**
 * Created by yingtan on 1/10/18.
 */
public class SnackString {

    public String snackString(String s) {
        StringBuilder res = new StringBuilder();
        // s[1] s[5] s[9]
        for (int i = 1; i < s.length(); i = i + 4) {
            res.append(s.charAt(i));
        }
        // s[0] s[2] s[4] s[6]
        for (int i = 0; i < s.length(); i = i + 2) {
            res.append(s.charAt(i));
        }
        // s[3] s[7] s[11]
        for (int i = 3; i < s.length(); i = i + 4) {
            res.append(s.charAt(i));
        }
        return res.toString();
    }
}
