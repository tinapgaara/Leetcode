package google.rangeSum;

/**
 * Created by yingtan on 10/3/17.
 */
public class CountOfRangeSum {

    class SegmentTreeNode {
        SegmentTreeNode left;
        SegmentTreeNode right;
        int count;
        long min;
        long max;
        public SegmentTreeNode(long min, long max) {
            this.min = min;
            this.max = max;
        }
    }
    private SegmentTreeNode buildSegmentTree(Long[] valArr, int low, int high) {
        if(low > high) return null;
        SegmentTreeNode stn = new SegmentTreeNode(valArr[low], valArr[high]);
        if(low == high) return stn;
        int mid = (low + high)/2;
        stn.left = buildSegmentTree(valArr, low, mid);
        stn.right = buildSegmentTree(valArr, mid+1, high);
        return stn;
    }
    private void updateSegmentTree(SegmentTreeNode stn, Long val) {
        if(stn == null) return;
        if(val >= stn.min && val <= stn.max) {
            stn.count++;
            updateSegmentTree(stn.left, val);
            updateSegmentTree(stn.right, val);
        }
    }
    private int getCount(SegmentTreeNode stn, long min, long max) {
        if(stn == null) return 0;
        if(min > stn.max || max < stn.min) return 0;
        if(min <= stn.min && max >= stn.max) return stn.count;
        return getCount(stn.left, min, max) + getCount(stn.right, min, max);
    }

    public int countRangeSum(int[] nums, int lower, int upper) {

        if(nums == null || nums.length == 0) return 0;
        int res = 0;
        long[] sums = new long[nums.length + 1];
        Long[] gnums = new Long[nums.length];
        //long sum = 0;
        for(int i = 0; i < nums.length; i++) {
            sums[i+1] =(long)(sums[i] + nums[i]);
            gnums[i] = (long)nums[i];
        }

        SegmentTreeNode root = buildSegmentTree(gnums, 0, nums.length-1);

        for(int j = 1; j < sums.length; j ++) {
            updateSegmentTree(root, sums[j]);
            //sum -= (long) nums[i];
            res += getCount(root, (long)lower+sums[j], (long)upper+sums[j]);
        }
        return res;
    }
}
