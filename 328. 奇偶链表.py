# 给定一个单链表，把所有的奇数节点和偶数节点分别排在一起。请注意，这里的奇数节点和偶数节点指的是节点编号的奇偶性，
# 而不是节点的值的奇偶性。
#
# 请尝试使用原地算法完成。你的算法的空间复杂度应为 O(1)，时间复杂度应为 O(nodes)，nodes 为节点总数。
#
# 示例 1:
# 输入: 1->2->3->4->5->NULL
# 输出: 1->3->5->2->4->NULL
#
# 示例 2:
# 输入: 2->1->3->5->6->4->7->NULL
# 输出: 2->3->6->7->1->5->4->NULL
# 说明:
# 应当保持奇数节点和偶数节点的相对顺序。
# 链表的第一个节点视为奇数节点，第二个节点视为偶数节点，以此类推。

# Definition for singly-linked list.
# class ListNode:
#     def __init__(self, val=0, next=None):
#         self.val = val
#         self.next = next

class Solution:
    def oddEvenList(self, head: ListNode) -> ListNode:
        odd, even = ListNode(), ListNode()
        odd_last, even_last = odd, even
        cur = head
        while cur:
            odd_last.next = cur
            cur, odd_last = cur.next, odd_last.next
            if cur:
                even_last.next = cur
                cur, even_last = cur.next, even_last.next
        odd_last.next, even_last.next = even.next, None
        return odd.next
