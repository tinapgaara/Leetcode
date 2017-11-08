package google.DFS;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by yingtan on 2/25/17.
 */
public class GraphValidTree {

    public class Node {
        List<Node> children;
        int color;
        int val;

        public Node(int val) {
            this.val = val;
            this.children = new ArrayList<Node>();
            this.color = 0; // 0 : unvisited ,  1: not finished,  2: visited
        }
    }

    public boolean validTree(int n, int[][] edges) {
        if ( (edges == null) || (edges.length == 0) ) {
            if (n == 1) return true;
            else return false;
        }
        HashMap<Integer, Node> nodes = new HashMap<Integer, Node>();
        for (int i = 0 ; i < n ; i ++) {
            Node newNode = new Node(i);
            nodes.put(i, newNode);
        }
        for (int i = 0 ; i < edges.length; i ++) {
            int start = edges[i][0];
            int end = edges[i][1];
            Node startNode = nodes.get(start);
            Node endNode = nodes.get(end);
            startNode.children.add(endNode);
            endNode.children.add(startNode);
        }
        int components = 0;

        // important !!! if edge(i, j) has been visited
        /*
        * Since we change this undirected graph to a directed graph(becasue boundary case: [2,0][2,1])
        * to judge the circle, we can not just use node's color ==1.
        * Sometimes, color==1, but we just start from that node and can not say this is a circle,
        * we need an extra memory to record if a edge from node i to node j has been visited
        * to judge if there is a circle
        *
        * pattern:
        * if (edge(i,j) has not been visited, but node j == 0), which mean this is a circle edge, and return false
        *
        * */
        boolean[][] visEdge = new boolean[n][n];

        for (int i = 0 ; i < n ; i ++) {
            Node node = nodes.get(i);
            if (node.color == 0) {
                if (dfs(node, visEdge))
                    components ++;
                else {
                    return false;
                }

            }
        }
        if (components == 1) return true;
        else return false;
    }

    public boolean dfs(Node node, boolean[][] visEdge) {
        node.color = 1;

        for (Node child : node.children) {
            if (child.color == 0) {
                visEdge[node.val][child.val] = true;
                visEdge[child.val][node.val] = true;
                if (! dfs(child, visEdge)) return false;
            }
            else if ( (child.color == 1) && ( ! visEdge[node.val][child.val]) ) {
                return false; // has circle
            }
        }
        node.color = 2;
        return true;
    }

    public static void main(String[] args) {
        GraphValidTree ob = new GraphValidTree();
        int[][] edges = new int[][]{{1, 0}};
        ob.validTree(2, edges);
    }
}
