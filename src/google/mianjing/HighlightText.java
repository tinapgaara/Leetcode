package google.mianjing;


import java.util.*;

/**
 * Created by erict on 2017/11/15.
 */
/*
public class HighlightText {
    public class Rect implements Comparable {
        public int m_leftX, m_rightX, m_topY, m_bottomY;

        public int compareTo(Object obj) {
            Rect other = (Rect) obj;
            if (this.m_topY < other.m_topY) return -1;
            else if (this.m_topY == other.m_topY) return (this.m_leftX - other.m_leftX);
            else return +1;
        }
    }

    public class WordRect {
        public String m_word;
        public Rect m_rect;

        public int compareTo(Object obj) {
            WordRect other = (WordRect) obj;
            return m_word.compareTo(other.m_word);
        }
    }

    // 最简单的情况，不考虑一行因字体不同而高度不同，即假定每行的高度相同，
    //             但是考虑文本自动换行（word wrapping）的问题
    public List<Rect> highlightText_1(WordRect[] wordRects, String str2Search) {
        Arrays.sort(wordRects);

        List<Rect> rectList = new ArrayList<>();
        WordRect wRect;
        StringTokenizer st = new StringTokenizer(str2Search, " ");
        while (st.hasMoreTokens()) {
            wRect = binarySearch(wordRects, st.nextToken());
            rectList.add(wRect.m_rect);
        }

        if (rectList.isEmpty()) return rectList;
        Collections.sort(rectList);
        return combineRects(rectList);
    }

    private List<Rect> combineRects(List<Rect> rectList) {
        List<Rect> result = new ArrayList<>();
        Rect pendingRect = null;
        for (Rect curRect : rectList) {
            if (pendingRect == null) pendingRect = curRect;
            else {
                if (pendingRect.m_topY == curRect.m_topY && pendingRect.m_bottomY == curRect.m_bottomY
                    && curRect.m_leftX <= pendingRect.m_rightX + 1)
                    // 以上条件成立说明可以合并
                    pendingRect.m_rightX = Math.max(pendingRect.m_rightX, curRect.m_rightX);
                else { // 不能合并
                    result.add(pendingRect);
                    pendingRect = curRect;
                }
            }
        }
        if (pendingRect != null) result.add(pendingRect);
        return result;
    }
}
*/
