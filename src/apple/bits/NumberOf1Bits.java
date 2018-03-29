package apple.bits;

public class NumberOf1Bits {
    public int hammingWeight(int n) {
        int count = 0;
        for (int i = 0 ; i < 32; i ++) {
            count = count + (n & 1);
            n = n >> 1;
        }
        return count;
    }
}
