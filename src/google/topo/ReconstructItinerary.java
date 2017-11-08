package google.topo;

import java.util.*;

/**
 * Created by yingtan on 8/19/17.
 *
 * http://www.cnblogs.com/EdwardLiu/p/5184961.html
 *
 * 这一道题不能用Topological sort是因为topological sort只能在DAG（A指Acyclic）里面找出一条路径，而这里只是directed graph, 环是可能存在的

 所以应该找Eulerian Path(visit each edge exactly once), 因为题目说了all tickets form at least one valid itinerary. 所以Eulerian Path一定是存在的

 所以，start from JFK, 我们用Hierholzer's algorithm来找Eulerian Path

 Eulerian Path可能不是唯一的, 比如上图有两条：JFK-D-A-C-D-B-C-JFK-A 以及 JFK-A-C-D-B-C-JFK-A

 因为题目限制了lexical order，所以找出来应该是第二条，用一个heap来实现有多个邻居选择的时候优先选择哪一个

 下面是重点：greedy的Hierholzer's algorithm

 First keep going forward until you get stuck. Put the stuck element always at the front of the result list（stuck element将会在我们最后的eulerian path的尾部，也就是最后走到这里去，因为先走到这里去会stuck）. Try if it is possible to travel to other places from the airport on the way.

 From JFK we first visit JFK -> A -> C -> D -> A. There we're stuck, so we write down A as the end of the route and retreat back to D. There we see the unused ticket to B and follow it: D -> B -> C -> JFK -> D. Then we're stuck again, retreat and write down the airports while doing so: Write down D before A, then JFK before D, the c before JFK, etc. When we're back from our cycle at D, the written route is D -> B -> C -> JFK -> D -> A. Then we retreat further along the original path, prepending C, A and finally JFK to the route, ending up with the route JFK -> A -> C -> D -> B -> C -> JFK -> D -> A.

 Since the problem asks for lexical order smallest solution, we can put the neighbors in a min-heap. In this way, we always visit the smallest possible neighbor first in our trip.

 Use LinkedList as the result type because we always add at the front of the list
 */
public class ReconstructItinerary {

    public List<String> findItinerary(String[][] tickets) {
        LinkedList<String> res = new LinkedList<String>();
        if (tickets == null || tickets.length == 0) {
            return res;
        }
        // construct graph
        Map<String, PriorityQueue<String>> edges = new HashMap<String, PriorityQueue<String>>();
        for (int i = 0 ; i < tickets.length; i ++) {
            String from = tickets[i][0];
            String to = tickets[i][1];
            if (edges.containsKey(from)) {
                edges.get(from).offer(to);
            }
            else {
                PriorityQueue<String> neighbors = new PriorityQueue<String>();
                neighbors.offer(to);

                edges.put(from, neighbors);
            }
        }

        // dfs, similar to topo, need to use linkedlist.addFirst, but topo can't tolerant cycle, here we need to count cycle, so use while loop
        dfs("JFK", edges, res);
        return res;
    }

    public void dfs(String start, Map<String, PriorityQueue<String>> edges, LinkedList<String> res) {
        while(edges.containsKey(start) && ! edges.get(start).isEmpty()) {
            String nextStart = edges.get(start).poll();
            dfs(nextStart, edges, res);
        }
        res.addFirst(start);
    }
}
