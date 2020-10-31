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

public class Count_Complete_Tree_Nodes_222 {
    // Binary search method
    int height;

    public int countNodes(TreeNode root) {
        if (root == null)
            return 0;

        height = depth(root);

        if (height == 1)
            return 1;

        int total_number_of_nodes_till_level_h_minus_1 = (int) Math.pow(2, height - 1) - 1;

        // now we will find number of nodes on leaf level using binary search
        int last_level_total_possible = (int) Math.pow(2, height - 1);
        int start = 1;
        int end = last_level_total_possible;

        int best = -1;
        while (start <= end) {
            int mid = (end - start) / 2 + start;
            // System.out.println(start + " end "+end + " mid = "+mid);
            if (binarySearch(root, mid, 0, 1, last_level_total_possible)) {
                start = mid + 1;
                // System.out.println("true "+mid);
                best = Math.max(best, mid);
            } else
                end = mid - 1;
        }
        // System.out.println("ans "+best);
        return total_number_of_nodes_till_level_h_minus_1 + best;
    }

    private int depth(TreeNode r) {
        if (r == null)
            return 0;

        return depth(r.left) + 1;
    }

    private boolean binarySearch(TreeNode r, int idx, int level, int start, int end) {
        if (level == height - 1)
            return r != null;

        int center = (end - start) / 2 + start;
        if (idx > center)
            return binarySearch(r.right, idx, level + 1, center + 1, end);
        else
            return binarySearch(r.left, idx, level + 1, start, center - 1);
    }
}