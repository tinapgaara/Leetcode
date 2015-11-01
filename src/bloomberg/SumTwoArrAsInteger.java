package bloomberg;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by yingtan on 10/25/15.
 */
public class SumTwoArrAsInteger {

    public List<Integer> sumTwoArray(List<Integer> arr1, List<Integer> arr2) {
        int carry = 0;
        List<Integer> res = new ArrayList<>();

        int i = arr1.size() - 1;
        int j = arr2.size() - 1;

        while ((i >= 0 ) && (j >= 0)) {
            int digit1 = arr1.get(i);
            int digit2 = arr2.get(j);

            int digit = (digit1 + digit2 + carry) % 10;
            carry = (digit1 + digit2 + carry) / 10;

            res.add(0, digit);

            i --;
            j --;
        }

        if (i >= 0) {
            while (i >= 0) {
                int digit = (arr1.get(i) + carry) % 10;
                carry = (arr1.get(i) + carry) / 10;

                res.add(0, digit);
                i --;
            }
        }
        else {
            while (j >= 0) {
                int digit = (arr2.get(j) + carry) % 10;
                carry = (arr2.get(j) + carry) / 10;

                res.add(0, digit);
                j --;
            }
        }

        if (carry > 0) res.add(0, carry); // Important !!!!
        return res;
    }

    public static void main(String[] args) {
        SumTwoArrAsInteger ob = new SumTwoArrAsInteger();
        List<Integer> arr1 = Arrays.asList(1, 8 , 9, 6);
        List<Integer> arr2 = Arrays.asList(9, 4 , 8);
        System.out.println(ob.sumTwoArray(arr1,arr2));
    }
}
