package vmware;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class FriendCircles {

    Map<Integer, List<Integer>> mEdges = new HashMap<>();

    public static void main(String[] args) {
    	int res = 0;
    	
    	FriendCircles obj = new FriendCircles();
        
        Scanner scanner = new Scanner(System.in);
        int numNodes = Integer.parseInt(scanner.nextLine());
        if (numNodes > 0) {
        	
        	for (int row = 0; row < numNodes; row++) {
        		obj.addNode(row);
        		
                String strLine = scanner.nextLine();
                for (int column = 0; column < numNodes; column++) {
                	if (column >= row) break;  // symmetry
                	
                	if (strLine.charAt(column) == 'Y') {
                		obj.addEdge(row, column);
                		obj.addEdge(column, row);
                	}
                }
            }
            
            res = obj.calConnectedComponents(numNodes);
        }
        
        scanner.close();
        System.out.println("" + res);
    }
    
    public void addNode(int node) {
    	List<Integer> listNodes = new ArrayList<Integer>();
    	mEdges.put(node, listNodes);
    }

    public void addEdge(int node_1, int node_2) {
    	List<Integer> listNodes = mEdges.get(node_1);
    	listNodes.add(node_2);
    }

    public int calConnectedComponents(int numNodes) {
        int[] colors = new int[numNodes];
        
        int res = 0;
        for (Integer key : mEdges.keySet()) {
            int nodeIndex = key.intValue();

            if (colors[nodeIndex] == 0) {
                DFS(nodeIndex, colors);

                res++;
            }
        }
        return res;
    }

    public void DFS(int nodeIndex, int[] colors) {
        colors[nodeIndex] = 1; // grey
        List<Integer> neighbors = mEdges.get(nodeIndex);
        if (neighbors != null) {
            for (Integer neighbor : neighbors) {
                if (colors[neighbor] == 0) {
                    DFS(neighbor, colors);
                }
            }
        }
        colors[nodeIndex] = 2 ; // black;
    }
    
}
