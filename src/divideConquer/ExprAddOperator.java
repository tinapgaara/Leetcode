package divideConquer;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yingtan on 9/27/15.
 */
public class ExprAddOperator {

    public List<String> addOperators(String num, int target) {
        List<String> res = new ArrayList<String>();
        if ( (num == null) || (num.length() == 0) ) return res;

        if (num.length() == 1) {
            if (Integer.parseInt(num) == target)
                res.add(num);
            return res;
        }
        String prev = "";
        for (int i = 0 ; i < num.length() ; i ++) {
            prev = prev + num.charAt(i);
            List<List<String>> startList = new ArrayList<List<String>>();
            List<List<String>> endList = new ArrayList<List<String>>();

            List<Integer> start = new ArrayList<>();
            start.add(Integer.parseInt(prev)); //addOperatorsValue(prev, startList);
            List<String> strStart = new ArrayList<>();
            strStart.add(prev);
            startList.add(strStart);

            List<Integer> end = addOperatorsValue(num.substring(i+1, num.length()), endList);
            for (int j = 0 ; j < start.size() ; j ++) {
                for (int k = 0 ; k < end.size(); k ++) {
                    int operator1 = start.get(j);
                    int operator2 = end.get(k);
                    if (operator1 + operator2 == target) {
                        String s  = "";
                        for (int m = 0 ; m < startList.get(j).size(); m ++) {
                            for (int n = 0 ; n < endList.get(k).size(); n ++) {
                                s = startList.get(j).get(m)+ "+" + endList.get(k).get(n);
                            }
                        }
                        if (!res.contains(s))
                            res.add(s);
                    }
                    if (operator1 * operator2 == target) {
                        String s  = "";
                        for (int m = 0 ; m < startList.get(j).size(); m ++) {
                            for (int n = 0 ; n < endList.get(k).size(); n ++) {
                                s = startList.get(j).get(m)+ "*" + endList.get(k).get(n);
                            }
                        }
                        if (!res.contains(s))
                            res.add(s);
                    }
                    if (operator1 - operator2 == target) {
                        String s  = "";
                        for (int m = 0 ; m < startList.get(j).size(); m ++) {
                            for (int n = 0 ; n < endList.get(k).size(); n ++) {
                                s = startList.get(j).get(m)+ "-" + endList.get(k).get(n);
                            }
                        }
                        if (!res.contains(s))
                            res.add(s);
                    }
                }
            }
        }
        return res;
    }

    public List<Integer> addOperatorsValue(String num, List<List<String>> res) {
        if (num.length() == 1) {
            List<String> single = new ArrayList<String>();
            single.add(num);
            res.add(single);

            List<Integer> intSingle = new ArrayList<>();
            intSingle.add(Integer.parseInt(num));
            return intSingle;
        }
        String prev = "";
        List<Integer> value = new ArrayList<Integer>();
        for (int i = 0 ; i < num.length() -1; i ++) {
            prev = prev + num.charAt(i);
            List<List<String>> startList = new ArrayList<List<String>>();
            List<List<String>> endList = new ArrayList<List<String>>();
            List<Integer> start = addOperatorsValue(prev, startList);
            List<Integer> end = addOperatorsValue(num.substring(i+1, num.length()), endList);
            for (int j = 0 ; j < start.size() ; j ++) {
                for (int k = 0 ; k < end.size(); k ++) {
                    int operator1 = start.get(j);
                    int operator2 = end.get(k);
                    value.add(operator1 + operator2);
                    List<String> list = new ArrayList<>();
                    for (int m = 0 ; m < startList.get(j).size(); m ++) {
                        for (int n = 0 ; n < endList.get(k).size(); n ++) {
                            String s = startList.get(j).get(m)+ "+" + endList.get(k).get(n);
                            list.add(s);
                        }
                    }
                    res.add(list);

                    value.add(operator1 * operator2);
                    list = new ArrayList<>();
                    for (int m = 0 ; m < startList.get(j).size(); m ++) {
                        for (int n = 0 ; n < endList.get(k).size(); n ++) {
                            String s = startList.get(j).get(m)+ "*" + endList.get(k).get(n);
                            list.add(s);
                        }
                    }
                    res.add(list);

                    value.add(operator1 - operator2);
                    list = new ArrayList<>();
                    for (int m = 0 ; m < startList.get(j).size(); m ++) {
                        for (int n = 0 ; n < endList.get(k).size(); n ++) {
                            String s = startList.get(j).get(m)+ "-" + endList.get(k).get(n);
                            list.add(s);
                        }
                    }
                    res.add(list);
                }
            }
        }
        System.out.println(value);
        return value;
    }

    public static void main(String[] args) {
        ExprAddOperator ob = new ExprAddOperator();
        System.out.println(ob.addOperators("3456237490",9191));
    }
}
