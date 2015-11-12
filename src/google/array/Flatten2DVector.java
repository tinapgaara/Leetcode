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
*
* */
public class Flatten2DVector {

    Queue<Integer> queue;

    public Flatten2DVector(List<List<Integer>> vec2d) {
        queue = new LinkedList<>();

        for (List<Integer> list : vec2d) {
            for (Integer element : list) {
                queue.offer(element);
            }
        }
    }

    public int next() {
        return queue.poll();
    }

    public boolean hasNext() {

        return !queue.isEmpty();
    }
}

/**
 * Your Vector2D object will be instantiated and called as such:
 * Vector2D i = new Vector2D(vec2d);
 * while (i.hasNext()) v[f()] = i.next();
 */
