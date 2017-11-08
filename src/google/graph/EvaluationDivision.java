package google.graph;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by yingtan on 11/5/17.
 *
 * 399. Evaluate Division
 DescriptionHintsSubmissionsDiscussSolution
 Discuss Pick One
 Equations are given in the format A / B = k, where A and B are variables represented as strings, and k is a real number (floating point number). Given some queries, return the answers. If the answer does not exist, return -1.0.

 Example:
 Given a / b = 2.0, b / c = 3.0.
 queries are: a / c = ?, b / a = ?, a / e = ?, a / a = ?, x / x = ? .
 return [6.0, 0.5, -1.0, 1.0, -1.0 ].

 The input is: vector<pair<string, string>> equations, vector<double>& values, vector<pair<string, string>> queries , where equations.size() == values.size(), and the values are positive. This represents the equations. Return vector<double>.

 According to the example above:

 equations = [ ["a", "b"], ["b", "c"] ],
 values = [2.0, 3.0],
 queries = [ ["a", "c"], ["b", "a"], ["a", "e"], ["a", "a"], ["x", "x"] ].
 */
public class EvaluationDivision {
    public double[] calcEquation(String[][] equations, double[] values, String[][] queries) {
        Map<String, Map<String, Double>> edges = new HashMap<>();
        int i = 0;
        for (String[] equation : equations) {
            String from = equation[0];
            String to = equation[1];
            double edgeValue = values[i];
            if (edges.containsKey(from)) {
                edges.get(from).put(to, edgeValue);
            }
            else {
                Map<String, Double> map = new HashMap<>();
                map.put(to, edgeValue);
                edges.put(from, map);
            }

            from = equation[1];
            to = equation[0];
            edgeValue = 1 / values[i];
            if (edges.containsKey(from)) {
                edges.get(from).put(to, edgeValue);
            }
            else {
                Map<String, Double> map = new HashMap<>();
                map.put(to, edgeValue);
                edges.put(from, map);
            }
            i ++;
        }

        double[] res = new double[queries.length];
        for (i = 0 ; i < queries.length; i ++) {
            String[] query = queries[i];
            Double d = dfs(edges, query[0], query[1], new HashSet<String>());
            if (d == null) {
                res[i] = -1;
            }
            else {
                res[i] = d.doubleValue();
            }
        }
        return res;
    }

    private Double dfs(Map<String, Map<String, Double>> edges, String start, String end, Set<String> vis) {
        if (! edges.containsKey(start) || ! edges.containsKey(end)) return new Double(-1);
        if (start.equals(end)) return new Double(1);
        if (! vis.contains(start)) {
            vis.add(start);
            Map<String, Double> neighbors = edges.get(start);
            for (Map.Entry<String, Double> neighbor : neighbors.entrySet()) {
                String nextNode = neighbor.getKey();
                double value = neighbor.getValue();
                Double nextValue = dfs(edges, nextNode, end, vis);
                if (nextValue != null) {
                    return value * nextValue.doubleValue();
                }
            }
            vis.remove(start);
        }
        return null;
    }
}
