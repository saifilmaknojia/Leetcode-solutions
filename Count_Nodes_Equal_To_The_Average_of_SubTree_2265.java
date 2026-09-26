public class Count_Nodes_Equal_To_The_Average_of_SubTree_2265 {
    
}


class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode() {}
    TreeNode(int val) { this.val = val; }
    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}


class Solution {
    int ans = 0;

    public int averageOfSubtree(TreeNode root) {
        int[] arr = new int[2];
        // where arr[0] = sum of subtree nodes
        // and arr[1] = number of nodes in the subtree for the current node

        dfs(root, arr);
        return ans;
    }

    private int[] dfs(TreeNode node, int[] arr) {
        if (node == null)
            return new int[2];

        int[] leftST = dfs(node.left, arr);
        int[] rightST = dfs(node.right, arr);

        int sumOfST = leftST[0] + rightST[0] + node.val;
        int numberOfNodesInST = leftST[1] + rightST[1] + 1;

        if ((sumOfST / numberOfNodesInST) == node.val)
            ans++;

        return new int[] { sumOfST, numberOfNodesInST};
    }
}