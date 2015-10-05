package string;

/**
 * Created by yingtan on 9/24/15.
 */
public class OneEditDistance {

    public boolean isOneEditDistance(String s, String t) {
        if ((s == null) || (t == null)) return false;

        int len_s = s.length();
        int len_t = t.length();
        if ((len_s == 0) && (len_t == 0)) return false;

        int count = 0;
        if (len_s == len_t) {
            for (int i = 0 ; i < len_s; i ++) {
                if (s.charAt(i) != t.charAt(i)) {
                    count ++;
                }
            }
        }
        else {
            int i = 0 , j = 0;
            if (len_s < len_t) { // s: longer
                String tmp = s;
                s = t;
                t = tmp;
            }
            while ((j < Math.min(len_s, len_t)) && (i < Math.max(len_s, len_t))) {
                if (s.charAt(i) != t.charAt(j)) {
                    count ++;
                } else {
                    j ++;
                }
                i ++;
            }
            if (Math.max(len_s, len_t) - i == 1) {
                if (count == 0) return true;
                else return false;
            }
        }
        if (count == 1)
            return true;
        else
            return false;
    }

    public static void main(String[] args) {
        String s = "teacher";
        String t = "tache";
        OneEditDistance ob = new OneEditDistance();
        System.out.println(ob.isOneEditDistance(s, t));
    }
}
