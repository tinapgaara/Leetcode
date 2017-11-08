package google.greedy;

/**
 * Created by yingtan on 8/13/17.
 *
 * 484. Find Permutation
 *
 * By now, you are given a secret signature consisting of character 'D' and 'I'. 'D' represents a decreasing relationship between two numbers, 'I' represents an increasing relationship between two numbers. And our secret signature was constructed by a special integer array, which contains uniquely all the different number from 1 to n (n is the length of the secret signature plus 1). For example, the secret signature "DI" can be constructed by array [2,1,3] or [3,1,2], but won't be constructed by array [3,2,4] or [2,1,3,4], which are both illegal constructing special string that can't represent the "DI" secret signature.

 On the other hand, now your job is to find the lexicographically smallest permutation of [1, 2, ... n] could refer to the given secret signature in the input.

 Example 1:
 Input: "I"
 Output: [1,2]
 Explanation: [1,2] is the only legal initial spectial string can construct secret signature "I", where the number 1 and 2 construct an increasing relationship.
 Example 2:
 Input: "DI"
 Output: [2,1,3]
 Explanation: Both [2,1,3] and [3,1,2] can construct the secret signature "DI",
 but since we want to find the one with the smallest lexicographical permutation, you need to output [2,1,3]
 */
public class FindPermutation {

    /*
    * 下面这种方法没有用到数组倒置，
    * 贪婪算法,遇到I就从当前curNum开始往上+1填,然后遇到了D之后就往回遍历一样的D
    *
    *
    * 而是根据情况来往结果res中加入正确顺序的数字，
    * 我们遍历s字符串，遇到D直接跳过，遇到I进行处理，
    * 我们每次先记录下结果curFilledNum，然后从i-1的位置开始往startPos遍历，
    * 将数字加入结果res中即可，
    *
    * * eg:
    * D D D I I D I
    * i=3, curFiledNum = 1, startPos= 0, res:[D D D 1 I D I]
    * 从I往回填:
    * j=2, curFiledNum = 2, startPos= 4, res:[D D 2 1 I D I]
    * j=1, curFiledNum = 3, startPos= 4, res:[D 3 2 1 I D I]
    * j=0, curFiledNum = 4, startPos= 4, res:[4 3 2 1 I D I]
    *
    * i=4, curFiledNum = 5, startPos= 4, res:[4 3 2 1 5 D I]
    * i=6, curFiledNum = 6, startPos= 4, res:[4 3 2 1 5 D 6]
    * j=5, curFiledNum = 7, startPos= 4, res:[4 3 2 1 5 7 6]
    *
    *
    * */
    public int[] findPermutation(String s) {
        int[] res = new int[s.length() + 1];
        int curFilledNum = 1;
        int startPos = 0;
        for (int i = 0 ; i < s.length() + 1; i ++) {
            if (i == s.length() || s.charAt(i) == 'I') {
                res[i] = curFilledNum;
                curFilledNum ++;

                // reverse back to fill D
                for (int j = i - 1; j >= startPos; j --) {
                    res[j] = curFilledNum;
                    curFilledNum ++;
                }
                startPos = i + 1;
            }
        }
        return res;
    }

}
