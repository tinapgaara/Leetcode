package bloomberg.graph;

import java.util.*;

/**
 * Created by yingtan on 11/14/15.
 */
/*
*
* 还有个event rearrangement，比如person1有ABCDEFG，person2有OPQDRSTFWUV，
让你返回一个String，共有的事件只出现一次，要保持字符串内顺序，返回任意一个，比如ABCOPQDERSTFGWUV。
* */
public class EventArrangment {

    Map<Character, List<Character>> edges = new HashMap<>();

    public String eventArrange(String[] persons) {
        buildGraph(persons);
        return topoSort();
    }

    public void buildGraph(String[] persons) {
        for (int i = 0 ; i < persons.length; i ++) {
            String str = persons[i];

            char[] chs = str.toCharArray();
            for (int j = 0 ; j < chs.length -1; j ++) {
                char ch = chs[j];
                char ch_2 = chs[j+1];
                if (edges.containsKey(ch)) {
                    List<Character> neighbors = edges.get(ch);
                    if (!neighbors.contains(ch_2)) {
                        neighbors.add(ch_2);
                    }
                }
                else {
                    List<Character> neighbors = new ArrayList<>();
                    neighbors.add(ch_2);
                    edges.put(ch, neighbors);
                }
            }
        }
    }

    public String topoSort() {
        Map<Character, Integer> visitedFlags = new HashMap<>();
        LinkedList<Character> res = new LinkedList<>();

        for (Map.Entry<Character, List<Character>> entry: edges.entrySet()) {
            Character node = entry.getKey();
            if (!visitedFlags.containsKey(node))
                DFS(node, visitedFlags, res); // do not need to judge cycle here: cycle was wrong
        }

        StringBuffer buffer = new StringBuffer();
        for (Character ch : res) {
            buffer.append(ch);
        }
        return buffer.toString();
    }

    public void DFS(Character nodeStr, Map<Character, Integer> visitedFlags, LinkedList<Character> res) {
        visitedFlags.put(nodeStr, 1);
        List<Character> neighbors = edges.get(nodeStr);
        if (neighbors != null) {
            for (Character neighbor : neighbors) {
                if (!visitedFlags.containsKey(neighbor)) {
                    DFS(neighbor, visitedFlags, res);
                }
            }
        }
        visitedFlags.put(nodeStr, 2);// black
        res.addFirst(nodeStr);
    }

    public static void main(String[] args) {
        EventArrangment ob = new EventArrangment();
        String[] arr = new String[]{"ABCDEFG", "OPQDRSTFWUV"};
        ob.eventArrange(arr);
        System.out.println("");
    }
}
