# 反转一个单链表。
#
# 示例:
#
# 输入: 1->2->3->4->5->NULL
# 输出: 5->4->3->2->1->NULL
# 进阶:
# 你可以迭代或递归地反转链表。你能否用两种方法解决这道题？

# Definition for singly-linked list.
class ListNode:
    def __init__(self, val=0, next=None):
        self.val = val
        self.next = next


class Solution:
    def reverseList(self, head: ListNode) -> ListNode:
        def backtrack(node: ListNode) -> (ListNode, ListNode):
            if not node.next:
                return node, node
            head, end = backtrack(node.next)
            end.next, node.next = node, None
            return head, node

        if not head:
            return None
        return backtrack(head)[0]


# class Solution:
#     def reverseList(self, head: ListNode) -> ListNode:
#         if not head:
#             return None
#
#         next, head.next = head.next, None
#         while next is not None:
#             next.next, next, head = head, next.next, next
#         return head
