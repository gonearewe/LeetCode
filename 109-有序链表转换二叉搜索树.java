// 给定一个单链表，其中的元素按升序排序，将其转换为高度平衡的二叉搜索树。

// 本题中，一个高度平衡二叉树是指一个二叉树每个节点 的左右两个子树的高度差的绝对值不超过 1。

// 示例:
// 给定的有序链表： [-10, -3, 0, 5, 9],
// 一个可能的答案是：[0, -3, 9, -10, null, 5], 它可以表示下面这个高度平衡二叉搜索树：

//       0
//      / \
//    -3   9
//    /   /
//  -10  5

// Definition for singly-linked list.
class ListNode {
    int val;
    ListNode next;

    ListNode() {
    }

    ListNode(int val) {
        this.val = val;
    }

    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
}

// Definition for a binary tree node.
public class TreeNode {
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

class Solution {
    public TreeNode sortedListToBST(ListNode head) {
        return build(head, null);
    }

    private TreeNode build(ListNode l, ListNode r) {
        if (l == r) { // 构建区间是 [l,r)
            return null;
        }

        ListNode slow = l, fast = l; // 快慢指针法，快指针是两倍速
        for (; fast.next != r && fast.next.next != r; slow = slow.next, fast = fast.next.next);

        var node = new TreeNode(slow.val); // slow 刚好就是中点
        node.right = build(slow.next, r); 
        node.left = build(l, slow);
        return node;
    }
}
