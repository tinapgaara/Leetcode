package vmware;

import javafx.util.Pair;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by yingtan on 10/28/15.
 */
/*
*Implement the “paint fill” function that one might see on many image editing programs. That is,
* given a screen (represented by a 2-dimensional array of Colors), a point, and a new color,
* fill in the surrounding area until you hit a border of that color.
*
*
* 实现图像处理软件中的“填充”函数，给定一块区域(可以不规则)，一个种子点和一种新颜色， 填充这块区域，直到到达这块区域的边界(边界是用这种新颜色画的一条封闭曲线)
* */
public class PaintFill {

    // Solution 1: DFS
    public void paintFill_DFS(int[][] screen, int m, int n, int x, int y, int color) { // m: x boundary; n: y boundary;  (x,y): start point
        if ((x < 0) || (x >= m) || (y < 0) || (y >= n)) return;
        if (screen[x][y] == color) return; // Important !!!!  similar to visitedFlag
        else {
            screen[x][y] = color;
            paintFill_DFS(screen, m, n, x - 1, y, color);
            paintFill_DFS(screen, m, n, x, y - 1, color);
            paintFill_DFS(screen, m, n, x + 1, y, color);
            paintFill_DFS(screen, m, n, x, y + 1, color);
        }
    }

    // Solution 2: BFS
    public void paintFill_BFS(int[][] screen, int m, int n, int x, int y, int color) {
        Queue<Pair<Integer, Integer>> queue = new LinkedList<>();
        queue.offer(new Pair<>(x, y));
        while(!queue.isEmpty()) {
            if ((x >= 0) && (x < m) && (y >= 0) && (y < n)) {
                if (screen[x][y] != color) {
                    screen[x][y] = color;
                    queue.offer(new Pair<>(x-1,y));
                    queue.offer(new Pair<>(x,y-1));
                    queue.offer(new Pair<>(x+1,y));
                    queue.offer(new Pair<>(x,y+1));
                }
            }
        }
    }

    public static void main(String[] args) {

    }

}
