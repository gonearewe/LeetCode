import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

// 给定一个二叉树，返回其节点值的锯齿形层次遍历。（即先从左往右，再从右往左进行下一层遍历，以此类推，层与层之间交替进行）。

// 例如：
// 给定二叉树 [3,9,20,null,null,15,7],

//     3
//    / \
//   9  20
//     /  \
//    15   7
// 返回锯齿形层次遍历如下：

// [
//   [3],
//   [20,9],
//   [15,7]
// ]
//
// Definition for a binary tree node.
// public class TreeNode {
//    int val;
//    TreeNode left;
//    TreeNode right;
//    TreeNode(int x) { val = x; }
// } 
class Solution {
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        var res = new ArrayList<List<Integer>>();
        if (root == null) {
            return res;
        }

        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.offer(root);
        var depth = 0;
        while (!queue.isEmpty()) {
            var level = new LinkedList<Integer>();
            var len = queue.size();
            for (int i = 0; i < len; i++) {
                var cur = queue.poll();
                if (depth % 2 == 0) {
                    level.offer(cur.val);
                } else {
                    level.push(cur.val);
                }

                if (cur.left != null) {
                    queue.offer(cur.left);
                }
                if (cur.right != null) {
                    queue.offer(cur.right);
                }
            }

            res.add(level);
            depth++;
        }

        return res;
    }
}