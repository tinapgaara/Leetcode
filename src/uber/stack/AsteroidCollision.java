package uber.stack;
import java.util.*;
public class AsteroidCollision {
    public int[] asteroidCollision(int[] asteroids) {
        // +2 -1  : merge
        // -2 +1, don't
        if (asteroids == null || asteroids.length == 0) {
            return asteroids;
        }
        Stack<Integer> stack = new Stack<>();
        stack.push(asteroids[0]);
        for (int i = 1; i < asteroids.length; i ++) {
            int cur = asteroids[i];
            boolean pushCur = true;
            while(!stack.isEmpty() && stack.peek() > 0 && cur < 0) {
                if (-1 * stack.peek() == cur) {
                    stack.pop();
                    pushCur = false;
                    break;
                }
                else if (-1 * stack.peek() < cur) {
                    // keep stack.peek
                    pushCur = false;
                    break;
                }
                else {
                    stack.pop();
                }
            }
            if (pushCur) {
                stack.push(cur);
            }
        }
        int[] res = new int[stack.size()];
        int i = res.length - 1;
        while(! stack.isEmpty()) {
            res[i] = stack.pop();
            i --;
        }
        return res;
    }
}
