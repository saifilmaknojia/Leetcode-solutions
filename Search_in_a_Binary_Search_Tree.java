/**
 * Definition for a binary tree node. public class TreeNode { int val; TreeNode
 * left; TreeNode right; TreeNode(int x) { val = x; } }
 */
class Search_in_a_Binary_Search_Tree {
    public TreeNode searchBST(TreeNode root, int val) {
        if (root == null)
            return root;
        if (root.val == val)
            return root;
        else
            root = root.val > val ? searchBST(root.left, val) : searchBST(root.right, val);
        return root;
    }
}