package lyft.stack;
import java.util.*;
public class AsteroidCollision {
    public int[] asteroidCollision(int[] asteroids) {
        if (asteroids == null || asteroids.length == 0) return asteroids;
        Stack<Integer> stack = new Stack<>();
        stack.push(asteroids[0]);
        for (int i = 1 ; i < asteroids.length; i ++) {
            int cur = asteroids[i];
            boolean pushCur = true;
            while(! stack.isEmpty() && cur < 0 && stack.peek() > 0) {
                if (stack.peek() * -1 == cur) {
                    // collide both
                    stack.pop();
                    pushCur = false;
                    break; // important to break
                }
                else if (stack.peek()  < cur * -1) {
                    // collide peek
                    stack.pop();
                    pushCur = true;
                }
                else {
                    // collide cur
                    pushCur = false;
                    break; // important to break
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
    // follow up : how to use O(1) space ?
    public int[] asteroidCollision_constantSpace(int[] asteroids) {
        if (asteroids == null || asteroids.length == 0) return asteroids;
        int latestIndex = 0;
        for (int i = 1; i < asteroids.length; i ++) {
            if (asteroids[i] < 0) {
                boolean collideCur = false;
                while(latestIndex >= 0 && asteroids[latestIndex] > 0) {
                    if (asteroids[latestIndex] < asteroids[i] * -1) {
                        // collide prev   10 3 -5
                        latestIndex --;
                    }
                    else if (asteroids[latestIndex] > asteroids[i] * -1) {
                        // collide cur
                        collideCur = true;
                        break;
                    }
                    else {
                        // collide both
                        collideCur = true;
                        latestIndex --;
                        break;
                    }
                }
                if (! collideCur) {
                    latestIndex ++;
                    asteroids[latestIndex] = asteroids[i];
                }
            }
            else {
                latestIndex ++;
                asteroids[latestIndex] = asteroids[i];
            }
        }
        int[] res = new int[latestIndex + 1];
        for (int i = 0; i <= latestIndex; i ++) {
            res[i] = asteroids[i];
        }
        return res;
    }
    public int[] asteroidCollision2(int[] asteroids) {
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
    public static void main(String[] args) {
        AsteroidCollision ob = new AsteroidCollision();
        int[] arr = {-2, 1, 1, -1};
        ob.asteroidCollision_constantSpace(arr);
    }
}
