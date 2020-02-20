import java.util.LinkedList;
import java.util.Queue;

// 给定一个二叉树，找出其最小深度。
// 最小深度是从根节点到最近叶子节点的最短路径上的节点数量。

// 说明: 叶子节点是指没有子节点的节点。

// 示例:
// 给定二叉树 [3,9,20,null,null,15,7],

//     3
//    / \
//   9  20
//     /  \
//    15   7
// 返回它的最小深度  2.
//
// Definition for a binary tree node.
// public class TreeNode {
//    int val;
//    TreeNode left;
//    TreeNode right;
//    TreeNode(int x) { val = x; }
// } 

class Solution { // BFS 最快
    public int minDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        var depth = 1;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            var len = queue.size();
            for (int i = 0; i < len; i++) {
                var cur = queue.poll();
                if (cur.left == null && cur.right == null) {
                    return depth; // 发现叶子节点，立刻返回深度
                }

                if (cur.left != null) {
                    queue.offer(cur.left);
                }
                if (cur.right != null) {
                    queue.offer(cur.right);
                }
            }

            depth++; // 搜索完每一层都要更新深度
        }

        return depth; // 其实永远都不会运行到这
    }
}