import java.util.LinkedList;
import java.util.Queue;

// 给定一个二叉树，根节点为第1层，深度为 1。在其第 d 层追加一行值为 v 的节点。

// 添加规则：给定一个深度值 d （正整数），针对深度为 d-1 层的每一非空节点 N，为 N 创建两个值为 v 的左子树和右子树。
// 将 N 原先的左子树，连接为新节点 v 的左子树；将 N 原先的右子树，连接为新节点 v 的右子树。
// 如果 d 的值为 1，深度 d - 1 不存在，则创建一个新的根节点 v，原先的整棵树将作为 v 的左子树。

// 示例 1:

// 输入: 
// 二叉树如下所示:
//        4
//      /   \
//     2     6
//    / \   / 
//   3   1 5   

// v = 1
// d = 2

// 输出: 
//        4
//       / \
//      1   1
//     /     \
//    2       6
//   / \     / 
//  3   1   5   

// 示例 2:

// 输入: 
// 二叉树如下所示:
//       4
//      /   
//     2    
//    / \   
//   3   1    

// v = 1
// d = 3

// 输出: 
//       4
//      /   
//     2
//    / \    
//   1   1
//  /     \  
// 3       1
// 注意:

// 1. 输入的深度值 d 的范围是：[1，二叉树最大深度 + 1]。
// 2. 输入的二叉树至少有一个节点。
//
// Definition for a binary tree node.
// public class TreeNode {
//    int val;
//    TreeNode left;
//    TreeNode right;
//    TreeNode(int x) { val = x; }
// } 

class Solution {
    public TreeNode addOneRow(TreeNode root, int v, int d) {
        if (d == 1) { // 判别根节点的特殊情况
            var node = new TreeNode(v);
            node.left = root;
            return node;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        var depth = 1;
        while (!queue.isEmpty()) { // 开始层次遍历
            var len = queue.size();
            for (int i = 0; i < len; i++) {
                var node = queue.poll();

                if (depth == d - 1) { // 到达指定深度的上层，开始处理
                    var l = new TreeNode(v);
                    var r = new TreeNode(v);
                    // 先搭接原本的子树
                    if (node.left != null) {
                        l.left = node.left;
                    }
                    if (node.right != null) {
                        r.right = node.right;
                    }

                    // 左右都插入新节点
                    node.left = l;
                    node.right = r;

                    continue;
                    // 已经到达工作深度，不会继续下降遍历了，跳过子节点入队操作
                }

                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }

            if (depth == d - 1) {
                return root; // 刚刚完成了处理，直接退出，不再继续遍历
            }

            depth++;
        }

        return root; // 对于有效的测试用例，不会到达这儿
    }
}