import java.util.*;
// 给定一个有相同值的二叉搜索树（BST），找出 BST 中的所有众数（出现频率最高的元素）。

// 假定 BST 有如下定义：
// 1. 结点左子树中所含结点的值小于等于当前结点的值
// 2. 结点右子树中所含结点的值大于等于当前结点的值
// 3. 左子树和右子树都是二叉搜索树

// 例如：
// 给定 BST [1,null,2,2],

//    1
//     \
//      2
//     /
//    2
// 返回[2].

// 提示：如果众数超过1个，不需考虑输出顺序

// 进阶：你可以不使用额外的空间吗？（假设由递归产生的隐式调用栈的开销不被计算在内）
//
// Definition for a binary tree node.
// public class TreeNode {
//    int val;
//    TreeNode left;
//    TreeNode right;
//    TreeNode(int x) { val = x; }
// } 

// 遍历二叉树，用一个 Map 记录每个值出现的次数，最后遍历 Map 得出结果
class Solution {
    private Map<Integer, Integer> counts = new HashMap<Integer, Integer>();

    public int[] findMode(TreeNode root) {
        count(root);
        int max = 0;
        var res = new ArrayList<Integer>();
        for (var pair : counts.entrySet()) {
            var cnt = pair.getValue();
            var num = pair.getKey();
            if (cnt < max) {
                continue;
            }

            if (cnt > max) {
                max = cnt;
                res.clear();
                res.add(num);
            } else {
                res.add(num);
            }
        }

        return toArray(res);
    }

    private void count(TreeNode node) {
        if (node == null) {
            return;
        }

        var num = node.val;
        var cnt = counts.get(num);
        if (cnt == null) {
            counts.put(num, 1);
        } else {
            counts.put(num, cnt + 1);
        }

        count(node.left);
        count(node.right);
    }

    private int[] toArray(List<Integer> li) {
        var res = new int[li.size()];
        int i = 0;
        for (var e : li) {
            res[i] = e;
            i++;
        }
        return res;
    }
}
