package google.file;

/**
 * Created by yingtan on 11/8/15.
 */
public class Read4 {
    public String buffer = "";
    public int read(char[] buf, int n) {
        int res = 0;
        int exisitedLen = buffer.length();
        int readBufLen = Math.min(exisitedLen, n);

        for (int i = 0 ; i < readBufLen; i ++) {
            buf[i] = buffer.charAt(i);
        }
        buffer = buffer.substring(readBufLen, buffer.length());// report index outof bound ?
        res = res + readBufLen;

        int newReadLen = n - readBufLen;
        int read4LenOnce = 0;
        int readActualLenOnce = 0;
        char[] tmp = new char[4];
        while (newReadLen > 0) {
            read4LenOnce = read4(tmp);
            if (read4LenOnce == 0) break;
            readActualLenOnce = Math.min(read4LenOnce, newReadLen);

            for (int i = res; i <= res - 1 + readActualLenOnce; i ++) {
                buf[i] = tmp[i - res];
            }
            res = res + readActualLenOnce;
            newReadLen = newReadLen - readActualLenOnce;
        }
        int storeBufLen = read4LenOnce - readActualLenOnce; // >0
        if (storeBufLen > 0) {
            for (int i = 0 ; i < storeBufLen; i ++) {
                buffer = buffer + tmp[readActualLenOnce+ i];
            }
        }
        return res;
    }

    public int read4(char[] tmp) {
        return 0;
    }
}
