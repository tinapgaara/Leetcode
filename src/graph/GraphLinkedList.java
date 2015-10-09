package graph;

import java.util.LinkedList;

/**
 * Created by yingtan on 10/6/15.
 */
public class GraphLinkedList {

    public LinkedList<Node>[] mAdjList;

    public GraphLinkedList(int nodeNum) {
        mAdjList = new LinkedList[nodeNum];
    }
}
