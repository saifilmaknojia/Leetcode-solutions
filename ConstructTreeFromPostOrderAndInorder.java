import java.util.*;

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
        val = x;
    }
}

class Solution {

    // This is very similar to the question, construct binary tree from inorder and
    // preorder traversal.
    // In preorder traversal, it is root -> left -> right
    // so we begin from index 0 and then add it to the tree, then search the value
    // in inorder array, and split it in left and right

    // similarly in postorder traversal, root comes at last, i.e left -> right ->
    // root
    // hence in this case we begin from the last index
    // and the other change is that instead of forming left tree first we form right
    // tree, since in post order traversal, before root, right comes.

    // In preorder, after root, left comes so we form left tree first

    // In short, the only change from preorder traversal, is instead of starting
    // from 0th index, we start from last index all our way to 0 and
    // we interchange order of recursion, i.e first right then left
    // line no - 39, 40

    int idx;
    HashMap<Integer, Integer> inMap = new HashMap<Integer, Integer>();

    public TreeNode buildTree(int[] inorder, int[] postorder) {
        idx = postorder.length - 1;
        for (int i = 0; i < inorder.length; i++) {
            inMap.put(inorder[i], i);
        }
        return constructTree(postorder, inorder, 0, postorder.length - 1);
    }

    TreeNode constructTree(int[] post, int[] in, int s, int e) {
        if (s > e || idx < 0)
            return null;
        TreeNode temp = new TreeNode(post[idx--]);

        int track = inMap.get(temp.val);
        // System.out.println(in[track]);
        temp.right = constructTree(post, in, track + 1, e);
        temp.left = constructTree(post, in, s, track - 1);
        return temp;
    }
}