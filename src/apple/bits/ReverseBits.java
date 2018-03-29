package apple.bits;

public class ReverseBits {
    public int reverseBits(int n) {
        if (n == 0) return 0;
        int res = 0;
        for (int i = 0 ; i < 32; i ++) {
            int digit = n & 1;
            res = (res << 1) + digit;
            n = n >> 1;
        }
        return res;
    }
    /*
    *
    * Follow up:
      If this function is called many times, how would you optimize it?
    *
    * How to optimize if this function is called multiple times? We can divide an int into 4 bytes,
     * and reverse each byte then combine into an int. For each byte, we can use cache to improve performance.
    * */
}
