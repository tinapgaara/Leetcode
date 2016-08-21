package google.graph;

/**
 * Created by yingtan on 11/5/15.
 */
/*
* ⼀一堆密码箱，每个密码都是四位0-9之间，算⼀一个暴⼒力破解序
列，包含所有可能的四位序列，让这个序列尽量短
* http://www.geeksforgeeks.org/backtracking-set-7-hamiltonian-cycle/
* */
public class HamiltonCycle {

    public String generateCode(int digitNum) {
        String str = "";
        boolean[] visitedFlags = new boolean[10];
        return recurGenerateCode(digitNum, 0, str, visitedFlags);
    }

    public String recurGenerateCode(int digitNum, int cur, String str, boolean[] visitedFlags) {
        // need to judge if has such edge connected , and if the last node
        if (cur == digitNum) {
            return str;
        }

        for (int i = 0; i <= 9 ; i ++) {
            if (!visitedFlags[i]) {
                String newstr = str + i;
                visitedFlags[i] = true;
                String res = recurGenerateCode(digitNum, cur + 1, newstr, visitedFlags);
                if (res != null) {
                    return res;
                }
                else {
                    visitedFlags[i] = false;
                }
            }
        }
        return null;
    }

    public static void main(String[] args) {
        HamiltonCycle ob = new HamiltonCycle();
        System.out.println(ob.generateCode(5));
    }
}
