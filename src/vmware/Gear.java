package vmware;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Gear {

	public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
		
		try {

			Gear ob = new Gear();
			int[] radius = new int[]{1, 2, 3, 4, 5};
			int[] cost = new int[]{2, 0, 4, 2, 4};
			int[] res = ob.Circles(3, radius, cost);
			Scanner in = new Scanner(System.in);
			String strLine1 = in.nextLine();
			String strLine2 = in.nextLine();
			String strLine3 = in.nextLine();
			in.close();

			/*
			String strLine1 = "5 10";
			String strLine2 = "1 3 6 2 5";
			String strLine3 = "5 6 8 3 4";

			String strLine1 = "5 8";
			String strLine2 = "1 3 6 2 5";
			String strLine3 = "5 6 8 3 4";
			//*/
			
			int[] result = null; //(new Gear()).Circles();
			if (result == null)
			    System.out.println("NULL");
			else {
				for (int i = 0; i < result.length; i++) {
			        System.out.print("" + result[i] + " ");
				}
		        System.out.println("");
			}
		}
		catch (NumberFormatException ex) {
			System.out.println("NumberFormatException: " + ex.getMessage());
		}
		/*
		catch (RuntimeException ex) {
			System.out.println("RuntimeException: " + ex.getMessage());
		}
		//*/
	}

	static int[] Circles(int distance, int[] radius, int[] costs) {

		if ( (radius == null) || (radius.length == 0) ||
				(costs == null) || (costs.length == 0) ) {
			return null;
		}

		int cGears = radius.length;

		int[] sortedSizes = new int[cGears];
		Map<Integer, Integer> sizeToGearNo = new HashMap<Integer, Integer>();
		int size;
		Integer Size;
		for (int i = 0; i < cGears; i++) {
			size = radius[i];
			sortedSizes[i] = size;

			Size = new Integer(size);
			Integer GearNo = sizeToGearNo.get(Size);
			if (GearNo == null) {
				sizeToGearNo.put(Size, new Integer(i + 1));
			}
			else {
				if (costs[i] < costs[GearNo.intValue() - 1]) {
					sizeToGearNo.put(Size, new Integer(i + 1));
				}
			}
		}

		java.util.Arrays.sort(sortedSizes);
		int maxSize = sortedSizes[cGears - 1];
		int minExtraSize = distance - maxSize;
		int minGearNo = sizeToGearNo.get(new Integer(maxSize));
		int minCost = costs[minGearNo - 1];
		for (int i = cGears - 2; i >= 0; i--) {
			size = sortedSizes[i];
			if (size < minExtraSize) {
				break;
			}

			Size = new Integer(size);
			int curGearNo = sizeToGearNo.get(Size);
			int curCost = costs[curGearNo - 1];
			if (minCost > curCost) {
				minCost = curCost;
				minGearNo = curGearNo;
			}
			else if (minCost <= curCost) {
				sizeToGearNo.put(Size, new Integer(minGearNo));
			}
		}

		int point = cGears - 1;
		int extraSize;
		for (int i = 0; i < cGears; i++) {
			extraSize = distance - sortedSizes[i];
			if (extraSize > maxSize) {
				continue;
			}
			Integer ExtraSize = new Integer(extraSize);
			if (sizeToGearNo.get(ExtraSize) != null) {
				continue;
			}

			while ( (point >= 0) && (sortedSizes[point] >= extraSize) ) {
				point--;
			}
			sizeToGearNo.put(ExtraSize, sizeToGearNo.get(new Integer(sortedSizes[point + 1])));
		}

		// from now on, we use the array sortedSizes as result
		for (int i = 0; i < cGears; i++) {
			extraSize = distance - radius[i];
			if (extraSize <= 0) {
				sortedSizes[i] = 0;
			}
			else {
				Integer GearNo = sizeToGearNo.get(new Integer(extraSize));
				if (GearNo == null) {
					sortedSizes[i] = 0;
				}
				else {
					sortedSizes[i] = GearNo.intValue();
				}
			}
		}

		sizeToGearNo.clear();

		return sortedSizes;
	}


}
