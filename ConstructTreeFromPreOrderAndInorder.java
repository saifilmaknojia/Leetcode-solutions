import java.util.*;

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
        val = x;
    }
}

class ConstructTreeFromPreOrderAndInorder {
    int idx = 0;
    HashMap<Integer, Integer> inMap = new HashMap<Integer, Integer>();

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        for (int i = 0; i < inorder.length; i++) {
            inMap.put(inorder[i], i);
        }
        return constructTree(preorder, inorder, 0, preorder.length - 1);
    }

    TreeNode constructTree(int[] pre, int[] in, int s, int e) {
        // System.out.println(idx);
        if (s > e || idx > pre.length)
            return null;
        TreeNode temp = new TreeNode(pre[idx++]);

        int track = inMap.get(temp.val);
        // System.out.println(in[track]);
        temp.left = constructTree(pre, in, s, track - 1);
        temp.right = constructTree(pre, in, track + 1, e);

        return temp;
    }
}