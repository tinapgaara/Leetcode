package lyft.iterator;

import java.util.*;
public class CommenElement {
    Iterator<Integer> iter1;
    Iterator<Integer> iter2;
    Integer prev1;
    Integer prev2;
    Integer common;
    public CommenElement(Iterator<Integer> iter1, Iterator<Integer> iter2) {
        this.iter1 = iter1;
        this.iter2 = iter2;
        prev1 = null;
        prev2 = null;
        common = null;
    }
    public int next() {
        int res = common;
        common = null;
        return res;
    }
    public boolean hasNext() { // if has the next common element
        if (common != null) {
            return true;
        }
        while((iter1.hasNext() || prev1 != null) && (iter2.hasNext() || prev2 != null)) { // important!!! the condition
            Integer num1;
            Integer num2;
            if (prev1 == null && prev2 == null) {
                num1 = iter1.next();
                num2 = iter2.next();
            }
            else if (prev1 != null && prev2 == null) {
                // move num2
                num2 = iter2.next();
                num1 = prev1;
            }
            else {
                // move num1
                num1 = iter1.next();
                num2 = prev2;
            }
            if (num1 == num2) {
                // they are common element
                common = num1;
                prev1 = null;
                prev2 = null;
                return true;
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
        return false;
    }
    public static void main(String[] args) {
        List<Integer> list1 = Arrays.asList(new Integer[]{1,1,2,2,3,4,5,6});
        List<Integer> list2 = Arrays.asList(new Integer[]{2,2,3,5,6});
        CommenElement ob = new CommenElement(list1.iterator(), list2.iterator());
        while(ob.hasNext()) {
            System.out.println(ob.next());
        }
    }
}
