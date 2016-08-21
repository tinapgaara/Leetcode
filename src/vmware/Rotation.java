package vmware;

import java.util.*;

public class Rotation {
	static Map<Integer, List<Integer>> edges = new HashMap<Integer, List<Integer>>();

	public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
		
		try {
			Scanner in = new Scanner(System.in);
			String strLine1 = in.nextLine();
			String strLine2 = in.nextLine();
			in.close();
			
			long result = (new Rotation()).rotation(strLine1, strLine2);
			
			System.out.println("" + result);
			/*
			final String fileName = System.getenv("OUTPUT_PATH");
			BufferedWriter bw = new BufferedWriter(new FileWriter(fileName));
			int result = ...;
			bw.write(String.valueOf(result));
			bw.newLine();
			bw.close();
			//*/
		}
		/*
		catch (IOException ex) {
			System.out.println("IOException: " + ex.getMessage());
		}
		//*/
		catch (NumberFormatException ex) {
			System.out.println("NumberFormatException: " + ex.getMessage());
		}
    }
	
	private long rotation(String strLine1, String strLine2) throws NumberFormatException {
		
		if ( (strLine1 == null) || strLine1.isEmpty() ||
				(strLine2 == null) || strLine2.isEmpty() ) {
			return 0;
		}
		
		long result = 0;
		
		int cNums = Integer.parseInt(strLine1.trim());
		if (cNums > 0) {
			int[] nums = new int[cNums];
			strLine2.trim();
			String[] numStrings = strLine2.split(" ");
			
			int num;
			long sum = 0, sum2 = 0;
			for (int i = 0; i < cNums; i++) {
				num = Integer.parseInt(numStrings[i]);
				nums[i] = num;
				sum += num;
				sum2 += ( (long) (i + 1) ) * num;
			}
			
			result = sum2;
			for (int r = 1; r < cNums; r++) {
				sum2 = sum2 - sum + ( ( (long) cNums ) * nums[r - 1]);
				if (result < sum2) result = sum2;
			}
		}
		
		return result;
	}

	static int MinimalCost(int n, String[] pairs) {
		if ((pairs == null) || (pairs.length == 0)) return 0;

		for (int i = 1; i <= n; i ++) {
			createNode(i);
		}

		for (int i = 0 ; i < pairs.length; i ++) {
			String[] strs = pairs[i].split(" ");
			int nodeNo_1 = Integer.parseInt(strs[0]);
			int nodeNo_2 = Integer.parseInt(strs[1]);
			createEdge(nodeNo_1, nodeNo_2);
		}
		return calConnectedComponent(n);
	}

	static void createNode(int node) {
		List<Integer> nodes = new ArrayList<Integer>();
		edges.put(node, nodes);
	}

	static void createEdge(int nodeNo_1, int nodeNo_2) {
		if (edges.containsKey(nodeNo_1)) {
			edges.get(nodeNo_1).add(nodeNo_2);
		}
		if (edges.containsKey(nodeNo_2)) {
			edges.get(nodeNo_2).add(nodeNo_1);
		}
	}

	static int calConnectedComponent(int numPoints) {
		int[] colors = new int[numPoints + 1];
		int res = 0;
		List<Integer> component = new ArrayList<Integer>();
		for (Map.Entry<Integer, List<Integer>> entry: edges.entrySet()) {
			int nodeNo = entry.getKey();
			if (colors[nodeNo] == 0) {
				DFS(nodeNo, colors, component);
				res = (int)(res  + Math.ceil(Math.sqrt(component.size())));
				component.clear();
			}
		}
		return res;
	}

	static void DFS(int nodeNo, int[] colors, List<Integer> components) {
		colors[nodeNo] = 1;
		List<Integer> neighbors = edges.get(nodeNo);
		components.add(nodeNo);
		if (neighbors != null) {
			for (Integer neighbor: neighbors) {
				if (colors[neighbor] == 0) {
					DFS(neighbor, colors, components);
				}
			}
		}
		colors[nodeNo] = 2;
	}
}
