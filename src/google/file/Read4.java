package google.file;

/**
 * Created by yingtan on 11/8/15.
 */
public class Read4 {
    public String buffer = "";

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
