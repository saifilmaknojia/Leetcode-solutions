class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
        val = x;
    }
}

class Validate_BinarySearchTree {
    public boolean isValidBST(TreeNode root) {
        if (root == null)
            return true;

        return checkTree(root, null, null);
    }

    private boolean checkTree(TreeNode node, TreeNode min, TreeNode max) {
        if (node == null)
            return true;

        /*
         * checkTree(node.left, min, node); if(min != null && node.val < min || max !=
         * null && node.val > max) return false; checkTree(node.right, node, max);
         */
        // System.out.println(node.val);
        if ((max != null && node.val >= max.val) || (min != null && node.val <= min.val))
            return false;

        return checkTree(node.left, min, node) && checkTree(node.right, node, max);
    }
}