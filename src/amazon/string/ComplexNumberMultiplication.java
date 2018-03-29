package amazon.string;

/**
 * Created by yingtan on 3/25/18.
 *
 * 537. Complex Number Multiplication
 DescriptionHintsSubmissionsDiscussSolution
 Pick One
 Given two strings representing two complex numbers.

 You need to return a string representing their multiplication. Note i2 = -1 according to the definition.

 Example 1:
 Input: "1+1i", "1+1i"
 Output: "0+2i"
 Explanation: (1 + i) * (1 + i) = 1 + i2 + 2 * i = 2i, and you need convert it to the form of 0+2i.
 Example 2:
 Input: "1+-1i", "1+-1i"
 Output: "0+-2i"
 Explanation: (1 - i) * (1 - i) = 1 + i2 - 2 * i = -2i, and you need convert it to the form of 0+-2i.
 */
public class ComplexNumberMultiplication {
    public String complexNumberMultiply(String a, String b) {
        if (a == null || b == null || a.length() == 0 || b.length() == 0) return "";
        int[] nums1 = helper(a);
        int[] nums2 = helper(b);
        System.out.println(nums1[0] + " " + nums1[1]);
        System.out.println(nums2[0] + " " + nums2[1]);
        int[] res = new int[3];
        res[0] = nums1[0] * nums2[0];
        res[1] = nums1[1] * nums2[0] + nums2[1] * nums1[0];
        res[2] = nums1[1] * nums2[1] * -1;
        int first = res[0] + res[2];
        return first + "+" + res[1] + "i";
    }
    public int[] helper(String s) {
        String[] strs = s.split("\\+");
        int a1 = Integer.parseInt(strs[0]);
        int a2 = Integer.parseInt(strs[1].replace("i",""));
        return new int[]{a1, a2};
    }
}
