package string;

import java.util.*;

/**
 * Created by yingtan on 10/14/15.
 */
/*
*
*
*
*
* Key:
* s1.compareTo(s2) = 1: if Integer.parseInt(s1) < Integer.parseInt(s2)
* 仅限于s1和s2长度一样时才可用
*
* */
public class StroboGrammaticNumII {
    static int count = 0;
    public int strobogrammaticInRange(String low, String high) {
        List<String> res = new ArrayList<String>();
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        map.put(0,0);
        map.put(1,1);
        map.put(8,8);
        map.put(6,9);
        map.put(9,6);
        int[] oneDigits = new int[]{0,1,8};
        String s = "";
        for (int i = low.length(); i <= high.length(); i ++) { // Important !!!!
            recurStrob(s, 0, i-1, low, high, map, oneDigits);
        }
        return count;
    }

    public void recurStrob(String s, int lowIndex, int highIndex, String low, String high,
                           HashMap<Integer, Integer> map, int[] oneDigits) {
        if (lowIndex > highIndex) {
            if (((s.length() == 1) || (s.charAt(0) != '0'))  // first digit can not be zero
                    && ((compareTo(low, s)) && (compareTo(s, high)))) { // in range Important!!
                count ++;
            }
            return;
        }
        int len = s.length();
        if (lowIndex == highIndex) {
            for (int i = 0 ; i < oneDigits.length; i ++) {
                String newstr = s.substring(0,len/2) + oneDigits[i] + s.substring(len/2);
                recurStrob(newstr, lowIndex+1, highIndex-1, low, high, map, oneDigits);
            }
        } else {
            for (Map.Entry<Integer, Integer> entry: map.entrySet()) {
                String newstr = s.substring(0, len/2) + entry.getKey() + entry.getValue() + s.substring(len/2);
                recurStrob(newstr, lowIndex+1, highIndex-1, low, high, map, oneDigits);
            }
        }
    }
    // compare if s1 <= s2
    public boolean compareTo(String s1, String s2) {
        if (s1.length() == s2.length()) {
            if (s1.compareTo(s2) <= 0) {
                return true;
            } else {
                return false;
            }
        } else {
            if (s1.length() < s2.length()) return true;
            else return false;
        }
    }

    public class Element {
        String str;
        int isSame;
        int lowIndex;
        int highIndex;

        public Element(String s,int flag, int low, int high) {
            isSame = flag;
            str = s;
            lowIndex = low;
            highIndex = high;
        }
    }
    public int strobogrammaticInRange_2(String low, String high) {
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        map.put(0,0);map.put(1,1);map.put(8,8);map.put(6,9);map.put(9,6);
        int[] oneDigits = new int[]{0,1,8};

        Queue<Element> queue = new LinkedList<Element>();
        initializeQueue(low, high, queue);

        while (!queue.isEmpty()) {
            Element e = queue.peek();
            int prevLowIndex = e.lowIndex;
            int prevHighIndex = e.highIndex;
            int curLowIndex = prevLowIndex + 1;
            int curHighIndex = prevHighIndex - 1;
            String prevStr = e.str;
            int prevLen = prevStr.length();
            if (curLowIndex < curHighIndex) {
                queue.poll();
                for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
                    char candidate = (char) ('0' + entry.getKey());
                    char upsideDownCandidate = (char) ('0' + entry.getValue());

                    if (e.isSame == 1) {// all the same
                        if ((candidate >= low.charAt(curLowIndex)
                                && (candidate <= high.charAt(curLowIndex)))) {
                            String newstr = prevStr.substring(0, prevLen / 2) + candidate + upsideDownCandidate + prevStr.substring(prevLen / 2);
                            if ((candidate == low.charAt(curLowIndex))
                                    && (candidate == high.charAt(curLowIndex)))
                                queue.offer(new Element(newstr, 1, curLowIndex, curHighIndex));
                            else if (candidate == low.charAt(curLowIndex))
                                queue.offer(new Element(newstr, 2, curLowIndex, curHighIndex));
                            else if (candidate == high.charAt(curLowIndex))
                                queue.offer(new Element(newstr, 3, curLowIndex, curHighIndex));
                            else
                                queue.offer(new Element(newstr, 0, curLowIndex, curHighIndex));
                        }
                    } else if (e.isSame == 2) { // same digit as low number, different from high number
                        if ((candidate >= low.charAt(curLowIndex))) {
                            String newstr = prevStr.substring(0, prevLen / 2) + candidate + upsideDownCandidate + prevStr.substring(prevLen / 2);
                            if ((candidate == low.charAt(curLowIndex)))
                                queue.offer(new Element(newstr, 2, curLowIndex, curHighIndex));
                            else
                                queue.offer(new Element(newstr, 0, curLowIndex, curHighIndex));
                        }
                    } else if (e.isSame == 3) {// same digit as high number, different from low number
                        if ((candidate <= high.charAt(curLowIndex))) {
                            String newstr = prevStr.substring(0, prevLen / 2) + candidate + upsideDownCandidate + prevStr.substring(prevLen / 2);
                            if ((candidate == high.charAt(curLowIndex)))
                                queue.offer(new Element(newstr, 3, curLowIndex, curHighIndex));
                            else
                                queue.offer(new Element(newstr, 0, curLowIndex, curHighIndex));
                        }
                    } else {
                        String newstr = prevStr.substring(0, prevLen / 2) + candidate + upsideDownCandidate + prevStr.substring(prevLen / 2);
                        queue.offer(new Element(newstr, 0, curLowIndex, curHighIndex)); // isSame = 0
                    }
                }
            } else if (curLowIndex == curHighIndex) {
                queue.poll();
                for (int i = 0; i < oneDigits.length; i++) {
                    char candidate = (char) ('0' + oneDigits[i]);

                    if (e.isSame == 1) {// all the same
                        if ((candidate >= low.charAt(curLowIndex)
                                && (candidate <= high.charAt(curLowIndex)))) {
                            String newstr = prevStr.substring(0, prevLen / 2) + candidate + prevStr.substring(prevLen / 2);
                            queue.offer(new Element(newstr, 0, curLowIndex, curHighIndex));
                        }
                    } else if (e.isSame == 2) {
                        if ((candidate >= low.charAt(curLowIndex))) {
                            String newstr = prevStr.substring(0, prevLen / 2) + candidate + prevStr.substring(prevLen / 2);
                            queue.offer(new Element(newstr, 0, curLowIndex, curHighIndex));
                        }
                    } else if (e.isSame == 3) {
                        if ((candidate <= high.charAt(curLowIndex))) {
                            String newstr = prevStr.substring(0, prevLen / 2) + candidate + prevStr.substring(prevLen / 2);
                            queue.offer(new Element(newstr, 0, curLowIndex, curHighIndex));
                        }
                    } else {
                        String newstr = prevStr.substring(0, prevLen / 2) + candidate + prevStr.substring(prevLen / 2);
                        queue.offer(new Element(newstr, 0, curLowIndex, curHighIndex)); // isSame = 0
                    }
                }
            } else break;
        }
        return 0;
    }

    public void initializeQueue(String low, String high, Queue<Element> queue) {
        queue.offer(new Element("69",1, 0, 5));
    }

    public static  void main(String[] args) {
        StroboGrammaticNumII ob = new StroboGrammaticNumII();
        System.out.println(ob.strobogrammaticInRange("50", "100"));
    }
}
