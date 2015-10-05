package fileReader;

/**
 * Created by yingtan on 9/29/15.
 */
public class Reader {

    /*
    * The API: int read4(char *buf) reads 4 characters at a time from a file.

     The return value is the actual number of characters read. For example, it returns 3 if there is only 3 characters left in the file.

     By using the read4 API, implement the function int read(char *buf, int n) that reads n characters from the file.

    Note: The read function may be called multiple times.
    * */


    public String buffer = "";
    public int read(char[] buf, int n) {
        int res = 0;
        int exisitedLen = buffer.length();
        int readBufLen = Math.min(exisitedLen, n);

        for (int i = 0 ; i < readBufLen; i ++) {
            buf[i] = buffer.charAt(i);
        }
        buffer = buffer.substring(readBufLen, buffer.length());// report index out of bound ?
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
        tmp[0] = 'a';
        tmp[1] = 'b';
        tmp[2] = 'c';
        return 3;
    }

    public static void main(String[] args) {
        Reader ob = new Reader();
        char[] buf = new char[512];
        int n = ob.read(buf, 2);
        for (int i = 0 ; i < n ; i ++)
            System.out.println(buf[i]);
    }
}
