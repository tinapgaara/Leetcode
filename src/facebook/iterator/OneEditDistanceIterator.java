package facebook.iterator;
import java.util.*;
/*
*
* 第二题是“一个编辑距离”的变体，但其实这题有点难度，输入没有给两个字符串，而是两个自定义的 interface,里面只有 next()一个方法，next()返回0的时候就是 end,
*
* */
public class OneEditDistanceIterator {
    // key: allows two times mismatch, and buffer the string between two mismatched times
    public boolean isOneDistance(Iterator<Character> i1, Iterator<Character> i2) {
        // iterator only have next
        // allow mismatched chs twice,
        // A A A B (i' - i)
        // B A A A (j' - j)
        StringBuilder str1 = new StringBuilder();
        StringBuilder str2 = new StringBuilder();
        boolean mismatchOnce = false;
        while(i1.hasNext() && i2.hasNext()) {
            char num1 = i1.next();
            char num2 = i2.next();
            if (num1 != num2) {
                if (mismatchOnce) {
                    // this is the second mismatch
                    // retrieve st1 and st2
                    if (str1.substring(0, str1.length()-1).equals(str2.substring(1, str2.length()))) {
                        // can't be mismatch in future, then we are good
                        if (i2.hasNext() && num1 == i2.next()) {
                            while(true) {
                                num1 = i1.next();
                                num2 = i2.next();
                                if (num1 != num2) {
                                    return false;
                                }
                                if (num1 == 0 && num2 == 0) {
                                    return true;
                                }
                            }
                        }
                        else {
                            return false;
                        }

                    }
                    else if (str1.substring(1, str1.length()).equals(str2.substring(0, str2.length()))) {
                        // can't be mismatch in future, then we are good
                        if (i1.hasNext() && num2 == i1.next()) {
                            while(true) {
                                num1 = i1.next();
                                num2 = i2.next();
                                if (num1 != num2) {
                                    return false;
                                }
                                if (num1 == 0 && num2 == 0) {
                                    return true;
                                }
                            }
                        }
                        else {
                            return false;
                        }
                    }
                }
                else {
                    mismatchOnce = true;
                    str1.append(num1);
                    str2.append(num2);
                }
            }
            else {
                if (mismatchOnce) {
                    str1.append(num1);
                    str2.append(num2);
                }
            }
        }
        return true;
    }
    public static void main(String[] args) {
        OneEditDistanceIterator ob = new OneEditDistanceIterator();
        Character a[] = new Character[]{'C', 'A', 'A', 'A', 'B', 0};
        List<Character> list1 = Arrays.asList(a);
        Character b[] = new Character[]{'C', 'B', 'A', 'A', 'A', 'B', 0};
        List<Character> list2 = Arrays.asList(b);

        ob.isOneDistance(list1.iterator(), list2.iterator());
    }
}
