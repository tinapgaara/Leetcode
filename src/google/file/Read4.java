package google.file;

/**
 * Created by yingtan on 11/8/15.
 */
public class Read4 {
    public String buffer = "";

    public int read_new(char[] buf, int n) {
        int res = 0;
        // Step 1:  find if can find n in left buff
        int existLen = buffer.length();
        int readBufLen = Math.min(existLen, n);
        for (int i = 0 ; i < readBufLen; i ++) {
            buf[i] = buffer.charAt(i);
        }
        // after getting sth from buffer, substring buffer
        buffer = buffer.substring(readBufLen, buffer.length());
        res = res + readBufLen;

        // Step 2: find the left part in read4
        // new chs needed
        int newReadLen = n - res;
        // one time chs read from read4
        int read4Len = 0;
        // how many chs actually needed
        int readActualLen = 0;
        char[] read4Buf = new char[4];

        while (newReadLen > 0) {
            read4Len = read4(read4Buf);
            if (read4Len == 0) break;
            readActualLen = Math.min(read4Len, newReadLen);
            for (int i = 0 ; i < readActualLen; i ++) {
                buf[i+res] = read4Buf[i];
            }
            res = res + readActualLen;
            newReadLen = newReadLen - readActualLen;
        }

        // Step 3: store left chs in read4 to buff
        int storeBufLen = read4Len - readActualLen;
        if (storeBufLen > 0) {
            for (int i = 0 ; i < storeBufLen; i ++) {
                buffer = buffer + read4Buf[readActualLen+ i];
            }
        }
        return res;
    }


    public int read(char[] buf, int n) {
        // keep track of result len
        int res = 0;

        // firstly read from buffer
        int readBufferLen = Math.min(buffer.length(), n);
        for (int i = res; i < res + readBufferLen; i ++) {
            buf[i] = buffer.charAt(i);// store buffer to result[]
        }
        buffer = buffer.substring(readBufferLen); // keep rest of buffer in memory
        res = res + readBufferLen; // attach new read len to res

        // secondly read from read4
        int leftReadLen = n - readBufferLen;// how many left chars we need to read
        char[] read4OnceBuf = new char[4];
        int read4OnceLen = 0; // call read4 once, the result it returns

        /*
        * Because sometimes: leftReadLen  < read4OnceLen, so we do not need 4 chars, just need 3.
        * In this case, we want o keep actual len to use which is 3.
        * So we have:
        *  actualRead4OnceLen = Math.min(leftReadLen, read4OnceLen);
        *
        *  Rest of the chars in read4OnceLen, we store it in buffer.
        * */
        int actualRead4OnceLen = 0;
        while (leftReadLen > 0) {
            read4OnceLen = read4(read4OnceBuf);
            // Very important here !!!!
            if (read4OnceLen == 0) break;
            actualRead4OnceLen = Math.min(leftReadLen, read4OnceLen);
            // append new read len to result char array
            for (int i = res; i < res + actualRead4OnceLen; i ++) {
                buf[i] = read4OnceBuf[i-res];
            }
            // attach new read len to res
            res = res + actualRead4OnceLen;
            // recalculate rest of chars we need to read
            leftReadLen = leftReadLen - actualRead4OnceLen;
        }

        int storeBufLen = read4OnceLen - actualRead4OnceLen;
        for (int i = 0 ; i < storeBufLen; i ++) {
            buffer = buffer + read4OnceBuf[actualRead4OnceLen+i];
        }
        return res;
    }

    public int read4(char[] tmp) {
        return 0;
    }
}
