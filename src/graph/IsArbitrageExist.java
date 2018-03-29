package graph;

/**
 * Created by yingtan on 1/28/18.
 */
import java.util.*;
public class IsArbitrageExist {

    public boolean isArbitrageExist(List<List<Double>> graph) {
        for (List<Double> edge : graph) {
            for (int i = 0 ; i < edge.size(); i ++) {
                edge.set(i, -1 * Math.log10(edge.get(i)));
            }
        }
        // run bellman-ford to find negative weight cycle
        return bellmanFord(graph, 0);
    }
    public boolean bellmanFord(List<List<Double>> graph, int sum) {
    return false;
    }
}
