package stack;

/**
 * Created by yingtan on 3/4/18.
 *
 * 726. Number of Atoms
 DescriptionHintsSubmissionsDiscussSolution
 Pick One
 Given a chemical formula (given as a string), return the count of each atom.

 An atomic element always starts with an uppercase character, then zero or more lowercase letters, representing the name.

 1 or more digits representing the count of that element may follow if the count is greater than 1. If the count is 1, no digits will follow. For example, H2O and H2O2 are possible, but H1O2 is impossible.

 Two formulas concatenated together produce another formula. For example, H2O2He3Mg4 is also a formula.

 A formula placed in parentheses, and a count (optionally added) is also a formula. For example, (H2O2) and (H2O2)3 are formulas.

 Given a formula, output the count of all elements as a string in the following form: the first name (in sorted order), followed by its count (if that count is more than 1), followed by the second name (in sorted order), followed by its count (if that count is more than 1), and so on.

 Example 1:
 Input:
 formula = "H2O"
 Output: "H2O"
 Explanation:
 The count of elements are {'H': 2, 'O': 1}.
 Example 2:
 Input:
 formula = "Mg(OH)2"
 Output: "H2MgO2"
 Explanation:
 The count of elements are {'H': 2, 'Mg': 1, 'O': 2}.
 Example 3:
 Input:
 formula = "K4(ON(SO3)2)2"
 Output: "K4N2O14S4"
 Explanation:
 The count of elements are {'K': 4, 'N': 2, 'O': 14, 'S': 4}.
 Note:

 All atom names consist of lowercase letters, except for the first character which is uppercase.
 The length of formula will be in the range [1, 1000].
 formula will only consist of letters, digits, and round parentheses, and is a valid formula as defined in the
 */
import java.util.*;

public class NumberOfAtoms {
    public String countOfAtoms_timeExceedLimit(String formula) {
        if (formula == null || formula.length() == 0) return formula;
        Stack<String> stack = new Stack<>();
        String cur = "";
        for (int i = 0; i < formula.length(); i ++) {
            // keep adding together until hit a digit or (
            if(i < formula.length()) {
                while(i < formula.length() &&
                        ( Character.isLetter(formula.charAt(i)) || Character.isDigit(formula.charAt(i)))) {
                    cur = cur + formula.charAt(i);
                    i ++;
                }
            }
            if (i >= formula.length()) break; // Important !!!!
            char ch = formula.charAt(i);
            if (ch == '(') {
                // push to stack
                stack.push(cur);
                cur = "";
            }
            else if (ch == ')') {
                // pop from stack
                String countStr = "";
                // get consecutive digits which represent count
                while(i+1 < formula.length() && Character.isDigit(formula.charAt(i+1))) {
                    countStr = countStr + formula.charAt(i+1);
                    i ++;
                }
                // expand
                if (countStr.length() > 0) {
                    int c = Integer.parseInt(countStr);
                    String newcur = "";
                    for (int j = 0 ; j < c; j ++) {
                        newcur = newcur + cur;
                    }
                    cur = newcur;
                }
                if (! stack.isEmpty()) {
                    String old = stack.pop();
                    cur = old + cur;
                }
            }
        }
        TreeMap<String, Integer> count = new TreeMap<>();
        String str = cur;
        int i = 0;
        String key = null;
        String newstrcount = "1";
        while(i < str.length()) {
            if(i < str.length() && Character.isUpperCase(str.charAt(i))) {
                key = str.charAt(i) + "";
                i ++;
                while(i < str.length() && Character.isLowerCase(str.charAt(i))) {
                    key = key + str.charAt(i);
                    i ++;
                }
            }
            if (i < str.length() && Character.isDigit(str.charAt(i))) {
                newstrcount = str.charAt(i) + "";
                i ++;
                while(i < str.length() && Character.isDigit(str.charAt(i))) {
                    newstrcount = newstrcount + str.charAt(i);
                    i ++;
                }
            }
            if (key != null) {
                if (count.containsKey(key)) {
                    count.put(key, count.get(key) + Integer.parseInt(newstrcount));
                }
                else {
                    count.put(key, Integer.parseInt(newstrcount));
                }
                // reset
                key = null;
                newstrcount = "1";
            }
        }
        String res = "";
        for (String s : count.keySet()) {
            if (count.get(s) > 1) {
                res = res + s+ count.get(s);
            }
            else {
                res = res + s;
            }
        }
        return res;
    }
    public static void main(String[] args) {
        NumberOfAtoms ob = new NumberOfAtoms();
        //ob.countOfAtoms("K4(ON(SO3)2)2");
        ob.countOfAtoms_timeExceedLimit("(((U42Se42Fe10Mc31Rh49Pu49Sb49)49V39Tm50Zr44Og6)33((W2Ga48Tm14Eu46Mt12)23(RuRnMn11)7(Yb15Lu34Ra19CuTb2)47(Md38BhCu48Db15Hf12Ir40)7CdNi21(Db40Zr24Tc27SrBk46Es41DsI37Np9Lu16)46(Zn49Ho19RhClF9Tb30SiCuYb16)15)37(Cr48(Ni31)25(La8Ti17Rn6Ce35)36(Sg42Ts32Ca)37Tl6Nb47Rh32NdGa18Cm10Pt49(Ar37RuSb30Cm32Rf28B39Re7F36In19Zn50)46)38(Rh19Md23No22PoTl35Pd35Hg)41)50");
    }
}
