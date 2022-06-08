import java.util.*;

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

public class Find_Duplicate_Subtrees_652 {

}

class Solution {
    List<TreeNode> result;

    public List<TreeNode> findDuplicateSubtrees(TreeNode root) {
        HashMap<Integer, List<TreeNode>> tracker = new HashMap<>();
        dfs(root, tracker);
        result = new ArrayList<>();

        second_dfs(root, tracker);

        return result;
    }

    private void second_dfs(TreeNode root, HashMap<Integer, List<TreeNode>> tracker) {
        if (root == null)
            return;

        for (TreeNode curr : tracker.get(root.val)) {
            if (curr == root)
                continue;

            if (isSameTree(root, curr)) {
                result.add(root);
                return;
            }
        }

    }

    private boolean isSameTree(TreeNode first, TreeNode second) {
        if (first == null && second == null)
            return true;

        if (first == null || second == null)
            return false;

        return first.val == second.val && isSameTree(first.left, second.left) && isSameTree(first.right, second.right);

    }

    private void dfs(TreeNode r, HashMap<Integer, List<TreeNode>> tracker) {
        if (r == null)
            return;

        tracker.putIfAbsent(r.val, new ArrayList<>());
        tracker.get(r.val).add(r);
        dfs(r.left, tracker);
        dfs(r.right, tracker);
    }
}