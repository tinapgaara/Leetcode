package facebook;

/**
 * Created by yingtan on 5/29/17.
 *
 * 157. Read N Characters Given Read4 Add to List
 DescriptionHintsSubmissionsSolutions
 Total Accepted: 30073
 Total Submissions: 103545
 Difficulty: Easy
 Contributor: LeetCode
 The API: int read4(char *buf) reads 4 characters at a time from a file.

 The return value is the actual number of characters read. For example, it returns 3 if there is only 3 characters left in the file.

 By using the read4 API, implement the function int read(char *buf, int n) that reads n characters from the file.

 Note:
 The read function will only be called once for each test case.
 */
public class read4 {

    public String buffer = "";

    public int read(char[] buf, int n) {
        int res = 0;
        int index = 0;
        while (res < n) {
            char[] read4Buffer = new char[4];
            int newOnes = read4(read4Buffer);
            if (newOnes <= 0) return res;
            res  = res + newOnes;
            if (res > n) {
                int diff = (res - n);
                newOnes = newOnes - diff;
                res = n;
            }
            for (int i = 0 ; i < newOnes; i ++) {
                buf[index] = read4Buffer[i];
                index++;
            }
        }
        return res;
    }


    public int read_multipletimes(char[] buf, int n) {
        int res = 0;
        // Step 1:  find if can find n in left buff
        int existLen = buffer.length();
        int readBufLen = Math.min(existLen, n);
        for (int i = 0; i < readBufLen; i++) {
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

    public int read4(char[] tmp) {
        return 0;
    }
}
