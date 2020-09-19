// 计算给定二叉树的所有左叶子之和。

// 示例：

//     3
//    / \
//   9  20
//     /  \
//    15   7

// 在这个二叉树中，有两个左叶子，分别是 9 和 15，所以返回 24
object Solution {
  def sumOfLeftLeaves(root: TreeNode): Int = {
    def isLeaf(node: TreeNode) = node != null && node.left == null && node.right == null

    if (root == null) 0
    else sumOfLeftLeaves(root.right) + (if (isLeaf(root.left)) root.left.value else sumOfLeftLeaves(root.left))
  }

  // Definition for a binary tree node.
  // class TreeNode(var _value: Int) {
  //   var value: Int = _value
  //   var left: TreeNode = _
  //   var right: TreeNode = _
  // }
}
