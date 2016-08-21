package bloomberg.mathpro;

/**
 * Created by yingtan on 10/25/15.
 */
/*
*
* Implement int sqrt(int x).

Compute and return the square root of x.
*
* */
public class SqrtX {

    // judge if log2X is 整数
    public boolean isLogTwo(int x) {

        if (x == 1) return true;

        double dx = (double)x;
        while (dx > 1) {
            dx = dx / 2;
        }
        if (dx == 1) return true;
        else return false;
    }

    public int mySqrt(int x) {
        //return binarySearch(x,1,x);
        return 0;
    }

    public static void main(String[] args) {
        SqrtX ob = new SqrtX();
        System.out.println(ob.isLogTwo(9));
    }
}
