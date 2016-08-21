package bloomberg.design.bigdata;

/**
 * Created by yingtan on 11/16/15.
 */
public class BigInteger {

    private Byte[] bytes; // 10
    private final static int MAX_VALUE = 0x20000000; // 512 M bytes.length * 8 1M: ???
    // shows 8 different numbers: where each number, there is only one bit to be 1, rest are 0.

    // offset = 0 -> 0x80 : 10000000
    // offset = 1:-> 0x40 : 01000000
    private Byte[] mbits = new Byte[]{(byte) 0x80, 0x40, 0x20, 0x10, 0x08, 0x04, 0x02, 0x01};


    public void storeInteger(long num) {
        int offset = (int)num % 8;
        int index = (int)num >> 3; // num / 8;

        bytes[index] = mbits[offset];
    }

    public boolean findInteger(long num) {
        int offset = (int)num % 8;
        int index = (int)num >> 3;

        if ((bytes[index] & mbits[offset]) == 1) return true;
        else return false;
    }

}
