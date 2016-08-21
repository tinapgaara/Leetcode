package google.stream;

/**
 * Created by yingtan on 11/24/15.
 */
public class OneEditDistanceStream {

    private int diff;
    private String buffer1;
    private String buffer2;

    public OneEditDistanceStream() {
        diff = 0;
        buffer1 = "";
        buffer2 = "";
    }

    public boolean isOneEditDistance(String stream1, String stream2) {
        if (diff > 1) return false;

        String cur1 = buffer1 + stream1;
        String cur2 = buffer2 + stream2;

        int compareLen = Math.min(cur1.length(), cur2.length());
        diff = diff + compareDistance(cur1, cur2, compareLen);
        if (diff > 1) return false;

        if (cur1.length() < cur2.length()) {

            buffer1 = "";
            buffer2 = cur2.substring(compareLen);

            if (cur2.length() - cur1.length() == 1) {
                if (diff == 0) return true;
            }
        }
        else if (cur1.length() > cur2.length()) {

            buffer1 = cur1.substring(compareLen);
            buffer2 = "";

            if (cur1.length() - cur2.length() == 1) {
                if (diff == 0) return true;
            }
        }
        else {
            buffer1 = "";
            buffer2 = "";

            return true;
        }
        return false;
    }

    public int compareDistance(String cur1, String cur2, int compareLen) {
        int count = 0 ;
        for (int i = 0 ; i < compareLen ; i ++) {
            if (cur1.charAt(i) != cur2.charAt(i)) {
                count ++;
                if (count > 1) break;
            }
        }
        return count;
    }

}
