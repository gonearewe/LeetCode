// 给定一个二叉树，检查它是否是镜像对称的。

// 例如，二叉树 [1,2,2,3,4,4,3] 是对称的。

//     1
//    / \
//   2   2
//  / \ / \
// 3  4 4  3
// 但是下面这个 [1,2,2,null,3,null,3] 则不是镜像对称的:

//     1
//    / \
//   2   2
//    \   \
//    3    3
//
// Definition for a binary tree node.
// public class TreeNode {
//    int val;
//    TreeNode left;
//    TreeNode right;
//    TreeNode(int x) { val = x; }
// } 

class Solution {
    public boolean isSymmetric(TreeNode root) {
        if (root == null) {
            return true;
        }

        return symmetric(root.left, root.right);
    }

    private boolean symmetric(TreeNode a, TreeNode b) {
        if (a == null || b == null) {
            return a == null && b == null;
        }

        return a.val == b.val && symmetric(a.left, b.right) && symmetric(a.right, b.left);
    }
}
