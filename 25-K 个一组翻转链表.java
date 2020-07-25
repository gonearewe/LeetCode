// 给你一个链表，每 k 个节点一组进行翻转，请你返回翻转后的链表。
// k 是一个正整数，它的值小于或等于链表的长度。
// 如果节点总数不是 k 的整数倍，那么请将最后剩余的节点保持原有顺序。

// 示例：
// 给你这个链表：1->2->3->4->5
// 当 k = 2 时，应当返回: 2->1->4->3->5
// 当 k = 3 时，应当返回: 3->2->1->4->5

// 说明：
// 你的算法只能使用常数的额外空间。
// 你不能只是单纯的改变节点内部的值，而是需要实际进行节点交换。

import java.util.ArrayList;
import java.util.List;

/**
 * Definition for singly-linked list.
 * public class ListNode {
 * int val;
 * ListNode next;
 * ListNode(int x) { val = x; }
 * }
 */
class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
        val = x;
    }
}

class Solution {
    private int k;

    public ListNode reverseKGroup(ListNode head, int k) {
        this.k = k;
        List<ListNode> heads = new ArrayList<>();
        var i = 0;
        var cnt = 0;
        // 先找到各组的头
        for (ListNode node = head; node != null; node = node.next, i++) {
            if (i % k == 0) {
                heads.add(node);
            }
            if (i % k == k - 1) {
                cnt++;         // 利用 cnt 确定是否是完整的一组
            }
        }

        ListNode tail = null;
        int start = heads.size() - 1;
        if (cnt != heads.size()) {
            tail = heads.get(heads.size() - 1);
            start = heads.size() - 2;
        }

        for (int j = start; j >= 0; j--) {
            tail = reverse(heads.get(j), tail); // 倒序反转各组，并拼接各组结果
        }

        return tail;
    }

    // EXAMPLE:
    // head: 2->6->7->3  tail: 0
    // ret: 3->7->6->2->0
    private ListNode reverse(ListNode head, ListNode tail) {
        var res = tail;
        ListNode node = head;
        var i = 0;
        while (i < this.k) { // 利用 k 控制反转结点数
            var n = node.next;
            node.next = res;
            res = node;
            node = n;
            i++;
        }
        return res;
    }
}
