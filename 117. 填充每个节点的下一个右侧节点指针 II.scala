// 给定一个二叉树

// struct Node {
//   int val;
//   Node *left;
//   Node *right;
//   Node *next;
// }

// 填充它的每个 next 指针，让这个指针指向其下一个右侧节点。
// 如果找不到下一个右侧节点，则将 next 指针设置为 NULL。

// 初始状态下，所有 next 指针都被设置为 NULL。

// 进阶：
// 你只能使用常量级额外空间。
// 使用递归解题也符合要求，本题中递归程序占用的栈空间不算做额外的空间复杂度。

// 示例：
// 输入：root = [1,2,3,4,5,null,7]
// 输出：[1,#,2,3,#,4,5,7,#]
// 解释：给定二叉树如图 A 所示，你的函数应该填充它的每个 next 指针，以指向其下一个右侧节点，如图 B 所示。
//  
// 提示：
// 树中的节点数小于 6000
// -100 <= node.val <= 100
import scala.collection.mutable

object Solution {
  def connect(root: Node): Node = {
    val queue = new mutable.Queue[Node]
    queue.enqueue(root)
    while (queue.nonEmpty) {
      var next: Node = null
      queue.indices foreach { i =>
        val e = queue.dequeue()
        if (e != null) {
          e.next = next
          next = e
          queue.enqueue(e.right, e.left)
        }
      }
    }
    root
  }

  // Definition for a Node.
  // class Node(var _value: Int) {
  //   var value: Int = _value
  //   var left: Node = null
  //   var right: Node = null
  //   var next: Node = null
  // }
}
