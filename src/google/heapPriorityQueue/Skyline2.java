package yt.leetCode.p218.skyline;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class Skyline2 {

	public List<int[]> getSkyline(int[][] buildings) {
        int n = buildings.length;
        if (n == 0) {
            return new ArrayList<int[]>();
        }

        Building[] bld = new Building[n];

        for(int i = 0; i<n; i++){
            bld[i] = new Building(buildings[i][0], buildings[i][2], buildings[i][1]);
        }

        return skyLine(bld);
    }

    public static class Building{
        int l;
        int h;
        int r;

        public Building(int left, int height, int right){
            l = left;
            h = height;
            r = right;
        }
    }

    public static ArrayList<int[]> skyLine(Building[] buildings){
        int n = buildings.length;
        Building[] start = Arrays.copyOf(buildings, n);
        Building[] end = Arrays.copyOf(buildings, n);

        // sort based on left coordinate of a building i.e. start of a range
        Arrays.sort(start, new Comparator<Building>() {
            @Override
            public int compare(Building o1, Building o2) {
                int c = Integer.compare(o1.l, o2.l);
                if(c == 0){
                    c = Integer.compare(o2.h, o1.h);
                }

                return c;
            }
        });

        // sort based on right coordinate of a building i.e. end of a range
        Arrays.sort(end, new Comparator<Building>() {
            @Override
            public int compare(Building o1, Building o2) {
                return Integer.compare(o1.r, o2.r);
            }
        });

        ArrayList<int[]> strips = new ArrayList<int[]>();
        PriorityQueue<Integer> maxHeap = new PriorityQueue<Integer>(n, Collections.reverseOrder());
        int i = 0, j  = 0;
        while (i < n || j < n) {
            // a new overlapping range i.e. a building
            if (i < n && start[i].l <= end[j].r) {
                // update max height seen so far in current overlap
                maxHeap.add(start[i].h);
                // max height in current overlap including the current building
                int maxHeightIncldingMe = maxHeap.peek();
                // add the current strip with the left of building and max height seen so far in current overlap
                strips.add(new int[]{start[i].l, maxHeightIncldingMe});
                // try next building
                i++;
            }
            else {
                // it's an end of a range of current overlap. So, we need to remove the height
                // of this range i.e. building from the max heap
                maxHeap.remove(end[j].h);
                // max height of remaining buildings in current overlap
                int maxHeightExcldingMe = maxHeap.isEmpty() ? 0 : maxHeap.peek();
                // add the current strip with the right of building and max height of remaining buildings
                strips.add(new int[]{end[j].r, maxHeightExcldingMe});
                // update end index
                j++;
            }
        }

        // merge strips to remove successive strips with same height
        ArrayList<int[]> mergedStrips = new ArrayList<int[]>();
        int prevHeight = 0;
        for (int[] strip : strips) {
        	
        	// deal with this kind of cases:
        	//     input:        [[2,4,7],[2,4,5],[2,4,6]]
        	//     output:       [[2,7],[4,6],[4,0]]
        	//     but expected: [[2,7],[4,0]]
            if (strip[0] == end[n-1].r && strip[1] != 0) {
                continue;
            }
            
            if (prevHeight == 0) {
                prevHeight = strip[1];
                mergedStrips.add(strip);
            }
            else if (prevHeight != strip[1]) {
                prevHeight = strip[1];
                mergedStrips.add(strip);
            }
        }
        
        return mergedStrips;
    }
}

