package graph;

import java.util.List;
import java.util.Map;

/**
 * Created by yingtan on 10/7/15.
 */
/*
* DFS: focus on time
* */
public class DFS {

    Map<Node, List<Node>> graph;
    public int time;

    public void DFS() {
        time = 0;
        for (Map.Entry<Node, List<Node>> entry : graph.entrySet()) {
            if (entry.getKey().mColor == 0) {
                DFSVisit(entry.getKey());
            }
        }
    }

    public void DFSVisit(Node node) {
        time ++;
        node.mDiscover = time;
        node.mColor = 1;
        List<Node> neighbors = graph.get(node);
        for (Node neighbor : neighbors) {
            if (neighbor.mColor == 0) {
                neighbor.mPi = node;
                DFSVisit(neighbor);
            }
            else if (neighbor.mColor == 1) {
                // Has CIRCLE !!!!
            }
        }
        node.mColor = 2;
        time ++;
        node.mFinish = time;
    }

    /*  IMPORTANT !!!!
    public void DFSVisitGrid(int x, int y) {
        time ++;
        (x,y).d = time;
        if ((x >= 0 ) && (x < len) && (y >= 0) && (y < row) ) {
            if (! visitedFlag[x][y]) {
                visitedFlag[x][y] = true;
                DFSVisitGrid(x-1, y);
                DFSVisitGrid(x, y-1);
                DFSVisitGrid(x+1, y);
                DFSVisitGrid(x, y+1);
            }
            time ++;
            (x.y).f = time;
        }
    }
    */
}
