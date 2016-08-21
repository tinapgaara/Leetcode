package bloomberg.calculator;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Created by yingtan on 10/18/15.
 */
public class AddOperators {

    public class Value {
        int val; // C
        int lastVal; // each value is separted as two values add together : C = a + b.  lastVal = b

        public Value(int val, int lastVal) {
            this.val = val;
            this.lastVal = lastVal;
        }
    }
    public List<String> addOperators(String num, int target) {
        List<String> res = new ArrayList<String>();
        Queue<String> expression = new LinkedList<String>();
        Queue<Value> val = new LinkedList<Value>();
        Queue<Integer> indexs = new LinkedList<Integer>();
        if ( (num == null) || (num.length() == 0) ) return res;

        intializeFirstOperatons(num, expression, val, indexs);

        while (!expression.isEmpty()) {
            String prevStr = expression.poll();
            int index = indexs.poll() + 1;
            Value prevval = val.poll();

            String element = "";
            if (index >= num.length()) {
                if (prevval.val == target)  {
                    res.add(prevStr);
                    continue;
                }
            }
            for (int j = index; j < num.length(); j ++) {
                element = element + num.charAt(j);

                int curval = Integer.parseInt(element); // 05
                // +
                expression.offer(prevStr + "+" + curval);
                val.offer((new Value(prevval.val + curval, curval)));
                indexs.offer(j);
                // -:
                expression.offer(prevStr + "-" + curval);
                val.offer((new Value(prevval.val - curval, (-1 * curval))));
                indexs.offer(j);
                // *:
                expression.offer(prevStr + "*" + curval);
                multipleOps(prevval, curval , val);
                indexs.offer(j);

                if (num.charAt(index) == '0') break; // Important !!! for 0 , 00, 000 : boundary case: 105,5. 1+ 5 Wrong
            }
        }
        return res;
    }

    public void intializeFirstOperatons(String num, Queue<String> res, Queue<Value> val, Queue<Integer> indexs) {
        String element = "";
        for (int i = 0 ; i < num.length(); i ++) {
            element = element + num.charAt(i);
            if (Long.parseLong(element) > Integer.MAX_VALUE) return; // Important for boundary case: num > MAX_VALUe
            int nEle = Integer.parseInt(element);
            res.offer(nEle+"");
            val.offer(new Value(nEle, nEle));
            indexs.offer(i);

            if (num.charAt(0) == '0') break; // Important !!!  for boundary case !!!! for 0 , 00 , 000
        }
    }

    public void multipleOps(Value prevval, int curval, Queue<Value> val) {
        int secondOp = prevval.lastVal;
        int firstOp = prevval.val - prevval.lastVal;
        val.offer(new Value((firstOp + secondOp * curval), (secondOp * curval)));
    }

    public static void main(String[] args) {
        AddOperators ob = new AddOperators();
        System.out.println(ob.addOperators("105", 5));
    }
}
