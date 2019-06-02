/**
 * Definition for a binary tree node. public class TreeNode { int val; TreeNode
 * left; TreeNode right; TreeNode(int x) { val = x; } }
 */

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
        val = x;
    }
}

class Solution {
    public boolean isBalanced(TreeNode root) {
        if (root == null)
            return true;

        // return height(root.left, root.right, 0);
        // find the max height of the left subtree and that of right subtree
        // find the difference of both, if its less than 2, return true
        // else return false
        if ((Math.abs(height(root.left) - height(root.right)) <= 1 && isBalanced(root.left) && isBalanced(root.right)))
            return true;
        else
            return false;

        // return false;
    }

    int height(TreeNode temp) {
        if (temp == null)
            return 0;
        int left_h = 1 + height(temp.left);
        int right_h = 1 + height(temp.right);

        // if(Math.abs(left_h-right_h)>1)
        return Math.max(left_h, right_h);
    }

}