import java.util.ArrayList;
import java.util.LinkedList;

// 给定一个二叉树，返回其按层次遍历的节点值。 （即逐层地，从左到右访问所有节点）。

// 例如:
// 给定二叉树: [3,9,20,null,null,15,7],

//     3
//    / \
//   9  20
//     /  \
//    15   7
// 返回其层次遍历结果：

// [
//   [3],
//   [9,20],
//   [15,7]
// ]
//
// Definition for a binary tree node.
// public class TreeNode {
//     int val;
//     TreeNode left;
//     TreeNode right;
//     TreeNode(int x) { val = x; }
// }

class Solution {
    public List<List<Integer>> levelOrder(TreeNode root) {
        var result = new ArrayList<List<Integer>>();
        if (root == null) {
            return result;
        }

        var queue = new LinkedList<TreeNode>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            var subresult = new ArrayList<Integer>();
            result.add(subresult);
            var len = queue.size();
            for (int i = 0; i < len; i++) {
                var node = queue.poll();
                subresult.add(node.val);
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
        }

        return result;
    }
}
