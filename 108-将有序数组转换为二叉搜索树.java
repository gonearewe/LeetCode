import java.util.*;
import javafx.util.*;

// 将一个按照升序排列的有序数组，转换为一棵高度平衡二叉搜索树。
// 本题中，一个高度平衡二叉树是指一个二叉树每个节点 的左右两个子树的高度差的绝对值不超过 1。

// 示例:
// 给定有序数组: [-10,-3,0,5,9],
// 一个可能的答案是：[0,-3,9,-10,null,5]，它可以表示下面这个高度平衡二叉搜索树：

//       0
//      / \
//    -3   9
//    /   /
//  -10  5
//
// Definition for a binary tree node.
// public class TreeNode {
//    int val;
//    TreeNode left;
//    TreeNode right;
//    TreeNode(int x) { val = x; }
// } 

class Solution {
    private int[] nums;

    // 递归法建树
    public TreeNode sortedArrayToBST(int[] nums) {
        this.nums = nums;
        return dfs(0, nums.length - 1);
    }

    private TreeNode dfs(int left, int right) {
        if (left > right) {
            return null;
        }

        var rootIndex = (left + right) / 2;
        var node = new TreeNode(nums[rootIndex]);
        node.left = dfs(left, rootIndex - 1);
        node.right = dfs(rootIndex + 1, right);

        return node;
    }

    // 迭代法层次遍历
    public TreeNode sortedArrayToBSTNonRecursively(int[] nums) {
        if(nums.length==0){
            return null;
        }
        var root = new TreeNode(nums[mid(0, nums.length - 1)]);
        // 这两个队列是同步的，第一个存储树节点，是层次遍历的模板，
        // 第二个模拟递归时传递的额外消息（即对应树节点在数组里的覆盖范围）
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        Queue<Pair<Integer, Integer>> queueRange = new LinkedList<Pair<Integer, Integer>>();

        queue.offer(root);
        queueRange.offer(new Pair<>(0, nums.length - 1));
        while (!queueRange.isEmpty()) {
            var len = queueRange.size();
            for (int i = 0; i < len; i++) {
                // 两个队列操作完全同步，最好写在一起
                var node = queue.poll();
                var range = queueRange.poll();
                // 当前节点及其子树在数组中的覆盖范围，左右都是闭区间
                var left = range.getKey(); 
                var right = range.getValue();

                // 处理子节点
                var rootIndex = mid(left, right);
                if (left <= rootIndex - 1) { // 存在左孩子
                    node.left = new TreeNode(nums[mid(left, rootIndex - 1)]);
                    queue.offer(node.left);
                    queueRange.offer(new Pair<>(left, rootIndex - 1));
                }
                if (rootIndex + 1 <= right) { // 存在右孩子
                    node.right = new TreeNode(nums[mid(rootIndex + 1, right)]);
                    queue.offer(node.right);
                    queueRange.offer(new Pair<>(rootIndex + 1, right));
                }
            }
        }

        return root;
    }

    // 求中间节点，显然范围是偶数时中间节点有两种可选的实现，
    // 所以本题生成的树不唯一
    private static int mid(int left, int right) {
        return (left + right) / 2;
    }
}