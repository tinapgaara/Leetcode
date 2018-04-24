package lyft.array;
import java.util.*;
public class IntersectionOfTwoSortedList {
    public List<Integer> intersection(List<Integer> list1, List<Integer> list2) {
        List<Integer> res = new ArrayList<>();
        int i1 = 0;
        int i2 = 0;
        while(i1 < list1.size() && i2 < list2.size()) {
            Integer num1 = list1.get(i1);
            Integer num2 = list2.get(i2);
            if (num1 == num2) {
                res.add(num1);
                i1 ++;
                i2 ++;
            }
            else if (num1  < num2) {
                i1 ++;
            }
            else {
                i2 ++;
            }
        }
        return res;
    }
}
