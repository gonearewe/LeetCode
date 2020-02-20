import java.util.*;
import java.lang.*;

// 给定一个非空二叉树, 返回一个由每层节点平均值组成的数组.

// 示例 1:
// 输入:
//     3
//    / \
//   9  20
//     /  \
//    15   7

// 输出: [3, 14.5, 11]
// 解释:
// 第0层的平均值是 3,  第1层是 14.5, 第2层是 11. 因此返回 [3, 14.5, 11].
// 注意：

// 节点值的范围在32位有符号整数范围内。

// Definition for a binary tree node.
// public class TreeNode {
//    int val;
//    TreeNode left;
//    TreeNode right;
//    TreeNode(int x) { val = x; }
// } 
class Solution {
    public List<Double> averageOfLevels(TreeNode root) {
        var res = new ArrayList<Double>();
        if (root == null) {
            return res;
        }

        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            var sum = 0;
            var len = queue.size();
            for (int i = 0; i < len; i++) {
                var cur = queue.poll();
                sum += cur.val;

                if (cur.left != null) {
                    queue.offer(cur.left);
                }
                if (cur.right != null) {
                    queue.offer(cur.right);
                }
            }

            res.add(sum * 1.0 / len);
        }

        return res;
    }
}