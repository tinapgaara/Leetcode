package google.string;

/**
 * Created by yingtan on 11/1/15.
 */
public class WordAbbreviation {

    public String abbreviate(String word) {
        if (word == null) return null;
        if (word.length() < 2) {
            return word;
        }
        return word.charAt(0) + (word.length() -2) + word.charAt(word.length()-1) + "";
    }

    public boolean checkDuplicate(String[] dict, String word) {
        int len = word.length();
        for (int i = 0 ; i < dict.length ; i ++) {
            if (!word.equals(dict[i])) {
                if (word.length() == dict[i].length()) {
                    if (word.length() >= 2) {
                        if (word.charAt(0) == dict[i].charAt(0)) {
                            if (word.charAt(len - 1) == dict[i].charAt(len - 1)) {
                                return true;
                            }
                        }
                    }
                }
            }
        }
        // or can use hashmap to store : string word -> string abbreviate
        return false;
    }

    // follow up :这个method如果被访问千万遍，要怎么降复杂度？
    // save space then hashtable
    // point: 字符串存在hashtable, 一提降复杂度-> use trie instead
    // TODO:
    public void buildTrie(String[] dict) {

           /*
           *       null
           *      /    \
           *     A     B
           *    / \   /
           *   2  3  1
           *  /   \
           * C    C
           * (real string)
           *
           * at end of the path, (at the last node) -> store real string
           *
           * */
    }

    /*
    * 可以为 a1breviation a2reviation 1bbreviation
    abbrevia4 ab3via4 abbreviation 11n 12等，问（1）⼀一共有多少
    种缩写（2）输出所有这些缩写
    * */
    public void printAllAbbrievations(String word) {

    }
}
