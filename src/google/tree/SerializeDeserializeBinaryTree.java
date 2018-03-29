package google.tree;

import tree.TreeNode;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * Created by yingtan on 2/26/17.
 *
 * 297. Serialize and Deserialize Binary Tree
 *
 */
public class SerializeDeserializeBinaryTree {

    // Sol 1 : BFS level order
    public String seri_bfs(TreeNode root) {
        if (root == null) return "";
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.offer(root);
        StringBuilder str = new StringBuilder();
        while(! queue.isEmpty()) {
            TreeNode cur = queue.poll();
            if(cur != null) {
                str.append(cur.val + ",");
                queue.offer(root.left);
                queue.offer(root.right);
            }
            else {
                str.append("#,");
            }
        }
        str.deleteCharAt(str.length()-1);
        return str.toString();
    }
    // memory exceed limits
    public TreeNode des_bfs(String data) {
        if (data.isEmpty()) return null;
        String[] list = data.split(",");
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        TreeNode root = new TreeNode(Integer.parseInt(list[0]));
        queue.offer(root);
        for (int i = 1; i < list.length; i ++) {
            TreeNode parent = queue.poll();
            String leftVal = list[i];
            if (! leftVal.equals("#")) {
                TreeNode left = new TreeNode(Integer.parseInt(leftVal));
                queue.offer(left);
                parent.left = left;
            }
            i ++ ;
            if(i < list.length) {
                String rightVal = list[i];
                if (! rightVal.equals("#")) {
                    TreeNode right = new TreeNode(Integer.parseInt(rightVal));
                    queue.offer(right);
                    parent.right = right;
                }
            }
        }
        return root;
    }

    // Sol 2: use preorder (recursion)
    public String seri_dfs(TreeNode root) {
        // Important !!!
        if (root == null) return "#";
        StringBuilder str = new StringBuilder();
        str.append(root.val + ",");

        str.append(seri_dfs(root.left) + "," + seri_dfs(root.right));
        return str.toString();
    }

    public TreeNode deser_dfs(String data) {
        if (data.isEmpty()) return null;
        String[] list = data.split(",");
        int[] index = new int[1];
        index[0] = 0;
        return recurDfs(list, index);
    }

    public TreeNode recurDfs(String[] list, int[] index) {
        if (index[0] >= list.length) return null;
        if (list[index[0]].equals("#")) return null;

        TreeNode cur = new TreeNode(Integer.parseInt(list[index[0]]));
        index[0] ++;
        TreeNode left = recurDfs(list, index);
        // Important !!!
        index[0] ++;
        TreeNode right = recurDfs(list, index);
        cur.left = left;
        cur.right = right;
        return cur;
    }

    // Sol 3: use preorder: iteration
    // Encodes a tree to a single string.
    public String serialize_iter(TreeNode root) {
        if(root==null)
            return null;

        Stack<TreeNode> stack = new Stack<TreeNode>();
        stack.push(root);
        StringBuilder sb = new StringBuilder();

        while(!stack.isEmpty()){
            TreeNode h = stack.pop();
            if(h!=null){
                sb.append(h.val+",");
                stack.push(h.right);
                stack.push(h.left);
            }else{
                sb.append("#,");
            }
        }

        return sb.toString().substring(0, sb.length()-1);
    }
    // Decodes your encoded data to tree.
    public TreeNode deserialize_iter(String data) {
        if(data == null)

            return null;

        int[] t = {0};
        String[] arr = data.split(",");

        return helper(arr, t);
    }
    public TreeNode helper(String[] arr, int[] t){
        if(arr[t[0]].equals("#")){
            return null;
        }
        TreeNode root = new TreeNode(Integer.parseInt(arr[t[0]]));
        t[0]=t[0]+1;
        root.left = helper(arr, t);
        t[0]=t[0]+1;
        root.right = helper(arr, t);

        return root;
    }

    // Sol 3: use preorder (recursion)
    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        if (root  == null) return "#";
        String res = "" + root.val;
        String left = serialize(root.left);
        String right = serialize(root.right);

        return res + "," + left + "," + right;
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        String[] list = data.split(",");
        Queue<String> queue = new LinkedList<String>();
        for(int i = 0;  i < list.length; i ++) {
            queue.offer(list[i]);
        }
        return buildTree(queue);
    }

    public TreeNode buildTree(Queue<String> queue) {
        String first = queue.remove();
        if (first.equals("#")) {
            return null;
        }
        else {
            TreeNode node = new TreeNode(Integer.parseInt(first));
            node.left = buildTree(queue);
            node.right = buildTree(queue);

            return node;
        }
    }

    public static void main(String[] args) {
        SerializeDeserializeBinaryTree ob = new SerializeDeserializeBinaryTree();
        //ob.serialize_BFS(ob.des_bfs("1,2,#"));
    }
}
