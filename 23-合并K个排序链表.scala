// 合并 k 个排序链表，返回合并后的排序链表。请分析和描述算法的复杂度。

// 示例:
// 输入:
// [
//   1->4->5,
//   1->3->4,
//   2->6
// ]
// 输出: 1->1->2->3->4->4->5->6

import scala.collection.mutable

/**
 * Definition for singly-linked list.
 * class ListNode(var _x: Int = 0) {
 *  var next: ListNode = null
 *  var x: Int = _x
 * }
 */
class ListNode(var _x: Int = 0) {
  var next: ListNode = _
  var x: Int = _x
}

object Solution {

  implicit object NodeOrdering extends Ordering[ListNode] {
    override def compare(x: ListNode, y: ListNode): Int = (x, y) match {
      case (null, _) => 1
      case (_, null) => -1
      case _ => y.x - x.x
    }
  }

  def mergeKLists(lists: Array[ListNode]): ListNode = {
    val head = new ListNode() // 哨兵结点，最后返回它的 next
    var tail = head
    val queue = mutable.PriorityQueue(lists: _*) // 优先队列协助排大小
    while (queue.nonEmpty) {
      tail.next = queue.dequeue() 
      if (tail.next != null)
        tail = tail.next // 几个链表里找到头最小的插入结果链表
      if (tail.next != null)
        queue.enqueue(tail.next)
    }
    head.next
  }
}

