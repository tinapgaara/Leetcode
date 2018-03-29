package facebook.binarySearch;

import java.util.Random;

/**
 * Created by yingtan on 5/15/17.
 *
 * 278. First Bad Version Add to List
 DescriptionHintsSubmissionsSolutions
 Total Accepted: 95323
 Total Submissions: 383438
 Difficulty: Easy
 Contributor: LeetCode
 You are a product manager and currently leading a team to develop a new product. Unfortunately, the latest version of your product fails the quality check. Since each version is developed based on the previous version, all the versions after a bad version are also bad.

 Suppose you have n versions [1, 2, ..., n] and you want to find out the first bad one, which causes all the following ones to be bad.

 You are given an API bool isBadVersion(version) which will return whether version is bad. Implement a function to find the first bad version. You should minimize the number of calls to the API.

 Credits:
 Special thanks to @jianchao.li.fighter for adding this problem and creating all test cases.
 */
public class FirstBadVersion {

    public int firstBadVersion(int n) {
        long low = 1;
        long high = n;
        while(((long)(low + 1)) < (long)high) {
            long mid = low + (high - low) / 2;
            if (isBadVersion((int)mid)) {
                high = mid;
            }
            else {
                low = mid + 1;
            }
        }
        if (isBadVersion((int)low)) return (int)low;
        else if (isBadVersion((int)high)) return (int)high;
        return -1;
    }
    public boolean isBadVersion(int num) {
        Random rn = new Random();
        return rn.nextBoolean();
    }
}
