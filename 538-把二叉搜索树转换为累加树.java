import java.util.*;
import javafx.util.*;
// 给定一个二叉搜索树（Binary Search Tree），
// 把它转换成为累加树（Greater Tree)，使得每个节点的值是原来的节点值加上所有大于它的节点值之和。

// 例如：

// 输入: 二叉搜索树:
//               5
//             /   \
//            2     13

// 输出: 转换为累加树:
//              18
//             /   \
//           20     13
//
// Definition for a binary tree node.
// public class TreeNode {
//    int val;
//    TreeNode left;
//    TreeNode right;
//    TreeNode(int x) { val = x; }
// } 

// 我们可以中序遍历二叉树把它变成递增的数组，然后从大到小地更新把数组变成累加的，
// 最后再遍历一次二叉树更新节点的值；
// 我们发现，更新一个节点只要知道比它大的节点的和，既然如此，
// 我们一边遍历一边更新节点也是可以的，只不过从最大值开始，需要反中序遍历。

class Solution {
    public TreeNode convertBST(TreeNode root) {
        var stack = new LinkedList<Pair<TreeNode, Boolean>>();
        stack.offer(new Pair<>(root, false));
        var sum = 0;
        while (!stack.isEmpty()) {
            var node = stack.peekLast().getKey();
            var visited = stack.peekLast().getValue();
            stack.removeLast();
            if (node == null) {
                continue;
            }

            if (visited) {
                // 更新节点
                node.val += sum;
                sum = node.val;
            } else {
                // 入栈顺序“左-中-右”，出栈遍历顺序是“右-中-左”，即反中序遍历
                stack.offer(new Pair<>(node.left, false));
                stack.offer(new Pair<>(node, true));
                stack.offer(new Pair<>(node.right, false));
            }
        }

        return root;
    }
}