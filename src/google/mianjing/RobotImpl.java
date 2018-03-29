package google.mianjing;

import java.util.List;

/**
 * Created by erict on 2017/11/9.
 */
/*
public class RobotImpl implements Robot {
    private Env m_env;
    private Grid m_curGrid;
    private int m_curDir;
    private int m_cleaned[][];
    private int m_cleanedGridNum;

    private static final int UP = 0;
    private static final int LEFT = 1;
    private static final int DOWN = 2;
    private static final int RIGHT = 3;

    public RobotImpl(Env env, int initX, int initY) {
        m_curGrid = new Grid(initX, initY);
        m_curDir = UP;
        m_cleaned = new int[env.getColumnNum()][env.getRowNum()];
        m_cleanedGridNum = 0;
    }

    public boolean move() {
        // returns true if next cell is open and robot moves into the cell.
        // returns false if next cell is obstacle and robot stays on the current cell.
        Grid nextGrid = getNextGrid(m_curGrid, m_curDir);
        if (m_env.isOpen(nextGrid)) {
            m_curGrid = nextGrid;
            return true;
        }
        return false;
    }

    // Robot will stay on the same cell after calling Turn*. k indicates how
    // many turns to perform.
    public void turnLeft(int k) {
        m_curDir = (m_curDir + k) % 4;
    }

    public void turnRight(int k) {
        int turns = k % 4;
        m_curDir = (m_curDir + (4 - turns)) % 4;
    }


    // Clean the current cell.
    public void clean() {
        int curX = m_curGrid.m_x;
        int curY = m_curGrid.m_y;
        if (m_cleaned[curX][curY] == 0) {
            m_cleaned[curX][curY] = 1;
            m_cleanedGridNum++;
        }
    }

    public boolean cleanAllGrids() {
        GridGraph graph = buildGraph(env);
        // 机器人周围都清理完了,但是还有一些cell在别的地方 还没清理
        boolean done = false;
        while ( ! done ) {
            List<Grid> path = findNearGridAndPath(graph);
            if (path == null)
                done = true;
            else
                traverse(path);
        }
        return (m_env.getOpenGridNum() == m_cleanedGridNum);
    }

    private List<Grid> findNearGridAndPath(GridGraph graph) {
        List<Grid> path = null;

        int rowNum = m_env.getRowNum();
        int columnNum = m_env.getColumnNum();

        int delta = 0;
        int maxDeltaX = Math.max(m_curGrid.m_x, columnNum - m_curGrid.m_x);
        int maxDeltaY = Math.max(m_curGrid.m_y, rowNum - m_curGrid.m_y);
        int maxDelta = Math.max(maxDeltaX, maxDeltaY);

        boolean pathFound = false;
        while ( ! pathFound ) {
            delta++;
            if (delta > maxDelta) break;
            int fromRow = Math.max(m_curGrid.m_y - delta, 0);
            int toRow = Math.min(m_curGrid.m_y + delta, rowNum - 1);
            int fromColumn = Math.max(m_curGrid.m_x - delta, 0);
            int toColumn = Math.min(m_curGrid.m_x + delta, columnNum - 1);
            for (int x = fromColumn; x <= toColumn; x++) {
                for (int y = fromRow; y <= toRow; y++) {
                    if (m_cleaned[x][y] == 0) {
                        path = graph.findShortestPath(m_curGrid, new Grid(x, y));
                        if (path != null) {
                            pathFound = true;
                            break;
                        }
                    }
                }
                if (path != null) break;
            }
        }
        return path;
    }

    private void traverse(List<Grid> path) {
        for (Grid grid: path) {
            int k = calcTurns(m_curGrid, grid, m_curDir);
            if (k > 0) turnLeft(k);// 向左转多少个90度
            else if (k < 0) turnRight(-k);//向右转多少个90度
            boolean moved = move();
            assert moved;// must succeed
            clean();
        }
    }

    private int calcTurns(Grid curGrid, Grid nextGrid, int curDir) {
        int turns = 0;
        if (nextGrid.m_y == curGrid.m_y - 1)  // UP
            turns = 4 - curDir;
            if (turns >= 3) turns -= 4;
        else if (nextGrid.m_x == curGrid.m_x - 1)  // LEFT
            turns = 4 - curDir - 3;
        else if (nextGrid.m_y == curGrid.m_y + 1)  // DOWN
            turns = 4 - curDir - 2;
        else if (nextGrid.m_x == curGrid.m_x + 1)  // RIGHT
            turns = 4 - curDir - 1;
            if (turns >= 3) turns -= 4;
        else
            assert false;  // impossible !!!
        return turns;
    }

    private Grid getNextGrid(Grid curGrid, int dir) {
        Grid next;
        switch (dir) {
            case LEFT:
                next = new Grid(curGrid.m_x - 1, curGrid.m_y);
                break;
            case RIGHT:
                next = new Grid(curGrid.m_x + 1, curGrid.m_y);
                break;
            case UP:
                next = new Grid(curGrid.m_x, curGrid.m_y - 1);
                break;
            case DOWN:
                next = new Grid(curGrid.m_x, curGrid.m_y + 1);
                break;
            default:
                assert(false);
                next = curGrid;
        }
        return next;
    }
}
*/
