package tree;

/**
 * Created by yingtan on 9/25/15.
 */
public class VerifyPreorder {

    public boolean verifyPreorder(int[] preorder) {
        if ((preorder == null) || (preorder.length == 0))
            return true;
        return recurVerifyPreorder(preorder, 0, preorder.length -1);
    }

    public boolean recurVerifyPreorder(int[] preorder, int low, int high) {
        if (low >= high)
            return true;
        int curVal = preorder[low];
        int newLow = low + 1 ;

        Integer newHigh = null;
        for(int i = low + 1; i <= high; i ++) {
            if (newHigh == null) {
                if (preorder[i] > curVal)
                    newHigh = new Integer(i);
            }
            else {
                if (preorder[i] < curVal) {
                    return false;
                }
            }
        }
        if (newHigh == null)
            return recurVerifyPreorder(preorder, newLow, high);
        else if (newHigh.intValue() <= newLow) {
            return recurVerifyPreorder(preorder, newHigh.intValue(), high);
        }
        else {
            return (recurVerifyPreorder(preorder, newLow, newHigh.intValue()-1)
                    && recurVerifyPreorder(preorder, newHigh.intValue(), high));
        }
    }
}
