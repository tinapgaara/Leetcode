package vmware;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by yingtan on 10/25/15.
 */
public class GearCircle {

    public static void main(String[] args) {
        GearCircle ob = new GearCircle();
        int[] radius = new int[]{1, 3, 6, 2, 5};
        int[] cost = new int[]{5, 4, 4, 3, 4};
        int[] res = ob.Circles(8, radius, cost);
        System.out.println("h");
    }

    static int[] Circles(int distance, int[] radius, int[] cost) {
        if ((radius == null) || (radius.length == 0) || (cost == null) || (cost.length == 0))
            return null;
        int cGears = radius.length;
        int[] sortedSizes = new int[cGears];
        int extraSize, minExtraSize = Integer.MAX_VALUE;
        // Map<Integer, Integer> sizeToGearNo = new HashMap<>();
        Map<Integer, List<Integer>> sizeToGearNo = new HashMap<>();
        for (int i = 0 ; i < cGears; i ++) {
            sortedSizes[i] = radius[i];
            extraSize = distance - radius[i];
            if ((extraSize >= 0) && (minExtraSize > extraSize)) {
                minExtraSize = extraSize;
            }
            //
            List<Integer> candidates = new ArrayList<>();
            candidates.add(new Integer(i + 1));
            sizeToGearNo.put(new Integer(radius[i]), candidates);
            // sizeToGearNo.put(new Integer(radius[i]), new Integer(i + 1));
        }
        java.util.Arrays.sort(sortedSizes);
        int maxSize = sortedSizes[cGears - 1];
        int minGearNo = sizeToGearNo.get(new Integer(maxSize)).get(0); //
        int minCost = cost[minGearNo - 1];
        int size;
        for (int i = cGears - 2; i >= 0; i --) {
            size = sortedSizes[i];
            if (size < minExtraSize) {
                /*
                List<Integer> newCandidates = new ArrayList<>();
                newCandidates.add(0);

                sizeToGearNo.put(size, newCandidates); //
                */
                break;
            }
            Integer Size = new Integer(size);
            int curGearNo = sizeToGearNo.get(Size).get(0);//
            int curCost = cost[curGearNo - 1];
            if (minCost > curCost) {
                minCost = curCost;
                minGearNo = curGearNo;
            }
            else if (minCost < curCost) {
                List<Integer> newCandidates = new ArrayList<>();
                newCandidates.add(new Integer(minGearNo));

                sizeToGearNo.put(Size, newCandidates); //
                // sizeToGearNo.put(Size, new Integer(minGearNo));
            } else { //
                List<Integer> curCandidates = sizeToGearNo.get(Size);
                insertMinGear(minGearNo, curCandidates, radius);
            }
        }
        int point = cGears - 1;
        for (int i = 0 ; i < cGears; i ++) {
            extraSize = distance -  sortedSizes[i];
            if (extraSize > maxSize) {
                continue;
            }
            Integer ExtraSize = new Integer(extraSize);
            if (sizeToGearNo.get(ExtraSize) != null) {
                continue;
            }
            while ( (point >= 0) && (sortedSizes[point] >= extraSize)) {
                point --;
            }
            sizeToGearNo.put(ExtraSize, sizeToGearNo.get(new Integer(sortedSizes[point + 1])));
        }

        for (int i = 0 ; i < cGears; i ++) {
            extraSize = distance - radius[i];
            if (sizeToGearNo.get(new Integer(extraSize)) != null) {
                Integer GearNo = sizeToGearNo.get(new Integer(extraSize)).get(0);//
                if (GearNo == null) {
                    radius[i] = 0;
                } else {
                    radius[i] = GearNo.intValue();
                }
            } else {
                radius[i] = 0;
            }
        }
        return radius;
    }

    static void insertMinGear(int minGearNo, List<Integer> curCandidates, int[] radius) {
        Integer curHeadNo = curCandidates.get(0);
        if (curHeadNo != null) {
            int curHeadRadius = radius[curHeadNo.intValue()-1];
            int minGearRadius = radius[minGearNo-1];

            if (minGearRadius > curHeadRadius) {
                curCandidates.add(0, minGearNo);
            } else {
                curCandidates.add(minGearNo);
            }
        }
    }
}
