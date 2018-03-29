package linkedin.impl;

import tree.TreeNode;

/**
 * Created by yingtan on 11/29/17.
 */
public class BST {

    public TreeNode root;

    public void insert(TreeNode node, TreeNode root) {
        if (node.val < root.val) {
            if (root.left == null) {
                root.left = node;
            }
            else {
                insert(node, root.left);
            }
        }
        else {
            if (root.right == null) {
                root.right = node;
            }
            else {
                insert(node, root.right);
            }
        }
    }

    public TreeNode deleteNode(TreeNode root, int value) {
        if (root == null)
            return null;
        if (root.val > value) {
            root.left = deleteNode(root.left, value);
        } else if (root.val < value) {
            root.right = deleteNode(root.right, value);

        } else {
            // if nodeToBeDeleted have both children
            if (root.left != null && root.right != null) {
                TreeNode temp = root;
                // Finding minimum element from right
                TreeNode minNodeForRight = min(temp.right);
                // Replacing current node with minimum node from right subtree
                root.val = minNodeForRight.val;
                // Deleting minimum node from right now
                deleteNode(root.right, minNodeForRight.val);

            }
            // if nodeToBeDeleted has only left child
            else if (root.left != null) {
                root = root.left;
            }
            // if nodeToBeDeleted has only right child
            else if (root.right != null) {
                root = root.right;
            }
            // if nodeToBeDeleted do not have child (Leaf node)
            else
                root = null;
        }
        return root;
    }

    public TreeNode min(TreeNode node) { //o(h)
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }

    public TreeNode max(TreeNode node) { //o(h)
        while (node.right != null) {
            node = node.right;
        }
        return node;
    }

    public int search(TreeNode node, TreeNode root) {
        if (node.val < root.val) {
            return search(node, root.left);
        }
        else if (node.val > root.val) {
            return search(node, root.right);
        }
        else
            return root.val;
    }



    public void delete(TreeNode node, TreeNode root) {
        if (node.left == null) {
            transplant(root, node, node.right);
        }
        else if (node.right == null) {
            transplant(root, node, node.left);
        }
        else {
            // find predecessor of current node
            TreeNode pred = searchPredecessor(node);
            node.val = pred.val;
            delete(pred, root); // delete this node
        }
    }

    // move attached node to node's parent , when deleting node
    public TreeNode transplant(TreeNode root, TreeNode node, TreeNode attachedNode) {
        TreeNode parent = node.parent;

        if (parent == null) {
            root = attachedNode;
            return root;
        }
        else {
            if(parent.right == node) { // node is right child of parent
                parent.right = attachedNode;
            }
            else {
                parent.left = attachedNode;
            }
        }
        // change attachedNode's parent node
        if (attachedNode != null) {
            attachedNode.parent = parent;
        }
        return root;
    }

    public TreeNode searchSuccessor(TreeNode node) { // o(h)
        if (node.right != null) {
            return min(node.right);
        }
        else {
            TreeNode parent = node.parent;
            if ((parent != null) && (parent.right == node)) {
                node = parent;
                parent = node.parent;
            }
            return parent;
        }
    }

    public TreeNode searchPredecessor(TreeNode node) { //o(h)
        if (node.left != null) {
            return max(node.left);
        }
        else {
            TreeNode parent = node.parent;
            if ((parent != null) && (parent.left == node)) {
                node = parent;
                parent = node.parent;
            }
            return parent;
        }
    }
}
