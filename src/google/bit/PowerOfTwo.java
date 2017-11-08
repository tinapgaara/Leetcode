package google.bit;

/**
 * Created by yingtan on 8/20/17.
 *
 * 231. Power of Two
 DescriptionHintsSubmissionsDiscussSolution
 Discuss Pick One
 Given an integer, write a function to determine if it is a power of two.


 */
public class PowerOfTwo {

    // 从1开始一直扩大*2，最后看是否可以这样扩大到n
    public boolean isPowerOfTwo(int n) {
        int m = 1;
        int i = 0;
        while (m < n && i < 31) {
            m = m << 1;
            i ++;
        }
        if (m == n) return true;
        else return false;
    }
}
