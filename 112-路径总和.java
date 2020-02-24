import java.util.*;
import javafx.util.*;
// 给定一个二叉树和一个目标和，判断该树中是否存在根节点到叶子节点的路径，这条路径上所有节点值相加等于目标和。

// 说明: 叶子节点是指没有子节点的节点。

// 示例: 
// 给定如下二叉树，以及目标和 sum = 22，

//               5
//              / \
//             4   8
//            /   / \
//           11  13  4
//          /  \      \
//         7    2      1
// 返回 true, 因为存在目标和为 22 的根节点到叶子节点的路径 5->4->11->2。
//
// Definition for a binary tree node.
// public class TreeNode {
//    int val;
//    TreeNode left;
//    TreeNode right;
//    TreeNode(int x) { val = x; }
// } 

class Solution {
    // 这样写是不行的，考虑一下 [1,2] 1 这组测试用例，它从左子树下去碰到 null，
    // 此时误判为 true，但是根节点不是叶子节点
    // public boolean hasPathSum(TreeNode root, int sum) {
    // if (root == null) {
    // return sum == 0;
    // }

    // return hasPathSum(root.left, sum - root.val) || hasPathSum(root.right, sum -
    // root.val);
    // }
    public boolean hasPathSum(TreeNode root, int sum) {
        if (root == null) { // 非叶子节点碰到 null，直接忽略
            return false;
        }

        if (root.left == null && root.right == null) { // 到达叶子节点，进行判断
            return sum == root.val;
        }

        // 向下递归
        return hasPathSum(root.left, sum - root.val) || hasPathSum(root.right, sum - root.val);
    }

    // 迭代法
    public boolean hasPathSumNonRecursively(TreeNode root, int sum) {
        var stack = new Stack<Pair<TreeNode, Boolean>>();
        var sumStack = new Stack<Integer>();
        stack.push(new Pair<>(root, false));
        sumStack.push(sum);
        while (!stack.isEmpty()) {
            int remaining = sumStack.pop();
            var elem = stack.pop();
            var node = elem.getKey();
            var visited = elem.getValue();
            if (node == null) {
                continue;
            }

            if (visited) {
                if (node.left == null && node.right == null && remaining == node.val) {
                    return true;
                }
            } else {
                stack.push(new Pair<>(node, true));
                stack.push(new Pair<>(node.right, false));
                stack.push(new Pair<>(node.left, false));

                sumStack.push(remaining);
                sumStack.push(remaining - node.val);
                sumStack.push(remaining - node.val);
            }
        }

        return false;
    }
}