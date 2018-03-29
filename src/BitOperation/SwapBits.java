package BitOperation;

/**
 * Created by yingtan on 2/7/18.
 */
public class SwapBits {

    /*
    The difference between >> and >>> would only show up when shifting negative numbers.
    The >> operator shifts a 1 bit into the most significant bit if it was a 1, and the >>> shifts in a 0 regardless.
    * */
    public long swapBits(long x, int i, int j) {
        // swap bits at ith and jth
        long bit_i = (x >> i) & 1;
        long bit_j = (x >> j) & 1;
        if (bit_i != bit_j) {
            // swap bit equals to revert bit_i and revert bit_j
            // revert 1 = 1 ^ 1
            // revert 0 = 0 ^ 1
            long bitMask = (1 << i) | (1 << j);
            x = x ^ bitMask;
        }
        return x;
    }
}
