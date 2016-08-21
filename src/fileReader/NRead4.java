package fileReader;

/**
 * Created by yingtan on 9/26/15.
 */
/*
public class NRead4 extends Reader4 {
    /**
     * @param buf Destination buffer
     * @param n   Maximum number of characters to read
     * @return    The number of characters read
     */
/*
    public int read(char[] buf, int n) {
        int total = 0;
        char[] tmp = new char[4];
        while (total < n) {
            int actualCount = read4(tmp);
            if ((n - total) < actualCount)
                actualCount = n - total;
            for (int i = 0; i < actualCount; i ++) {
                buf[total] = tmp[i];
                total ++;
            }
            if (actualCount < 4)
                break;
        }
        return total;
    }
}
*/