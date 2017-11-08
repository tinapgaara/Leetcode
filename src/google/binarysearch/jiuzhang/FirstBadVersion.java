package google.binarysearch.jiuzhang;

/**
 * Created by yingtan on 10/29/17.
 *
 * 78. First Bad Version
 DescriptionHintsSubmissionsDiscussSolution
 Discuss Pick One
 You are a product manager and currently leading a team to develop a new product. Unfortunately, the latest version of your product fails the quality check. Since each version is developed based on the previous version, all the versions after a bad version are also bad.

 Suppose you have n versions [1, 2, ..., n] and you want to find out the first bad one, which causes all the following ones to be bad.

 You are given an API bool isBadVersion(version) which will return whether version is bad. Implement a function to find the first bad version. You should minimize the number of calls to the API.

 Credits:
 Special thanks to @jianchao.li.fighter for adding this problem and creating all test cases.


 */
public class FirstBadVersion {

    public int firstBadVersion(int n) {

        int low = 1;
        int high = n;
        while (low + 1 < high) {
            int mid = low + (high - low) / 2;
            if(isBadVersion(mid)) {
                high = mid;
            }
            else {
                // important !!! use low = mid, can not use low = mid + 1; will Time Exceed Limit for input n : 2^31 -1
                low = mid;
            }
        }
        if (isBadVersion(low)) return low;
        else if (isBadVersion(high)) return high;
        return -1;
    }

    private boolean isBadVersion(int index) {
         return true;
    }
}
