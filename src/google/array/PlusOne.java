package google.array;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yingtan on 11/8/15.
*/
public class PlusOne {

    public List<Integer> plusOne(int[] num) {
        List<Integer> res = new ArrayList<Integer>();
        int carry = 1;
        for (int i = num.length-1; i >= 0 ; i --) {
            int digit = (num[i] + carry) % 10;
            carry = (num[i] + carry) / 10;
            res.add(0, digit);
        }
        if (carry > 0) {
            res.add(0, carry);
        }

        return res;
    }

    public static void main(String[] args) {
        int[] num = new int[]{9,1};
        PlusOne ob = new PlusOne();
        System.out.println(ob.plusOne(num));
    }
}
