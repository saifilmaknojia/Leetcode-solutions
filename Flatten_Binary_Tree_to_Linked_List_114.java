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

public class Flatten_Binary_Tree_to_Linked_List_114 {
    // using extra memory, one option that comes to mind, is take an arraylist and
    // traverse the tree in pre-order fashion (root-left-right) and store all the
    // elements in the arraylist
    // then set root as null and then using the list recreate the root tree
    TreeNode prev = null;

    public void flatten(TreeNode root) {
        if (root == null)
            return;

        flatten(root.right);
        flatten(root.left);

        root.right = prev;
        root.left = null;
        prev = root;
    }

    /*
     * Few Test cases [1,2,5,3,4,null,6]
     * [1,2,5,null,null,7,null,8,6,null,3,null,11,null,14,15,null]
     * [8,6,78,3,null,11,null,14,15,null]
     * [1,2,5,3,4,99,6,null,null,14,null,17,null,24,null,99,null,81,null,14,null,54,
     * null,87]
     * [1,4,null,5,null,6,null,7,null,8,null,9,10,11,null,13,null,15,null,16,null,18
     * ,null,25,27]
     */

    public void flatten_mine(TreeNode root) {
        performRightFirstDFS(root);
    }

    // TreeNode[0] = right, TreeNode[1] = prev-last- to attach
    public TreeNode[] performRightFirstDFS(TreeNode r) {
        if (r == null)
            return null;

        TreeNode[] left = performRightFirstDFS(r.left);
        TreeNode[] right = performRightFirstDFS(r.right);

        if (left != null) {
            r.right = left[0];
            r.left = null;
            if (left[1] != null && right != null) {
                left[1].right = right[0];
                left[1].left = null;
            } else if (right != null) {
                left[0].right = right[0];
                left[0].left = null;
            }
        }
        TreeNode temp = null;
        if (left == null && right == null)
            temp = r;
        else if (right != null)
            temp = right[1] != null ? right[1] : right[0];
        else
            temp = left[1] != null ? left[1] : left[0];

        return new TreeNode[] { r, temp };

    }
}
