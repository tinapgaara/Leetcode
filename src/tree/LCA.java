package tree;

/**
 * Created by yingtan on 9/1/15.
 */
public class LCA {

    // Solution 1: stack over flow
    /*
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) { // not ValidBST

        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        int[] count = new int[1];
        count[0] = 0;

        map = TraverseTree(root, map, count);

        return recurLCA(root, p, q, map);
    }

    public HashMap<Integer, Integer> TraverseTree
            (TreeNode root, HashMap<Integer, Integer> map, int[] count) {
        if (root == null) {
            return map;
        }
        map = TraverseTree(root.left, map, count);
        count[0] = count[0] + 1;
        map.put(root.val, count[0]);
        map = TraverseTree(root.right, map, count);

        return map;

    }

    public TreeNode recurLCA(TreeNode root, TreeNode p, TreeNode q, HashMap<Integer, Integer> map) {
        if ( (root == null) || (p == null) || (q == null) ) {
            return null;
        }

        int direction = ifLeftOrRight(root, p, q, map);
        if ( direction == -1) {
            return recurLCA(root.left, p, q, map);
        }
        else if (direction == 1) {
            return recurLCA(root.right, p, q, map);
        }
        else {
            return root;
        }
    }

    public int ifLeftOrRight(TreeNode root, TreeNode p, TreeNode q, HashMap<Integer, Integer> map) {
        // left:-1 right:1 or:0
        int qVal = q.val;
        int pVal = p.val;
        int rootVal = root.val;

        int indexq = map.get(qVal);
        int indexp = map.get(pVal);
        int indexRoot = map.get(rootVal);

        if ((indexq < indexRoot) && (indexp < indexRoot)) {
            return -1;
        }
        else if ((indexq > indexRoot) && (indexp > indexRoot)) {
            return 1;
        }
        else {
            return 0;
        }
    }
    */

    // Solution 2: cost time
    /*
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {

        if ( (root == null) || (p == null) || (q == null) ) {
            return null;
        }

        if ( ifExist(root.left, p) && ifExist(root.left, q)) {
            return lowestCommonAncestor(root.left, p, q);
        }
        else if (ifExist(root.right, p) && ifExist(root.right, q)) {
            return lowestCommonAncestor(root.right, p, q);
        }
        else {
            return root;
        }

    }

    public boolean ifExist(TreeNode root, TreeNode node) {
        if (root == null) {
            return false;
        }
        else if (root.val == node.val) {
            return true;
        }
        else if (ifExist(root.left, node) || ifExist(root.right, node)) {
            return true;
        }
        return false;
    }
    */

    // Solution 3: buttom to up
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) {
            return null;
        }
        TreeNode leftAncestor = lowestCommonAncestor(root.left, p, q);
        TreeNode rightAncestor = lowestCommonAncestor(root.right, p, q);

        if ( (root == p) || (root == q) ) {
            return root;
        }
        else if ( (leftAncestor == null) && (rightAncestor == null) ) {
            return null;
        }
        else if ( (leftAncestor != null) && (rightAncestor != null) ) {
            return root;
        }
        else if (rightAncestor != null) {
            return rightAncestor;
        }
        else if (leftAncestor != null) {
            return leftAncestor;
        }
        return null;
    }

    public static void main(String[] args) {
        LCA ob = new LCA();
        TreeNode root = new TreeNode(0);
        TreeNode l = new TreeNode(1);
        TreeNode r = new TreeNode(2);
        root.left = l;
        root.right = r;

        System.out.println(ob.lowestCommonAncestor(root, l,r).val);
    }
}
