package google.file;

/**
 * Created by yingtan on 11/22/15.
 */
/*
* 给了⼀一个UTF-8的pattern：
1byte - 0XXX, XXXX
2byte - 110X, XXXX, 10XX,XXXX
3byte - 1110, XXXX, 10XX,XXXX, 10XX,XXXX
4byte - 1111, 0XXX, 10XX,XXXX, 10XX,XXXX, 10XX,XXXX
5byte - 1111, 10XX, 10XX,XXXX, 10XX,XXXX, 10XX,XXXX
6byte - 1111, 110X, 10XX,XXXX, 10XX,XXXX, 10XX,XXXX
7byte - 1111, 1110, 10XX,XXXX, 10XX,XXXX, 10XX,XXXX,
8byte - 1111, 1111, 10XX,XXXX, 10XX,XXXX, 10XX,XXXX,

len = 1:
 0XXX, XXXX  & 1000,0000,0000,0000 (0x8000) = 0000,0000,0000,0000 (0)
len = 2:
 110X,XXXX,10XX,XXXX & 1110,0000,0000,0000 (0xe000) = 1100,0000,0000,0000 (0xc000)
len = 3:
 1110,XXXX,10XX,XXXX,10XX,XXXX & 1111,0000,0000,0000 (0xf0000) = 1110,0000,0000,0000 (0xe000)
len = 4:
 1111,0XXX,10XX,XXXX,10XX,XXXX,10XX,XXXX & 1111,1000,0000,0000 (0xf800)= 1111,0000,0000,0000 (0xf0000)
len = 5:
 1111,10XX,10XX,XXXX,10XX,XXXX,10XX,XXXX & 1111,1100,0000,0000 (0xfc00) = 1111,1000,0000,0000 (0xf800)
len = 6:
 1111,110X,10XX,XXXX,10XX,XXXX,10XX,XXXX & 1111,1110,0000,0000 (0xfe00) = 1111,1100,0000,0000 (0xfc00)
len = 7:
 1111,1110,10XX,XXXX,10XX,XXXX,10XX,XXXX & 1111,1111,0000,0000 (0xff00) = 1111,1110,0000,0000 (0xfe00)
len = 8:
 1111,1111,10XX,XXXX,10XX,XXXX,10XX,XXXX & 1111,1111,0000,0000 (0xff00) = 1111,1111,0000,0000 (0xff00)

然后让写⼀一个boolean 函数：
input: byte数组
output： trye or false;
boolean isvalidUTF-8(byte[] input){
}
*
* */
public class UTF8Validation {

    boolean isvalidUTF8(byte[] input){

        // according to the first byte in arr, judge the length of this input arr
        byte first = input[0];
        int expectedLen = 0;
        if ((first & 0x8000) == 0) {
            expectedLen = 1;
        }
        else if ((first & 0xe000) == (0xc000)) {
            expectedLen = 2;
        }
        else if ((first & 0xf000) == (0xe000)) {
            expectedLen = 3;
        }
        else if ((first & 0xf800) == (0xf000)) {
            expectedLen = 4;
        }
        else if ((first & 0xfc00) == (0xf800)) {
            expectedLen = 5;
        }
        else if ((first & 0xfe00) == (0xfc00)) {
            expectedLen = 6;
        }
        else if ((first & 0xff00) == (0xfe00)) {
            expectedLen = 7;
        }
        else if ((first & 0xff00) == (0xff00)) {
            expectedLen = 8;
        }
        else {
            return false;
        }

        if (expectedLen !=input.length) {
            return false;
        }

        // judge rest part of byte array
        // must be the same form :
        // 10XX,XXXX, 10XX,XXXX, 10XX,XXXX
        // each 10xx,xxxx & 1100,0000 = must equals to 1000,0000
        for (int i = 1; i < input.length; i ++) {
            if ((input[i] & 0xc0) != (0x80)) {
                return false;
            }
        }
        return true;
    }

}
