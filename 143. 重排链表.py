# 给定一个单链表 L 的头节点 head ，单链表 L 表示为：
#  L0 → L1 → … → Ln-1 → Ln
# 请将其重新排列后变为：
# L0 → Ln → L1 → Ln-1 → L2 → Ln-2 → …
#
# 不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。
#
# 示例 1:
# 输入: head = [1,2,3,4]
# 输出: [1,4,2,3]
#
# 示例 2:
# 输入: head = [1,2,3,4,5]
# 输出: [1,5,2,4,3]
#
# 提示：
# 链表的长度范围为 [1, 5 * 104]
# 1 <= node.val <= 1000

# Definition for singly-linked list.
# class ListNode:
#     def __init__(self, val=0, next=None):
#         self.val = val
#         self.next = next

class Solution:
    def reorderList(self, head: ListNode) -> None:
        """
        Do not return anything, modify head in-place instead.
        """

        def reverse(node: ListNode) -> ListNode:
            if node is None or node.next is None:
                return node
            head = reverse(node.next)
            node.next.next, node.next = node, None
            return head

        slow, fast = head, head
        while fast.next and fast.next.next:
            slow = slow.next
            fast = fast.next.next
        li, slow.next = reverse(slow.next), None

        dummy = ListNode()
        cur = dummy
        while head and li:
            cur.next, cur, head = head, head, head.next
            cur.next, cur, li = li, li, li.next
        cur.next = head
        return dummy.next
