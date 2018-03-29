package facebook.string;

/**
 * Created by yingtan on 2/19/18.
 *
 * 28. Implement strStr()
 DescriptionHintsSubmissionsDiscussSolution
 Pick One
 Implement strStr().

 Return the index of the first occurrence of needle in haystack, or -1 if needle is not part of haystack.

 Example 1:

 Input: haystack = "hello", needle = "ll"
 Output: 2
 Example 2:

 Input: haystack = "aaaaa", needle = "bba"
 Output: -1

 */
public class ImplementstrStr {
    // Solution 1: translate a string to hashcode,  compare if str1 and str2 have the same hashcode.
    // hash(ABC) = A * 100 + B * 10 + C
    // ABC -> BCD : hash(ABC) - 100* A => BC, then, hash(BC) * 10 + D
    // complexity: o(haystack)
    public int strStr(String haystack, String needle) {
        if (haystack == null || needle == null) return 0;
        int needLen = needle.length();
        if (haystack.length() < needle.length()) return -1;
        long hayhash = 0; // must use long
        long needlehash = 0;
        long prod = 1;
        StringBuilder builder = new StringBuilder();
        for (int i = 0 ; i < needLen; i ++) {
            char needlech = needle.charAt(i);
            char haych = haystack.charAt(i);
            prod = prod * 10;
            hayhash = hayhash * 10 + (int)haych;
            needlehash = needlehash * 10 + (int)needlech;
            builder.append(haych);
        }
        prod = prod / 10;
        if (hayhash == needlehash && builder.toString().equals(needle)) {
            return 0; //  true, can find it starting at 0 index
        }
        // important !!! starts with needLen, ends with haystack.length()
        for (int i = needLen; i < haystack.length(); i ++) {
            hayhash = update(hayhash, builder, haystack.charAt(i), prod);
            // not equals, move to the next possible candidate: remove 0, add ith ch
            if (hayhash == needlehash) {
                // double check
                if (builder.toString().equals(needle)) {
                    // the one ends with i-1 is a good one
                    return (i - needLen + 1);
                }
            }
        }
        return -1;
    }
    public long update(long hayhash, StringBuilder builder, char newch, long prod) {
        // ABC -> BCD : (int)ABC - 100* A = BC, then, (int)BC * 10 + D
        long res = (hayhash - ((int)builder.charAt(0) * prod)) * 10 + (int)newch;
        builder.deleteCharAt(0);
        builder.append(newch);
        return res;
    }



    // Solution 2:  normal way to compare two strings, if a ch equals, go to check if two string equals completely.
    // complexity: o(haystack * needle)
    public int strStr_2(String haystack, String needle) {
        if (haystack == null || haystack.length() == 0 || needle == null || needle.length() == 0) {
            if (haystack.length() == 0 && needle.length() == 0) return 0;
            else if (needle.length() == 0) return 0;
            else return -1;
        }
        int needleIndex = 0;
        int i = 0;
        while (i < haystack.length()) {
            if (haystack.charAt(i) == needle.charAt(needleIndex)) {
                if (isEqual(haystack, i, needle, needleIndex)) {
                    return i;
                }
            }
            i++;
        }
        return -1;
    }
    public boolean isEqual(String str, int i, String needle, int j) {
        while (i < str.length() && j < needle.length()) {
            if (str.charAt(i) == needle.charAt(j)) {
                i++;
                j++;
            } else {
                return false;
            }
        }
        return j == (needle.length());
    }
}

