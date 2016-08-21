package google.bigdata;

/**
 * Created by yingtan on 11/28/15.
 */
/*
* 从最高位流入，digits的数量很大，怎么进行加1操作
*
* */
public class BigPlusOne { // for 十进制

    public static final int MAX_BufLength = 4096;
    public static final byte MAX_Digit = 9;

    private byte[] buffer;
    private int bufferCur;

    private byte lastNon9Digit;
    private byte numOfConsecutive9; // number of 9

    public BigPlusOne() {
        buffer = new byte[MAX_BufLength];
        bufferCur = 0;
        lastNon9Digit = -1;
        numOfConsecutive9 = 0;
    }

    public void plus1(byte[] stream, boolean plusOneFlag) {
        for (int i = 0 ; i < stream.length ; i ++) {
            if (stream[i] < MAX_Digit) {
                flushToBuf(false);
                lastNon9Digit = stream[i];
                numOfConsecutive9 = 0;
            }
            else {
                numOfConsecutive9 ++;
            }
        }

        // need to do plus one
        if (plusOneFlag) {
            if (lastNon9Digit == -1) {
                // all consecutive 9 digits
                // so when add one:  999 + 1 = 1000, there will be one more digit here
                buffer[bufferCur] = 1;
                bufferCur ++;
            }
            else {
                lastNon9Digit ++; // 6999+1 = 7000 or 6 + 1 = 7
            }
            flushToBuf(true); // then concate the following 999, and reverse them as 000
        }
    }

    private void flushToBuf(boolean plusOneFlag) {

        if (lastNon9Digit >= 0 ) { // if not larger than 0, means there is all 9 digits
            buffer[bufferCur] = lastNon9Digit;
            bufferCur ++;
        }

        byte storeDigit = MAX_Digit;// 9  or 0 ? if end which means we need reverse 9, storeDigit is 0.
        if (plusOneFlag)
            storeDigit = 0;

        for (int i = 0 ; i < numOfConsecutive9; i ++) {
            buffer[bufferCur] = storeDigit;
            bufferCur ++;
            if (bufferCur > MAX_BufLength) { // to avoid that buffer is out of memory
                writeToDisk(buffer);
                bufferCur = 0; // clean the buffer
            }
        }
    }

    private void writeToDisk(byte[] buffer) {
        for (int i = 0; i < buffer.length; i++)
            System.out.print("" + buffer[i]);
    }

    public static void main(String[] args) {

        BigPlusOne obj = new BigPlusOne();

        // first block, use false flag
        //byte[] arr = new byte[] {8, 7, 9, 9, 6, 5, 9, 9, 9, 9};
        byte[] arr = new byte[] {9, 9, 9, 9, 9, 9, 9, 9, 9, 9};
        obj.plus1(arr, false);

        //arr = new byte[] {9, 9, 9, 9, 9, 7, 9, 9, 9, 9};
        arr = new byte[] {9, 9, 9, 9, 9, 9, 9, 9, 9, 9};
        obj.plus1(arr, true);

    }
}
