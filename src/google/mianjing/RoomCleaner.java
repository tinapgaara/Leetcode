package google.mianjing;

import java.util.*;

/**
 * Created by erict on 2017/11/14.
 */
/*
public class RoomCleaner {
    public final static int DIR_up = 0;
    public final static int DIR_left = 1;
    public final static int DIR_down = 2;
    public final static int DIR_right = 3;
    private final static int[] ALL_dirs = new int[] { DIR_up, DIR_left, DIR_down, DIR_right};
    private final static int[] DELTA_x = new int[] { 0, -1, 0, 1};
    private final static int[] DELTA_y = new int[] { -1, 0, 1, 0};
    private final static int[][] TURN = new int[][]{
            {0, 1, 2, -1},  // based on DIR_up
            {-1, 0, 1, 2},  // based on DIR_left
            {2, -1, 0, 1},  // based on DIR_down
            {1, 2, -1, 0}   // based on DIR_right
    };
    public class Position {
        public int m_x, m_y;

        public Position(int x, int y) {
            m_x = x;
            m_y = y;
        }
        @Override
        public boolean equals(Object obj){
            if (this == obj)
                return true;

            Position other = (Position) obj;
            return (this.m_x == other.m_x && this.m_y == other.m_y);
        }
        public Position[] getAllNeighbors() {
            Position[] neighbors = new Position[4];  // num of all directions
            int index = 0;
            for (int dir: ALL_dirs) {
                neighbors[dir] = new Position(m_x + DELTA_x[dir], m_y + DELTA_y[dir]);
            }
            return neighbors;
        }
    }
    public class Cell {
        public Position m_pos;
        public int m_blocked;  // 0: unknown, 1: empty, -1: blocked
        public Set<Cell> m_reachable;

        public Cell(Position pos) {
            m_pos = pos;
            m_blocked = 0;  // unknown
            m_reachable = new HashSet<>();
        }
    }

    private Robot m_robot;
    private Position m_curPos;
    private int m_curDir;
    Map<Position, Cell> m_cleanCells = new HashMap<>();
    Map<Position, Cell> m_blockCells = new HashMap<>();

    public int cleanAllCells() throws Exception {
        // start from (0, 0)
        m_curPos = new Position(0, 0);
        m_curDir = DIR_up;
        m_robot.clean();
        Cell curCell = new Cell(m_curPos);
        m_cleanCells.put(m_curPos, curCell);

        boolean done = false;
        List<Cell> path;
        while (! done) {
            path = findNextPosToExploit();
            if (path == null) {
                done = true;
            }
            else {
                Cell dstCell = path.get(path.size() - 1);
                if (moveAlongPath(path)) {
                    m_robot.clean();

                    dstCell.m_blocked = 1;  // open
                    m_cleanCells.put(dstCell.m_pos, dstCell);

                    Position[] neighbors = dstCell.m_pos.getAllNeighbors();
                    for (Position ne : neighbors) {
                        Cell neCell = m_cleanCells.get(ne);
                        if (neCell != null) {
                            neCell.m_reachable.add(dstCell);
                            dstCell.m_reachable.add(neCell);
                        }
                    }
                }
                else {
                    dstCell.m_blocked = -1;  // blocked
                    m_blockCells.put(dstCell.m_pos, dstCell);
                }
            }
        }
        if (m_cleanCells.isEmpty()) return 0;
        else return m_cleanCells.keySet().size();
    }

    private boolean moveAlongPath(List<Cell> path) throws Exception {
        for (Cell cell : path) {
            if (! moveTo(cell.m_pos))
                return false;
        }
        return true;
    }

    private boolean moveTo(Position dst) throws Exception {
       int orient = calcOrient(m_curPos, dst);
       int turn = TURN[m_curDir][orient];
       if (turn > 0) {
           m_robot.turnLeft(turn);
           m_curDir = orient;
       }
       else if (turn < 0) {
           m_robot.turnRight(-turn);
           m_curDir = orient;
       }
       if (m_robot.move()) {
           m_curPos = dst;
           return true;
       }
       return false;
    }

    private int calcOrient(Position fromPos, Position toPos) throws Exception {
        if (toPos.m_x == fromPos.m_x - 1) return DIR_left;
        if (toPos.m_x == fromPos.m_x + 1) return DIR_right;
        if (toPos.m_y == fromPos.m_y - 1) return DIR_up;
        if (toPos.m_y == fromPos.m_y + 1) return DIR_down;
        throw new Exception("......");  // impossible here !!!
    }

    private List<Cell> findNextPosToExploit() {
        List<Cell> path = null;
        int xMin = m_curPos.m_x, xMax = m_curPos.m_x, yMin = m_curPos.m_y, yMax = m_curPos.m_y;
        int exLeft, exRight, exTop, exBottom;
        while (path == null) {
            exLeft = xMin; exRight = xMax;
            exTop = yMin; exBottom = yMax;
            for (int x = exLeft - 1; x <= exRight + 1; x++) {
                for (int y = exTop - 1; y <= exBottom + 1; y++) {
                    Position pos = new Position(x, y);
                    Cell cell;
                    cell = m_blockCells.get(pos);
                    if (cell != null) continue;
                    cell = m_cleanCells.get(pos);
                    if (cell == null) {
                        Position[] neighbors = pos.getAllNeighbors();
                        for (Position ne : neighbors) {
                            Cell neighborCell = m_cleanCells.get(ne);
                            if (neighborCell == null) continue;
                            path = findShortestPath(m_cleanCells.get(m_curPos), neighborCell);
                            if (path == null) continue;
                            else {
                                path.add(new Cell(pos));
                                break;
                            }
                        }
                    }
                    else {
                        if (pos.m_x < xMin) xMin = pos.m_x;
                        if (pos.m_x > xMax) xMax = pos.m_x;
                        if (pos.m_y < yMin) yMin = pos.m_y;
                        if (pos.m_y > yMax) yMax = pos.m_y;
                    }
                    if (path != null) break;
                }
                if (path != null) break;
            }
            if (path == null) {
                if (xMin >= exLeft && xMax <= exRight && yMin >= exTop && yMax <= exBottom)
                    break;  // impossible to find any new cell to exploit
            }
        }
        return path;
    }

    private List<Cell> findShortestPath(Cell from, Cell to) {

    }
}
*/
