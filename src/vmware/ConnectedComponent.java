package vmware;

import java.util.*;

public class ConnectedComponent {

    static Map<Integer, List<Integer>> edges = new HashMap<>();

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        int numPoints = Integer.parseInt(scanner.nextLine());
        int numEdges = Integer.parseInt(scanner.nextLine());

        // int numPoints = 4;
    	// int numEdges = 2;

        ConnectedComponent obj = new ConnectedComponent();
        for (int i = 1; i <= numPoints; i++) {
            obj.createNodes(i);
        }

        for (int i = 0 ; i < numEdges; i ++) {
            String line = scanner.nextLine();
            String[] strs  = line.split(" ");
            int nodeNo_1 = Integer.parseInt(strs[0]);
            int nodeNo_2 = Integer.parseInt(strs[1]);

            obj.createEdge(nodeNo_1, nodeNo_2);
            obj.createEdge(nodeNo_2, nodeNo_1);
        }
        //*/
        // obj.createEdge(1, 2);
        // obj.createEdge(3, 2);

        scanner.close();

        int res = obj.calConnectComponent(numPoints);
        System.out.println(res);
    }

    static int calConnectComponent(int numPoints) {
        int[] colors = new int[numPoints + 1];
        
        long res = 0;
        // List<Integer> component = new ArrayList<>();
        long[] num = new long[1];
        for (Map.Entry<Integer, List<Integer>> entry: edges.entrySet()) {
            int nodeNo = entry.getKey();

            if (colors[nodeNo] == 0) {
                //DFS(nodeNo, colors, num);
                BFS(nodeNo, colors, num);
                res = (int) (res + Math.ceil(Math.sqrt(num[0])));
                /*
                System.out.println(component+ ", "+ res);
                //*/
                num[0] = 0;
                
                // component.clear();
            }
        }
        return (int)res;
    }

    static void BFS(int nodeNo, int[] colors, long[] compSize) {
        colors[nodeNo] = 1; // grey

        Queue<Integer> queue = new LinkedList<Integer>();
        queue.offer(nodeNo);

        while ( ! queue.isEmpty() ) {
            int curNode = queue.remove();
            List<Integer> neighbors = edges.get(curNode);
            compSize[0] = compSize[0] + 1;
            if (neighbors != null) {
                for (Integer neighbor : neighbors) {
                    if (colors[neighbor] == 0) {
                        colors[neighbor] = 1; // grey
                        queue.offer(neighbor);
                    }
                }
            }
            colors[curNode] = 2 ; // black;
        }
    }

    static void DFS(int nodeNo, int[] colors, long[] num) {
        colors[nodeNo] = 1; // grey
        List<Integer> neighbors = edges.get(nodeNo);
        num[0] ++;
        if (neighbors != null) {
            for (Integer neighbor : neighbors) {
                if (colors[neighbor] == 0) {
                    DFS(neighbor, colors, num);
                }
            }
        }
        colors[nodeNo] = 2 ; // black;
    }

    static void createEdge(int nodeNo_1, int nodeNo_2) {
        if (edges.containsKey(nodeNo_1)) {
            edges.get(nodeNo_1).add(nodeNo_2);
        }
        if (edges.containsKey(nodeNo_2)) {
            edges.get(nodeNo_2).add(nodeNo_1);
        }
    }

    static void createNodes(int node) {
        List<Integer> nodes = new ArrayList<>();
        edges.put(node, nodes);
    }
}
