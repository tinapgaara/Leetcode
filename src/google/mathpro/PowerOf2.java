package google.mathpro;

/**
 * Created by yingtan on 12/22/15.
 */
/*
* Given an integer, write a function to determine if it is a power of two.
*
*
* */
public class PowerOf2 {

    public boolean isPowerOfTwo(int n) {
        int helper = 1;
        int i = 0;
        while ((helper < n) && (i < 31) ){
            helper = helper << 1;
            i ++;
        }
        if (helper != n) return false;
        else return true;

    }
}

