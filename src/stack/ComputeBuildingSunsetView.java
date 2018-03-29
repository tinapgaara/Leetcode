package stack;

/**
 * Created by yingtan on 1/20/18.
 */
import java.util.*;
public class ComputeBuildingSunsetView {
    public Stack<Integer> sunsetView(int[] buildings) {
        Stack<Integer> stack = new Stack<>();
        for (int i = 0 ; i < buildings.length; i ++) {
            if (stack.isEmpty() ) {
                stack.push(buildings[i]);
            }
            else {
                while(! stack.isEmpty() && buildings[i] > stack.peek()) {
                    stack.pop();// this is blocke by the curent building
                }
                stack.push(buildings[i]);
            }
        }
        return stack;
    }
}
