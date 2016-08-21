package google.iterator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by yingtan on 11/20/15.
 */
/*
*Given two 1d vectors, implement an iterator to return their elements alternately.

For example, given two 1d vectors:

v1 = [1, 2]
v2 = [3, 4, 5, 6]
By calling next repeatedly until hasNext returns false, the order of elements returned by next should be: [1, 3, 2, 4, 5, 6].

Follow up: What if you are given k 1d vectors? How well can your code be extended to such cases?

Clarification for the follow up question - Update (2015-09-18):
The "Zigzag" order is not clearly defined and is ambiguous for k > 2 cases. If "Zigzag" does not look right to you, replace
"Zigzag" with "Cyclic". For example, given the following input:

[1,2,3]
[4,5,6,7]
[8,9]
It should return [1,4,8,2,5,9,3,6,7].
* */
public class ZigzagIterator {

    private List<List<Integer>> lists;
    private int pos;
    private int listNo;

    public ZigzagIterator(List<Integer> v1, List<Integer> v2) {
        pos = 0;
        listNo = 0;
        lists = new ArrayList<>();
        lists.add(v1);
        lists.add(v2);

        // jump to the list which is not a empty list
        while ((listNo < lists.size()) && (pos >= lists.get(listNo).size())) {
            listNo ++;
            pos = 0;

        }
    }

    public int next() {
        int res = lists.get(listNo).get(pos);

        listNo ++;

        int maxCount = lists.size();
        int count = 0;

        while (((listNo >= lists.size()) || (pos >= lists.get(listNo).size())) && (count < maxCount)) {
            if (listNo >= lists.size()) {
                listNo = 0;
                pos ++;
            }
            else {
                listNo ++;
            }
            count ++;
        }
        return res;
    }

    public boolean hasNext() {
        if ((listNo < lists.size()) && (pos < lists.get(listNo).size()))
            return true;
        else
            return false;
    }

    public static void main(String[] args) {

        List<Integer> list1 = Arrays.asList(1,2);
        List<Integer> list2 = Arrays.asList(3, 4, 5, 6);

        ZigzagIterator iter = new ZigzagIterator(list1, list2);
        while (iter.hasNext()) {
            System.out.println(iter.next());
        }

    }
}
