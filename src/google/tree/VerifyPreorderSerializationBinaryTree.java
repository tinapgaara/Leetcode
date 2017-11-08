package google.tree;

/**
 * Created by yingtan on 2/26/17.
 * 331. Verify Preorder Serialization of a Binary Tree
 *
 * One way to serialize a binary tree is to use pre-order traversal. When we encounter a non-null node, we record the node's value. If it is a null node, we record using a sentinel value such as #.

 _9_
 /   \
 3     2
 / \   / \
 4   1  #  6
 / \ / \   / \
 # # # #   # #
 For example, the above binary tree can be serialized to the string "9,3,4,#,#,1,#,#,2,#,6,#,#", where # represents a null node.

 Given a string of comma separated values, verify whether it is a correct preorder traversal serialization of a binary tree. Find an algorithm without reconstructing the tree.

 Each comma separated value in the string must be either an integer or a character '#' representing null pointer.

 You may assume that the input format is always valid, for example it could never contain two consecutive commas such as "1,,3".

 Example 1:
 "9,3,4,#,#,1,#,#,2,#,6,#,#"
 Return true

 Example 2:
 "1,#"
 Return false

 Example 3:
 "9,#,#,1"
 Return false



 */
public class VerifyPreorderSerializationBinaryTree {

    Integer pos = -1;
    public boolean isValidSerialization(String preorder) {
        String[] list = preorder.split(",");
        if ( (recurValid(list)) && (pos == list.length - 1) ) return true;
        else return false;
    }

    // preorder:
    // node0 ( node1 node1left node1right node2 node2left node2right ) ()
    public boolean recurValid(String[] list) {
        pos ++;
        if (pos >= list.length) return false;
        if (list[pos].equals("#")) return true;

        return recurValid(list) &&  // left branch is valid
                recurValid(list); // right branch is valid
    }
}
