package google.DFS;

/**
 * Created by yingtan on 11/6/15.
 */
/*
* input:⼀一个字符串可能含有 ‘*’，‘*’可以代表'j' 或'k'
要求打印所有可能的字符串

// Important !!!!

// in-place: backtracing
* */
public class PrintStrs {

    public void printStrs(String str) {
        recurPrintStrs(str, 0);
    }

    public void recurPrintStrs(String str, int index) {
        if (index == str.length() -1) {
            System.out.println(str);
            return;
        }
        char[] chs = str.toCharArray();
        if (str.charAt(index) == '*') {
            chs[index] = 'j';
            recurPrintStrs(new String(chs), index+1);
            chs[index] = 'k';
            recurPrintStrs(new String(chs), index+1);

        }
        else {
            recurPrintStrs(str, index+1);
        }

    }

}
