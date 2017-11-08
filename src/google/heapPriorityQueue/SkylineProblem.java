package google.heapPriorityQueue;

import java.util.*;

/**
 * Created by yingtan on 12/22/15.
 */
public class SkylineProblem {

    /*
    There are only two cases which will generate overlap points:
    case 1: left point [3,15] is pushed, and max heap's height changed from [2,10']'s 10 to [3,15]'s 15
    case 2: right point [7,15] is removed, and max heap's height changed from [3,15]'s 15 to [5,12]'s 12

    genreal idea:
    everytime
         when left side point coming in, push to maxheap
         when right side point coming in, pop from maxheap
    then, see if the maxheap's top changed,
         if changed: this is overlap point
    */
    public List<int[]> getSkyline(int[][] buildings) {
        List<int[]> res = new ArrayList<>();
        if (buildings == null || buildings.length == 0) {
            return res;
        }

        List<int[]> height = new ArrayList<>();

        for (int[] b : buildings) {
            int leftPoint = b[0];
            int rightPoint = b[1];
            int h = b[2];
            /*
            make left side point neg
            make right side point pos
            so we can differentiate the left side point and right side point, because we need to push left point and pop right pop from heap :
            When left side point is pushed, this may genreate the overlap point.
            When right side point is removed, this may also genreate the overlap point.
            */
            height.add(new int[]{leftPoint, -1 * h});
            height.add(new int[]{rightPoint, h});
        }

        // smaller one is at front
        Collections.sort(height, (a, b) -> {
            if (a[0] != b[0]) return a[0]-b[0];
            else return a[1]-b[1];
        });

        // larger one is at front, maxheap.
        Queue<Integer> heightQueue = new PriorityQueue<Integer>((a, b) -> (b - a));
        heightQueue.offer(0);
        int prevTop = 0;
        for (int[] h : height) {
            int index = h[0];
            int hg = h[1];
            // if this is left side, push this to max heap
            if (hg < 0) heightQueue.offer(-1 * hg);
                // if this is a right side, this is already here, remove this from max heap
            else heightQueue.remove(hg);
            // the current top is the max element in the heap
            int curTop = heightQueue.peek();

            /*
            the prev stores previous top element in maxheap(largest height). if peek == prev then this means the new added does not change the largest number in queue thus this height is not largest one, in this case, we do not need to store this. If the peek != prev, this means max heap's top is changed, so there must be an overlap point.
            if the peek != prev, then, this means, the top element in max heap is changed such as from red point [3,15] to [7, 12] when point (7,15) is removed, so we need to store this. Two cases:
            eg1: the overlap of [3,15] and [7, 12] is need to record because, when [7,15](right side) is removed, the heap's top changes from 15->12, so this is the point we want to record
            eg2: the overlap of [2,10] and [3,15] is need to record because, when [3,15](left side) is pushed, the heap's top changes from 12->15, so this is the point we want to record.
            */
            // important !!  != in order to show from [3,15] to [7,12]
            if (curTop != prevTop) {
                res.add(new int[]{index, curTop});
                prevTop = curTop;
            }
        }
        return res;
    }
}
