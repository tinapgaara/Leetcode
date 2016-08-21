package search;

/**
 * Created by yingtan on 9/26/15.
 */
public class Sqrt {

    // Solution 1: binarySearch: time limited
    // Should using Newton's method
    public int mySqrt(int x) {
        return binarySearch(x,1,x);
    }

    public int binarySearch(int value, int lowVal, int highVal) {
        System.out.println(lowVal + "," + highVal);
        if (lowVal >= highVal) {
            if (lowVal * lowVal == value)
                return lowVal;
            else
                return -1;
        }
        int middleVal = (lowVal + highVal) / 2;
        int leftVal = binarySearch(value, lowVal, middleVal);
        if (leftVal != -1) return leftVal;
        else {
            int rightVal = binarySearch(value, middleVal + 1, highVal);
            if (rightVal != -1)  return rightVal;
        }
        return -1;
    }

    public static void main(String[] args) {
        Sqrt ob = new Sqrt();
        ob.mySqrt(2147395599);// boudnary case, can not pass
    }
}
