package graph;

import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Set;

/**
 * Created by yingtan on 9/20/15.
 */
public class Topo {

    public class Graph {
        LinkedList<Edge>[] m_adj;

        public Graph() {
            m_adj = new LinkedList[256];
        }
    }

    public class Edge {
        int m_in;
        int m_out;
        public Edge(int in, int out) {
            m_in = in;
            m_out = out;
        }
    }

    public Graph buildGraph(String[] words) {
        Graph graph = new Graph();
        int len = words.length;
        for (int i = 0 ; i < len - 1; i ++) {
            char[] word_1 = words[i].toCharArray();
            for (int j = (i + 1); j < len; j ++) {
                char[] word_2 = words[j].toCharArray();

                int minLen = Math.min(word_1.length, word_2.length);
                for (int k = 0 ; k < minLen; k ++) {
                    if (word_1[k] != word_2[k]) {
                        Edge edge = new Edge((word_1[k] - 'a'), (word_2[k] - 'a'));

                        LinkedList<Edge> edges = graph.m_adj[word_1[k] - 'a'];
                        if (edges == null) {
                            edges = new LinkedList<>();
                        }
                        edges.addLast(edge);
                        graph.m_adj[word_1[k] - 'a'] = edges;
                        break;
                    }
                }
            }
        }
        return graph;
    }

    public String topoSort(Graph graph, String[] words) {

        int[] visitedFlags = new int[256]; //0 : unvisited 1: grey  2:black(finished)
        String res = "";

        for (int i = 0 ; i < graph.m_adj.length; i ++) {
            if (graph.m_adj[i] != null) {
                if (visitedFlags[i] == 0) {
                    res = DFSVisit(graph, i, res, visitedFlags);
                }
                if (res == null) { // is cycle
                    return "";
                }
            }
        }
        res = combineIndepVertex(graph, res, words);
        return res;
    }

    public String DFSVisit(Graph graph, int nodeNo, String res, int[] visitedFlags) {
        LinkedList<Edge> edges = graph.m_adj[nodeNo];
        visitedFlags[nodeNo] = 1;
        if (edges != null) {
            for (Edge edge : edges) {
                int anotherNodeNo = edge.m_out;
                if (visitedFlags[anotherNodeNo] == 0) {
                    res = DFSVisit(graph, anotherNodeNo, res, visitedFlags);
                } else if (visitedFlags[anotherNodeNo] == 1) {// judge if cycle
                    return null;
                }
            }
        }
        if (res == null) {
            return null;
        }
        res = (char)(nodeNo + 'a') + res;
        visitedFlags[nodeNo] = 2;

        return res;
    }

    public Set<Integer> storeNodes(String[] words) {
        Set<Integer> set = new HashSet<>();
        for (String word: words) {
            char[] chs = word.toCharArray();
            for (int j = 0 ; j < chs.length; j ++) {
                if (! set.contains(chs[j]-'a')) {
                    set.add(chs[j]-'a');
                }
            }
        }
        return set;
    }

    public String combineIndepVertex(Graph graph, String res, String[] words) {
        Set<Integer> nodes  = storeNodes(words);
        LinkedList<Edge>[] edges = graph.m_adj;
        for (int i = 0 ; i < edges.length; i ++) {
            LinkedList<Edge> adj_edge = edges[i];
            if (adj_edge != null) {
                for (Edge edge: adj_edge) {
                    int in = edge.m_in;
                    int out = edge.m_out;
                    if (nodes.contains(in)) {
                        nodes.remove(in);
                    }
                    if (nodes.contains(out)) {
                        nodes.remove(out);
                    }
                }
            }
        }
        Iterator<Integer> itr = nodes.iterator();
        while (itr.hasNext()) {
            res = res + (char)(itr.next() + 'a');
        }
        return res;
    }

    public String alienOrder(String[] words) {
        Graph graph = buildGraph(words);
        return topoSort(graph, words);
    }

    public static void main(String[] args) {
        String[] words = {"zy","zx"};
        //String[] words = {"a", "b"};
        Topo ob = new Topo();
        System.out.println("result:" + ob.alienOrder(words));
    }
}
