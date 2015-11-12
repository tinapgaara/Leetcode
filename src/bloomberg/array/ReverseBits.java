package bloomberg.array;

/**
 * Created by yingtan on 10/16/15.
 */
/*
* Leetcode: reverse bits
*
* */
public class ReverseBits {

    public int reverseBits(int n) {
        int i = 0;
        int num = n & 1;
        while (i < 31) {
            n = n >> 1;
            int digit = n & 1;
            num = (num << 1) + digit;
            i++;
        }
        return num;
    }
}
