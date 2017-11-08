package google.stack.jiuzhang;
import java.util.Stack;
/**
 * Created by yingtan on 11/4/17.
 */
public class DecodeString {

    public String expressionExpand(String s) {
        Stack<Object> stack = new Stack<>();
        char[] arr = s.toCharArray();

        int num = 0;
        for(char c : arr){
            if(Character.isDigit(c)){
                num = num * 10 + c - '0';
            }
            else if(c == '['){
                stack.push(Integer.valueOf(num));
                num = 0;
            }
            else if(c == ']'){
                popStack(stack);
            }
            else{
                stack.push(c);
            }
        }
        popStack(stack);
        return stack.pop().toString();
    }
    private void popStack(Stack<Object> stack){
        StringBuilder add = new StringBuilder();
        int count;
        Stack<Object> buffer = new Stack<Object>();
        while(!stack.isEmpty() && stack.peek() instanceof Integer == false){
            buffer.push(stack.pop());
        }
        while(!buffer.isEmpty()){
            add.append(buffer.pop());
        }

        count = stack.isEmpty()? 1 : (Integer) stack.pop();
        StringBuilder part = new StringBuilder(add);
        if(count > 0){
            for(int i = 0; i < count - 1; i++)
                add.append(part);
            stack.push(add);// reput
        }
    }
}
