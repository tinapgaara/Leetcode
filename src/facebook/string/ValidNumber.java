package facebook.string;

public class ValidNumber {
    /*
    * several rules: . e  + - 0-9
    * e: the previous chs can not have e; the previous chs must have number, the later chs must have number
    * +/-: the one ch before must be e
    * .: the previous chs can not have e .
    *
    * */
    public boolean isNumber(String s) {
        if (s == null || s.length() == 0) return false;
        s = s.trim();
        boolean hasNumber = false;
        boolean hasNumberAfterE = false;
        boolean hasE = false;
        boolean hasDot = false;
        for (int i = 0 ; i < s.length(); i ++) {
            char ch = s.charAt(i);
            if (ch >= '0' && ch <= '9') {
                hasNumber = true;
                hasNumberAfterE = true;
            }
            //e: the previous chs can not have e; the previous chs must have number
            else if (ch == 'e') {
                if (hasE || ! hasNumber) {
                    return false;
                }
                hasNumberAfterE = false; // reset here, since e appear, must make sure a number appear after e
                hasE = true;
            }
            else if (ch == '.') { // .: the previous chs can not have e .
                if (hasDot || hasE) {
                    return false;
                }
                hasDot = true;
            }
            else if (ch == '+' || ch == '-') {
                if (i > 0 && (s.charAt(i -1) != 'e')) { // +/-: the one ch before must be e. A number can start with -/+ : -1
                    return false;
                }
            }
            else {
                return false; // Important !!!
            }
        }
        // make sure if there is a e, there is a number after e appear
        // even there is no e, we still set hasNumberAfterE = true whenever a number appear so it is fine.
        return hasNumber && hasNumberAfterE;
    }
}
