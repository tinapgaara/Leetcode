package google.string;

/**
 * Created by yingtan on 10/28/17.
 *
 * 686. Repeated String Match
 DescriptionHintsSubmissionsDiscussSolution
 Discuss Pick One
 Given two strings A and B, find the minimum number of times A has to be repeated such that B is a substring of it. If no such solution, return -1.

 For example, with A = "abcd" and B = "cdabcdab".

 Return 3, because by repeating A three times (“abcdabcdabcd”), B is a substring of it; and B is not a substring of A repeated two times ("abcdabcd").

 Note:
 */
public class RepeatedStringMatch {

    public int repeatedStringMatch(String A, String B) {
        if(A == null || B == null) return -1;
        int count = 0;
        StringBuilder sb = new StringBuilder();
        while(sb.length() < B.length()) {
            sb.append(A);
            count ++;
        }
        if (sb.toString().contains(B)) {
            return count;
        }
        // important !!! ,  when sb.length >= B.length, if sb still does not contains B, this means, there are some off, and off should be in one A.
        // if sb + A still does not contain B, then, this means, not exist
        if (sb.append(A).toString().contains(B)) {
            count ++;
            return count;
        }
        return -1;
    }
}
