import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

import javax.swing.tree.TreeNode;

// 给定一个二叉树，返回其节点值自底向上的层次遍历。 （即按从叶子节点所在层到根节点所在的层，逐层从左向右遍历）

// 例如：
// 给定二叉树 [3,9,20,null,null,15,7],

//     3
//    / \
//   9  20
//     /  \
//    15   7
// 返回其自底向上的层次遍历为：

// [
//   [15,7],
//   [9,20],
//   [3]
// ]

// Definition for a binary tree node.
// public class TreeNode {
//    int val;
//    TreeNode left;
//    TreeNode right;
//    TreeNode(int x) { val = x; }
// } 
class Solution { // 层次遍历肯定是从上到下的，那么就把结果中下一层的值放在上一层的前面
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        var res = new LinkedList<List<Integer>>(); // 外层用链表，前端插入开销小
        if (root == null) {
            return res;
        }

        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            var len = queue.size();
            var level = new ArrayList<Integer>();
            for (int i = 0; i < len; i++) {
                var node = queue.poll();
                level.add(node.val);

                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }

            res.push(level);
        }

        return res;
    }
}