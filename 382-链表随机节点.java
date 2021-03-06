// 给定一个单链表，随机选择链表的一个节点，并返回相应的节点值。保证每个节点被选的概率一样。

// 进阶:
// 如果链表十分大且长度未知，如何解决这个问题？你能否使用常数级空间复杂度实现？

// 示例:
// // 初始化一个单链表 [1,2,3].
// ListNode head = new ListNode(1);
// head.next = new ListNode(2);
// head.next.next = new ListNode(3);
// Solution solution = new Solution(head);
// // getRandom()方法应随机返回1,2,3中的一个，保证每个元素被返回的概率相等。
// solution.getRandom();

import java.util.Random;

// Definition for singly-linked list.
class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
        val = x;
    }
}

class Solution { //蓄水池抽样
    private ListNode head;
    private Random rand = new Random();

    /**
     * @param head The linked list's head.
     *             Note that the head is guaranteed to be not null, so it contains at least one node.
     */
    public Solution(ListNode head) {
        this.head = head;
    }

    /**
     * Returns a random node's value.
     */
    public int getRandom() {
        var node = head.next;
        int ret = head.val, cnt = 0;
        ;
        while (node != null) {
            cnt++;
            if (rand.nextInt(cnt + 1) == 0) {
                ret = node.val;
            }
            node = node.next;
        }
        return ret;
    }
}

/**
 * Your Solution object will be instantiated and called as such:
 * Solution obj = new Solution(head);
 * int param_1 = obj.getRandom();
 */

