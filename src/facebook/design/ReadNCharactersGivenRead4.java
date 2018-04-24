package facebook.design;

/**
 * Created by yingtan on 2/12/18.
 *
 * 157. Read N Characters Given Read4
 DescriptionHintsSubmissionsDiscussSolution
 Pick One
 The APIToAddPoint: int read4(char *buf) reads 4 characters at a time from a file.

 The return value is the actual number of characters read. For example, it returns 3 if there is only 3 characters left in the file.

 By using the read4 APIToAddPoint, implement the function int read(char *buf, int n) that reads n characters from the file.

 Note:
 The read function will only be called once for each test case.


 */
public class ReadNCharactersGivenRead4 {
    public int read(char[] buf, int n) {
        int chCountReadSoFar = 0;
        int chCountLeft = n;
        while(chCountReadSoFar < n) {
            char[] fourbuf = new char[4];
            int chsCountThisRound = read4(fourbuf);
            if (chsCountThisRound == 0) return chCountReadSoFar; //  end of file
            int actualChToPut = Math.min(chsCountThisRound, chCountLeft); // important !!!!
            for (int i = 0 ; i < actualChToPut; i ++) {
                buf[chCountReadSoFar] = fourbuf[i];
                chCountReadSoFar ++;
                chCountLeft --;
            }
        }
        return chCountReadSoFar;
    }

    public int read4(char[] buf) {
        return 0;
    }
}
