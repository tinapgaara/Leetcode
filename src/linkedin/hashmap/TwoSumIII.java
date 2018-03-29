package linkedin.hashmap;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by yingtan on 11/18/17.
 *
 * 170. Two Sum III - Data structure design
 DescriptionHintsSubmissionsDiscussSolution
 Discuss Pick One
 Design and implement a TwoSum class. It should support the following operations: add and find.

 add - Add the number to an internal data structure.
 find - Find if there exists any pair of numbers which sum is equal to the value.

 For example,
 add(1); add(3); add(5);
 find(4) -> true
 find(7) -> false

 先用省时间的方法做的，果然follow up问有没有省空间的方法，以及什么时候用哪一种方法。
 */
public class TwoSumIII {

    Map<Integer, Integer> count;

    // Sol 2: for calling find() multiple times, calling add one time
    Set<Integer> nums;
    Set<Integer> sum;

    /** Initialize your data structure here. */
    public TwoSumIII() {
        count = new HashMap<>();
        nums = new HashSet<>();
        sum = new HashSet<>();
    }

    /** Add the number to an internal data structure.. */
    // used for calling add multiple times
    // o(1)
    public void add(int number) {
        if (count.containsKey(number)) {
            count.put(number, count.get(number) + 1);
        } else {
            count.put(number, 1);
        }
    }

    /** Find if there exists any pair of numbers which sum is equal to the value. */
    // o(n)
    public boolean find(int value) {
        for (Map.Entry<Integer, Integer> entry : count.entrySet()) {
            int num1 = entry.getKey();
            int num2 = value - entry.getKey();
            if (num1 == num2) {
                if (entry.getValue() >= 2) {
                    return true;
                }
            }
            else {
                if (count.containsKey(num2)) {
                    return true;
                }
            }
        }
        return false;
    }


    // o(n)
    public void add_2(int number) {
        if (nums.contains(number)) {
            sum.add(number * 2);
            return;
        }
        else {
            for (Integer num : nums) {
                sum.add(num + number);
            }
        }
    }

    //o(1)
    public boolean find_2(int value) {
        return sum.contains(value);
    }
}
