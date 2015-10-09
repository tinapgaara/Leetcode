package graph;

import java.util.List;

/**
 * Created by yingtan on 10/6/15.
 */
public class Node {

    int mVal;
    int mDist;
    Node mPi;
    int mColor; //0: white   1:grey   2:black

    int mDiscover;
    int mFinish;

    public Node(int val, int dist, Node pi) {
        mVal = val;
        mDist = dist;
        mPi = pi;
    }

    public Node(int val, int dist, Node pi, int color) {
        mVal = val;
        mDist = dist;
        mPi = pi;
        mColor = color;
    }

    public Node(int val, int discover, int finish, Node pi, int color) {
        mVal = val;
        mDiscover = discover;
        mFinish = finish;
        mPi = pi;
        mColor = color;
    }
}
