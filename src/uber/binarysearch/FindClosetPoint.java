package uber.binarysearch;

/**
 * Created by yingtan on 11/9/17.
 *
 *
 第三轮是国人小哥，也是先聊工作经历然后做题，
 大意是有个data stream输入很多数据点，每个数据点包括timestamp和value，
 要写个get函数，给定一个timestamp，求离该timestamp最近的数据点的value。
 历史数据点是可以存在array里的，所以我用了二分法。最后follow up，如果输入有可能被delay，次序被打乱了怎么办等等。

 */
public class FindClosetPoint {

    public int findCloset(int[] timestamp, double target) {
        int low = 0;
        int high = timestamp.length - 1;
        while(low + 1 < high) {
            int med = low + (high - low) / 2;
            if (timestamp[med] > target) {
                high = med;
            }
            else {
                low = med;
            }
        }
        System.out.println(low + "," + high);
        double diff1ow = Math.abs(low - target);
        double diffhigh = Math.abs(high - target);
        if (diff1ow < diffhigh) {
            return timestamp[low];
        }
        else {
            return timestamp[high];
        }
    }

    public static void main(String[] args) {
        FindClosetPoint ob = new FindClosetPoint();
        int[] time = {1,2,3,4,5};
        System.out.println(ob.findCloset(time, 4.666));
    }
}
