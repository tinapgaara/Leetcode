package lyft.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class IntersectionOfTwoIterator {

    // follow up: what if only given two iterators?
    public List<Integer> intersection(Iterator<Integer> list1, Iterator<Integer> list2) {
        Integer prev1 = null;
        Integer prev2 = null;
        List<Integer> res = new ArrayList<>();
        while((list1.hasNext() || prev1 != null) && (list2.hasNext() || prev2 != null)) { // important!!! the condition
            Integer num1;
            Integer num2;
            if (prev1 == null && prev2 == null) {
                num1 = list1.next();
                num2 = list2.next();
            }
            else if (prev1 != null && prev2 == null) {
                // move num2
                num2 = list2.next();
                num1 = prev1;
            }
            else {
                // move num1
                num1 = list1.next();
                num2 = prev2;
            }
            if (num1 == num2) {
                res.add(num1);
                prev1 = null;
                prev2 = null;
            }
            else if (num1 > num2) {
                // next time move num2, keep num1
                prev1 = num1;
                prev2 = null;
            }
            else {
                // keep num2, move num1
                prev2 = num2;
                prev1 = null;
            }
        }
        return res;
    }
    public static void main(String[] args) {
        List<Integer> list1 = Arrays.asList(new Integer[]{1,1,2,2,3,4,5,6});
        List<Integer> list2 = Arrays.asList(new Integer[]{2,2,3,5,6});
        IntersectionOfTwoIterator ob = new IntersectionOfTwoIterator();
        System.out.println(ob.intersection(list1.iterator(), list2.iterator()));
    }
}
