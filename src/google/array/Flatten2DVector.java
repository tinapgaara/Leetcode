package google.array;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Created by yingtan on 11/10/15.
 */
/*
*Implement an iterator to flatten a 2d vector.

For example,
Given 2d vector =

[
  [1,2],
  [3],
  [4,5,6]
]
By calling next repeatedly until hasNext returns false,
the order of elements returned by next should be: [1,2,3,4,5,6].

Input:
[[],[3]]
Output:
[]
Expected:
[3]
*
* */
public class Flatten2DVector {

    private List<List<Integer>> lists;
    private int listNo;
    private int curPos;

    public Flatten2DVector(List<List<Integer>> vec2d) {
        lists = vec2d;
        listNo = 0;
        curPos = 0;
    }

    public int next() {
        int res = lists.get(listNo).get(curPos);
        curPos ++;
        return res;
    }

    public boolean hasNext() {

        // Very important here !!!!!! jump all empty list
        while ((listNo < lists.size()) && (curPos >= lists.get(listNo).size())) {
            curPos = 0;
            listNo ++;
        }
        if (listNo >= lists.size()) {
            return false;
        }
        else
            return true;
    }
}

/**
 * Your Vector2D object will be instantiated and called as such:
 * Vector2D i = new Vector2D(vec2d);
 * while (i.hasNext()) v[f()] = i.next();
 */
