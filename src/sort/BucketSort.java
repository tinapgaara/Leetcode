package sort;

import java.util.LinkedList;

/**
 * Created by yingtan on 10/5/15.
 */
public class BucketSort {
    /*
    * Assume that the input is gnerated by a random process that distributed elements uniformly and indepedently over interval [0,1)
    * Divides the interval [0,1) into n equal-sized subintervals: buckets,
    * and sort numbers in each bucket and go through the buckets in order.
    *
    * Time: under uniform distribution: O(n)
    * */

    public class Bucket {

        LinkedList<Double> m_list;

        public Bucket(LinkedList<Double> list) {
            m_list = list;
        }

        public void insert(double e) {
            // insertionSort // O(n)
            double key = e;
            int j = m_list.size()-1; // PAY attention !!!!!: firstly save this value
            m_list.add(e);

            for (; j >=0 ; j --) {
                double num = m_list.get(j);
                if (num > key) {
                    m_list.set(j+1, num);
                }
                else {
                    break; // important !!!!
                }
            }
            m_list.set(j+1, key);
        }
    }

    public int[] bucketSort(int[] nums, int maxNum) {

        // change integer to double between [0,1) : O(n)
        double[] doubleNums = new double[nums.length];
        for (int i = 0 ; i < nums.length ; i ++) {
            doubleNums[i] = (double)(nums[i]) / (maxNum + 1) ;
        }
        // initialize bucket data structure : O(n)
        Bucket[] buckets = new Bucket[nums.length];
        for (int i = 0 ; i < nums.length; i ++) {
            LinkedList<Double> list = new LinkedList<>();
            Bucket bucket = new Bucket(list);
            buckets[i] = bucket;
        }

        // insert num[i] to buckets : under uniform distribution: O(n)
        for (int i = 0 ; i < doubleNums.length ; i ++) {
            double dNum = doubleNums[i];
            int insertBucketNo = (int) (nums.length * dNum); // Important !!!! reover double to int : nums.length * double
            buckets[insertBucketNo].insert(dNum);
        }

        int[] res = new int[nums.length];
        int j = 0;
        // iterate to return all values
        for (int i = 0 ; i < buckets.length; i ++) {
            LinkedList<Double> list = buckets[i].m_list;
            for (Double d: list) {
                res[j] = (int) (d.doubleValue() * (maxNum + 1));
                j ++;
            }
        }
        return res;
    }

    // O(n) time, O(n) space
    /*
    * Leetcode: Maximum Gap
    * Given an unsorted array, find the maximum difference between the successive elements in its sorted form.

        Try to solve it in linear time/space.

        Return 0 if the array contains less than 2 elements.

        You may assume all elements in the array are non-negative integers and fit in the 32-bit signed integer range.
    * */
    public int maximumGap(int[] nums) {

        int maxValue = 0;
        for (int i = 0 ; i < nums.length; i ++) {
            maxValue = Math.max(nums[i], maxValue);
        }
        nums = bucketSort(nums, maxValue);
        int max = 0;
        int prev = 0;
        int cur = 1;
        while (cur < nums.length) {
            max = Math.max((nums[cur] - nums[prev]), max);
            prev ++;
            cur ++;
        }
        return max;
    }

    public static void main(String[] args) {
        BucketSort ob = new BucketSort();
        int[] nums = new int[]{15252,16764,27963,7817,26155,20757,3478,22602,20404,6739,16790,10588,16521,6644,20880,15632,27078,25463,20124,15728,30042,16604,17223,4388,23646,32683,23688,12439,30630,3895,7926,22101,32406,21540,31799,3768,26679,21799,23740};
        nums = ob.bucketSort(nums, 32683);
    }
}
