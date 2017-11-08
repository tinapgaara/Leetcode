package intervalTree;


import java.util.*;

/**
 * Created by yingt on 2017/10/26.
 */

public class RectOverlap {

    private List<Rect> m_rects;

    public RectOverlap(List<Rect> rects) {
        m_rects = rects;
    }

    private static final Comparator<Rect> xComparator = new Comparator<Rect>() {
        @Override
        public int compare(Rect arg0, Rect arg1) {
            if (arg0.m_leftX < arg1.m_leftX) return -1;
            else if (arg0.m_leftX > arg1.m_leftX) return +1;
            else return (arg0.m_rightX - arg1.m_rightX);
        }
    };

    /*
    private static final Comparator<Rect> yComparator = new Comparator<Rect>() {
        @Override
        public int compare(Rect arg0, Rect arg1) {
            if (arg0.m_leftY < arg1.m_leftY) return -1;
            else if (arg0.m_leftY > arg1.m_leftY) return +1;
            else return (arg0.m_rightY - arg1.m_rightY);
        }
    };
    //*/


    public boolean isOverlap() {
        if ((m_rects == null) || m_rects.isEmpty()) return false;

        List<Rect> rects_x = new ArrayList<>();
        rects_x.addAll(m_rects);
        Collections.sort(rects_x, xComparator);

        int minY = Integer.MAX_VALUE;
        int maxY = Integer.MIN_VALUE;
        TreeSet<Integer> xSet = new TreeSet<>();
        HashMap<Integer, List<Rect>> mapLeft = new HashMap<>();
        HashMap<Integer, List<Rect>> mapRight = new HashMap<>();
        for (Rect curRect : rects_x) {
            minY = Math.min(minY, curRect.m_rightY);
            maxY = Math.max(maxY, curRect.m_leftY);

            xSet.add(curRect.m_leftX);
            xSet.add(curRect.m_rightX);

            List<Rect> list = mapLeft.get(curRect.m_leftX);
            if (list == null) {
                list = new ArrayList<>();
                mapLeft.put(curRect.m_leftX, list);
            }
            list.add(curRect);

            list = mapRight.get(curRect.m_rightX);
            if (list == null) {
                list = new ArrayList<>();
                mapRight.put(curRect.m_rightX, list);
            }
            list.add(curRect);
        }

        IntervalTree tree = new IntervalTree(minY, maxY);

        // scan, NOTE: xSet is sorted from left to right
        boolean overlapped = false;
        for (int curX : xSet) {
            for (Rect rect : mapLeft.get(curX)) {
                overlapped = tree.add(rect);
                if (overlapped) break;
            }

            if (overlapped) break;

            for (Rect rect : mapRight.get(curX)) {
                tree.remove(rect);
            }
        }

        return overlapped;
    }
}
