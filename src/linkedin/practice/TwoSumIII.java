package linkedin.practice;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by yingtan on 11/22/17.
 *
 *  170. Two Sum III - Data structure design
 DescriptionHintsSubmissionsDiscussSolution
 Discuss Pick One
 Design and implement a TwoSum class. It should support the following operations: add and find.

 add - Add the number to an internal data structure.
 find - Find if there exists any pair of numbers which sum is equal to the value.

 For example,
 add(1); add(3); add(5);
 find(4) -> true
 find(7) -> false
 */
public class TwoSumIII {
    Map<Integer, Integer> count;

    public TwoSumIII() {
        count = new HashMap<>();
    }

    /** Add the number to an internal data structure.. */
    public void add(int number) {
        if (count.containsKey(number)) {
            count.put(number, count.get(number) + 1);
        }
        else {
            count.put(number, 1);
        }
    }

    /** Find if there exists any pair of numbers which sum is equal to the value. */
    // time: o(n) space: o(n)
    public boolean find(int value) {
       for (Map.Entry<Integer, Integer> entry : count.entrySet()) {
           int num1 = entry.getKey();
           int num2 = value - num1;
           if (count.containsKey(num2)) {
               if (num2 == num1) {
                   return count.get(num2) >= 2;
               }
               return true;
           }
       }
        return false;
    }
}
