public class Delete_Node_in_a_BST_450 {

}

// Definition for a binary tree node.
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode() {
    }

    TreeNode(int val) {
        this.val = val;
    }

    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}

class Solution {
    // single child - explore that child
    // no child - just delete
    // 2 children - choose any
    boolean found_node = false;

    public TreeNode deleteNode(TreeNode root, int key) {
        if (root == null)
            return null;

        if (root.val == key) {
            if (root.left != null) {
                TreeNode replace = findLargestOnLeft(root, root.left, true);
                TreeNode right_tree = root.right;
                TreeNode left_tree = root.left;

                System.out.println(replace.val);

                if (left_tree != replace)
                    replace.left = left_tree;

                replace.right = right_tree;

                return replace;

            } else if (root.right != null) {
                TreeNode replace = findSmallestOnRight(root, root.right, false);
                TreeNode right_tree = root.right;
                TreeNode left_tree = root.left;

                replace.left = left_tree;

                if (right_tree != replace)
                    replace.right = right_tree;

                return replace;
            } else
                return null;
        }

        dfs_search(root, null, key, false);

        return root;
    }

    private void dfs_search(TreeNode curr, TreeNode parent, int key, boolean isLeft) {
        if (curr == null || found_node)
            return;

        if (curr.val == key) {
            rearrange(curr, parent, isLeft);
            found_node = true;
        } else if (curr.val > key)
            dfs_search(curr.left, curr, key, true);
        else
            dfs_search(curr.right, curr, key, false);
    }

    private void rearrange(TreeNode toRemove, TreeNode parent, boolean location) {
        // TreeNode temp = null;
        System.out.println("PArent is " + parent.val + " On ==> " + location);
        // No children
        if (toRemove.left == null && toRemove.right == null) {
            if (location)
                parent.left = null;
            else
                parent.right = null;

            return;
        } else if (toRemove.right == null) {

            if (location)
                parent.left = toRemove.left;
            else
                parent.right = toRemove.left;

        } else if (toRemove.left == null) {

            if (location)
                parent.left = toRemove.right;
            else
                parent.right = toRemove.right;
        }

        else {
            // have both children then search for the biggest on the left, remove it and
            // replace the toRemoves value with it
            TreeNode replace = findSmallestOnRight(toRemove, toRemove.right, false);
            System.out.println(replace.val);

            TreeNode right_tree = toRemove.right;
            // TreeNode left_tree = toRemove.left;

            toRemove.val = replace.val;

            if (toRemove.val == right_tree.val)
                toRemove.right = right_tree.right;
        }

        return;
    }

    private TreeNode findLargestOnLeft(TreeNode parent, TreeNode curr, boolean isLeft) {
        if (curr == null)
            return null;

        System.out.println("here large on left");
        if (curr.left == null && curr.right == null) {
            TreeNode send = curr;
            System.out.println(" left hai kya? " + isLeft);
            if (isLeft)
                parent.left = null;
            else
                parent.right = null;

            return send;
        } else if (curr.right != null)
            return findLargestOnLeft(curr, curr.right, false);
        else
            return curr;
    }

    private TreeNode findSmallestOnRight(TreeNode parent, TreeNode curr, boolean isLeft) {
        if (curr == null)
            return null;

        if (curr.left == null && curr.right == null) {
            TreeNode send = curr;
            if (isLeft)
                parent.left = null;
            else
                parent.right = null;

            return send;
        } else if (curr.left != null)
            return findSmallestOnRight(curr, curr.left, true);
        else
            return curr;
    }
}