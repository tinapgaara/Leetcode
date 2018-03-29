package graph;

/**
 * Created by yingtan on 1/28/18.
 */
import java.util.*;
public class RoadNetwork {

    public class highwaysection {
        int from;
        int to;
        int distance;
        public highwaysection(int x, int y, int distance) {
            this.from = x;
            this.to = y;
            this.distance = distance;
        }
    }
    public highwaysection findBestProposal(List<highwaysection> proposals, List<highwaysection> existing, int cityNum) {
        // Step 1. build graph for exitings, stores [i][j] : ith city distance to jth city
        int[][] graph = new int[cityNum][cityNum];
        for (highwaysection section : existing) {
            int from = section.from;
            int to = section.to;
            int dist = section.distance;
            graph[from][to] = dist;
            graph[to][from] = dist;
        }
        // Step 2. find shortest path sum between all pairs and update graph[][] : floydWarshall: Important !!!!
        // graph[i][j] = min(graph[i][j], graph[i][k] + graph[k][j])
        for (int i = 0 ; i < graph.length; i ++) {
            for (int j = 0 ; j < graph.length; j ++) {
                for (int k = 0 ; k < graph.length; k ++) {
                    if (graph[i][k] != 0 && graph[k][j] != 0) {
                        graph[i][j] = Math.min(graph[i][j], graph[i][k] + graph[k][j]);
                    }
                }
            }
        }
        int maxSaving = 0;
        highwaysection best = null;
        // Step 3. len(i, j) compares with len(i, k.from) + p(k.from, k.to) + len(k.to, j)
        for (highwaysection p : proposals) {
            int pFrom = p.from;
            int pTo = p.to;
            int saving = 0;
            for (int from = 0 ; from < cityNum; from ++) {
                for (int to = 0 ; to < cityNum; to ++) {
                    int orignalMinDist = graph[from][to];
                    int distWithProposal = graph[from][pFrom] + p.distance + graph[pTo][to];
                    if (distWithProposal < orignalMinDist) {
                        // good one
                        saving = saving + orignalMinDist - distWithProposal;
                    }
                }
            }
            if (saving > maxSaving) {
                maxSaving = saving;
                best = p;
            }
        }
        return best;
    }
}
