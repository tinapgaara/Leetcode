package google.DFS;

import java.util.*;

/**
 * Created by yingtan on 3/28/17.
 */
public class Matrix01 {

    public class Point {
        int x;
        int y;
        int dist;
        public Point(int x, int y, int dist) {
            this.x = x;
            this.y = y;
            this.dist = dist;
        }
    }

    public List<List<Integer>> updateMatrix(List<List<Integer>> matrix) {
        Queue<Point> queue = new LinkedList<Point>();

        int row = matrix.size();
        int col = matrix.get(0).size();
        boolean[][] vis = new boolean[row][col];
        List<List<Integer>> res = new ArrayList<>();
        for (int i = 0 ; i < row; i ++) {
            List<Integer> newchild = new ArrayList<Integer>();
            res.add(newchild);
            for (int j = 0 ; j < col; j ++) {
                newchild.add(Integer.MAX_VALUE);
                if (matrix.get(i).get(j) == 0) {

                    queue.offer(new Point(i, j, 0));
                }
            }
        }

        while (! queue.isEmpty()) {
            Point p = queue.poll();
            int x = p.x ;
            int y = p.y;
            int dist = p.dist;



            if ( (x < 0) || (x >= row) || (y < 0) || (y >= col) ) continue;
            if (vis[x][y]) continue;
            vis[x][y] = true;

            List<Integer> newchild = res.get(x);
            if (dist < newchild.get(y)) {
                newchild.set(y, dist);
            }

            queue.offer(new Point(x+1, y, dist + 1));
            queue.offer(new Point(x-1, y, dist + 1));
            queue.offer(new Point(x, y+1, dist + 1));
            queue.offer(new Point(x, y-1, dist + 1));
        }

        return res;
    }

    public static void main(String[] args) {
        Integer[][] arr = new Integer[][]{{0,0,0},{0,1,0},{0,0,0}};
        List<Integer> lst1 = Arrays.asList(0,0,0);
        List<Integer> lst2 = Arrays.asList(0,1,0);
        List<Integer> lst3 = Arrays.asList(0,0,0);
        List<List<Integer>> op = new ArrayList<>();
        op.add(lst1);
        op.add(lst2);
        op.add(lst3);

        Matrix01 ob = new Matrix01();
        ob.updateMatrix(op);
    }
}
