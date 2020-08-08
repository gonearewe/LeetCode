// 二叉搜索树中的两个节点被错误地交换。
// 请在不改变其结构的情况下，恢复这棵树。

// 示例 1:
// 输入: [1,3,null,null,2]

//    1
//   /
//  3
//   \
//    2

// 输出: [3,1,null,null,2]

//    3
//   /
//  1
//   \
//    2

// 示例 2:
// 输入: [3,1,4,null,null,2]

//   3
//  / \
// 1   4
//    /
//   2

// 输出: [2,1,4,null,null,3]

//   2
//  / \
// 1   4
//    /
//   3

// 进阶:
// 使用 O(n) 空间复杂度的解法很容易实现。
// 你能想出一个只使用常数空间的解决方案吗？

// Definition for a binary tree node.
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode() {
    }

    TreeNode(int val) {
        this.val = val;
    }

    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}

class Solution { // Morris 中序遍历
    private TreeNode last = null, x = null, y = null;

    public void recoverTree(TreeNode root) {
        TreeNode cur = root;

        while (cur != null) {
            // 没有左孩子
            if (cur.left == null) {
                visit(cur);

                last = cur;
                cur = cur.right; // 向右走

                continue;
            }

            // 有左孩子
            var pred = predecessor(cur); // 后继子
            if (pred.right == cur) { // 说明这是第二次到达此结点，左孩子已经被遍历过了
                pred.right = null; // 去掉环

                visit(cur);

                last = cur;
                cur = cur.right;
            } else { // pred.right==null，说明这是第一次到达此结点
                // 不访问此结点，也不更新 last，直接左转
                pred.right = cur;
                cur = cur.left;
            }
        }

        swap(x, y);
    }

    private TreeNode predecessor(final TreeNode cur) {
        var node = cur.left;
        while (node.right != null && node.right != cur) { // 注意需要 node.right != cur 防止死循环
            node = node.right;
        }
        return node;
    }

    private void swap(TreeNode a, TreeNode b) {
        var tmp = a.val;
        // 直接修改树结点上的值
        a.val = b.val;
        b.val = tmp;
    }

    // 正常的中序遍历结果应该是升序的，异常有两种情况：
    //  1. 交换的数字不相邻
    //     假如中序遍历为 1 5 3 4 2 6，显然 2 与 5 位置反了，则遍历时会发现两处异常，先遍历到的 3 比
    //     前面的 5 小，后遍历到的 2 比前面的 4 小；
    //  2. 交换的数字相邻
    //     假如中序遍历为 1 3 2 4，显然 2 与 3 位置反了，那么遍历时只会发现一处异常，即 2 比 前面的 3 小。
    //
    // 那么我们可以设置两个变量 x,y，发现第一处异常时分别记录当前值与上一个值(5与3 或者 3与2），
    // 假如没有发现第二处异常(case 2)，直接交换此时的 x(3),y(2)，否则(case 1)把 y 更新为当前值(3->2)，
    // 最后还是交换 x(5),y(2)。
    private void visit(TreeNode cur) {
        // 树调试技巧：先依次打印遍历的结点，确定遍历顺序无误后再尝试打印各个结点的状态
        // System.out.println("" + cur + "(" + cur.val + ") " + cur.left + " " + cur.right + " " + last);
        if (last != null && cur.val < last.val) { // 发现有问题的地方
            if (x == null) {
                x = last;
            }
            y = cur; // 两种情况下 y 都是当前值
        }
    }
}
