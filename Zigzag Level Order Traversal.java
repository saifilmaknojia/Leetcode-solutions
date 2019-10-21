
/**
 * Definition for a binary tree node. public class TreeNode { int val; TreeNode
 * left; TreeNode right; TreeNode(int x) { val = x; } }
 */
import java.util.*;

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
        val = x;
    }
}

class Zigzag_Level_Order_Traversal {

    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> outer = new ArrayList<>();
        List<Integer> inner = new ArrayList<>();
        if (root == null)
            return outer;

        LinkedList<TreeNode> tracker = new LinkedList<TreeNode>();
        LinkedList<TreeNode> tracker_next = new LinkedList<TreeNode>();
        boolean right_to_left = false;
        tracker.add(root);

        while (!tracker.isEmpty()) {
            TreeNode temp = tracker.removeLast();
            if (right_to_left) {
                // System.out.println("hello");
                if (temp.right != null)
                    tracker_next.add(temp.right);
                if (temp.left != null)
                    tracker_next.add(temp.left);

                // System.out.println(tracker_next);
            } else {
                if (temp.left != null)
                    tracker_next.add(temp.left);
                if (temp.right != null)
                    tracker_next.add(temp.right);
            }

            inner.add(temp.val);
            // System.out.println("inner -- "+inner);

            if (tracker.isEmpty()) {
                tracker = tracker_next;
                tracker_next = new LinkedList<TreeNode>();
                outer.add(inner);
                inner = new ArrayList<>();
                right_to_left = !right_to_left;
                // System.out.println(right_to_left);
            }

        }

        return outer;
    }
}