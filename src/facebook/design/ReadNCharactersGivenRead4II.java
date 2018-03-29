package facebook.design;

/**
 * Created by yingtan on 2/12/18.
 *
 * 158. Read N Characters Given Read4 II - Call multiple times
 DescriptionHintsSubmissionsDiscussSolution
 Pick One
 The API: int read4(char *buf) reads 4 characters at a time from a file.

 The return value is the actual number of characters read. For example, it returns 3 if there is only 3 characters left in the file.

 By using the read4 API, implement the function int read(char *buf, int n) that reads n characters from the file.

 Note:
 The read function may be called multiple times.

 */
// firstly, need to read buffer left from last round
// if not reach n, then call read 4 in a while loop, until the read ones count >= n
// each time, if read4 return 0, this is end of file, return
// if read total > n , save it to buffer

public class ReadNCharactersGivenRead4II {
    StringBuilder builder = new StringBuilder();
    char[] buf4 = new char[4];
    // firstly, need to read buffer left from last round
// if not reach n, then call read 4 in a while loop, until the read ones count >= n
// each time, if read4 return 0, this is end of file, return
// if read total > n , save it to buffer
    public int read(char[] buf, int n) {
        int chReadSoFar = 0;
        String newBufferStr = "";
        // Step 1: read from buffer
        int readLenFromBuffer = Math.min(builder.toString().length(),n);
        for (int i = 0 ; i < readLenFromBuffer ; i ++) {
            buf[chReadSoFar] = builder.toString().charAt(0);
            builder.deleteCharAt(0);
            chReadSoFar ++;
        }
        // Step 2:  buffer is not enough, so read from read4
        while(chReadSoFar < n) {
            int actualChCountPerRound = read4(buf4);
            if (actualChCountPerRound == 0) {
                // end of file
                return chReadSoFar;
            }
            int actualReadCount = Math.min(actualChCountPerRound,n - chReadSoFar);
            for (int i = 0 ; i < actualReadCount; i ++) {
                buf[chReadSoFar] = buf4[i];
                chReadSoFar ++;
            }
            // check if have the left buffer string, save it to new buffer
            for (int i = actualReadCount; i < actualChCountPerRound; i ++) {
                builder.append(buf4[i]);
            }
        }
        return chReadSoFar;
    }

    public int read4(char[] buf) {
        return 0;
    }
}
