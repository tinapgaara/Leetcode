package bloomberg.median;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by yingtan on 11/16/15.
 */
public class GlobalMedian {

    public static void main(String[] args) {

        Node node;
        List<Node> nodeList = new ArrayList<Node>();
        node = new GlobalMedian.Node(new int[]{1, 3, 5, 7, 10, 13, 16, 18});
        nodeList.add(node);
        node = new GlobalMedian.Node(new int[]{2, 6, 9, 12, 17});
        nodeList.add(node);
        node = new GlobalMedian.Node(new int[]{4, 8, 11, 14, 15, 19});
        nodeList.add(node);

        GlobalMedian obj = new GlobalMedian(nodeList);

        int result = obj.globalMedian();
        System.out.println("RESULT = [" + result + "]");
    }

    private List<Node> mNodeList;
    private int mMedianPos;

    public GlobalMedian(List<Node> nodeList) {
        mNodeList = nodeList;

        int lengthSum = 0;
        if (mNodeList != null) {
            for (Node node : mNodeList) {
                lengthSum += node.getDataCount();
            }
        }
        if ((lengthSum & 0x0001) == 0) mMedianPos = (lengthSum >> 1);
        else mMedianPos = ((lengthSum + 1) >> 1);
    }

    public int globalMedian() {

        if ((mNodeList == null) || mNodeList.isEmpty()) {
            return 0;
        }

        int min = Integer.MIN_VALUE;
        int max = Integer.MAX_VALUE;
        return calcGlobalMedian(min, max);
    }

    private int calcGlobalMedian(int min, int max) {

        if (min >= max) {
            return min;
        }

        int minMedian = Integer.MAX_VALUE;
        int maxMedian = Integer.MIN_VALUE;
        for (Node node : mNodeList) {
            if (node.getDataCount() == 0) continue;

            int median = node.calcLocalMedian(min, max);
            if (minMedian > median) minMedian = median;
            if (maxMedian < median) maxMedian = median;
        }

        int middle;
        if (minMedian > min || maxMedian < max)
            middle = calcGlobalMedian(minMedian, maxMedian);
        else
            middle = min;

        int[] nums = new int[]{minMedian, middle, maxMedian};
        int[] leftCounts = new int[3];
        for (Node node : mNodeList) {
            node.countLeft(3, nums, leftCounts);
        }

        for (int i = 0; i < 3; i++) {
            if (leftCounts[i] == mMedianPos) return nums[i];
        }

        int newMin = Integer.MIN_VALUE, newMax = Integer.MAX_VALUE;
        for (int i = 0; i < 3; i++) {
            if (leftCounts[i] < mMedianPos) newMin = Math.max(newMin, nums[i]);
            else newMax = Math.min(newMax, nums[i]);
        }

        for (Node node : mNodeList) {
            node.reset(newMin, newMax);
        }
        return calcGlobalMedian(newMin, newMax);
    }

    public static class Node {
        private int[] mData;
        private List<Integer> mSortedData;
        private int mCount;

        public Node(int[] data) {
            mData = data;
            if (data == null) {
                mCount = 0;
                mSortedData = null;
            } else {
                mCount = data.length;
                Arrays.sort(mData);
                mSortedData = new ArrayList<Integer>();
                for (int i = 0; i < mCount; i++) {
                    mSortedData.add(mData[i]);
                }
            }
        }

        public int getDataCount() {
            return mCount;
        }

        public int calcLocalMedian(int min, int max) {

            if (mCount == 1) return mSortedData.get(0);

            if (min > Integer.MIN_VALUE || max < Integer.MAX_VALUE) {
                boolean ignoreMinMax = ((mSortedData.get(0) >= min) && (mSortedData.get(mCount - 1) <= max));
                List<Integer> newList = new ArrayList<Integer>();
                mCount = 0;
                for (Integer data : mSortedData) {
                    if (ignoreMinMax) {
                        if (data <= min || data >= max) continue;//如果所有元素都在min-max之间，则将min和max都过滤掉
                    } else {
                        if (data < min || data > max) continue;
                    }
                    newList.add(data);
                }
                mCount++;
                mSortedData.clear();
                mSortedData = newList;
            }

            return getMedian();
        }

        public void countLeft(int length, int[] nums, int[] leftCounts) {
            for (int data : mData) {
                for (int i = 0; i < length; i++) {
                    if (data <= nums[i]) leftCounts[i] = leftCounts[i] + 1;
                }
            }
        }

        public void reset(int min, int max) {
            mSortedData.clear();
            mCount = 0;
            int data;
            for (int i = 0; i < mData.length; i++) {
                data = mData[i];
                if ((data >= min) && (data <= max)) {
                    mSortedData.add(data);
                    mCount++;
                }
            }
        }


        private int getMedian() {
            int median;
            if ((mCount & 0x0001) == 0) median = mSortedData.get((mCount / 2) - 1); // here we use lower median
            else median = mSortedData.get(((mCount + 1) / 2) - 1);
            return median;
        }
    }
}
