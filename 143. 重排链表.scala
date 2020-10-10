// 给定一个单链表 L：L0→L1→…→Ln-1→Ln ，
// 将其重新排列后变为： L0→Ln→L1→Ln-1→L2→Ln-2→…

// 你不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。

// 示例 1:
// 给定链表 1->2->3->4, 重新排列为 1->4->2->3.

// 示例 2:
// 给定链表 1->2->3->4->5, 重新排列为 1->5->2->4->3.

object Solution {
  def reorderList(head: ListNode): Unit = {
    if (head == null) return

    // find mid
    var (slow, fast) = (head, head)
    while (fast != null && fast.next != null) {
      slow = slow.next
      fast = fast.next.next
    }

    def reverse(head: ListNode): ListNode = {
      if (head == null || head.next == null) return head

      val node = reverse(head.next)
      head.next.next = head
      head.next = null
      node
    }

    slow.next = reverse(slow.next)
    var (front, back, tail) = (head, slow.next, new ListNode())
    while (front != slow.next || back != null) {
      if (front != slow.next) {
        tail.next = front
        front = front.next
        tail = tail.next
      }
      if (back != null) {
        tail.next = back
        back = back.next
        tail = tail.next
      }
    }
    tail.next = null
  }

  // Definition for singly-linked list.
  class ListNode(_x: Int = 0, _next: ListNode = null) {
    var next: ListNode = _next
    var x: Int = _x
  }
}
