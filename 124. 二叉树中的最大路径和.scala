// 给定一个非空二叉树，返回其最大路径和。

// 本题中，路径被定义为一条从树中任意节点出发，沿父节点-子节点连接，
// 达到任意节点的序列。该路径至少包含一个节点，且不一定经过根节点。

// 示例 1：
// 输入：[1,2,3]

//        1
//       / \
//      2   3

// 输出：6

// 示例 2：
// 输入：[-10,9,20,null,null,15,7]

//    -10
//    / \
//   9  20
//     /  \
//    15   7

// 输出：42
import scala.collection.mutable

object Solution {
  def maxPathSum(root: TreeNode): Int = {
    val map = mutable.Map[TreeNode, Int]()

    // 函数 maxGain(node) 计算二叉树中的一个节点的最大贡献值，
    // 具体而言，就是在以该节点为根节点的子树中寻找以该节点为起点的一条路径，使得该路径上的节点值之和最大。
    def maxGain(node: TreeNode): Int = {
      val gain = if (node == null) 0
      else node.value + (maxGain(node.left) max maxGain(node.right) max 0)
      map(node) = gain
      gain
    }

    maxGain(root)
    var res = Int.MinValue

    def dfs(node: TreeNode): Unit = {
      if (node != null) {
        val maxPath = node.value + (map(node.left) max 0) + (map(node.right) max 0)
        res = res max maxPath
        dfs(node.left)
        dfs(node.right)
      }
    }

    dfs(root)
    res
  }

  // Definition for a binary tree node.
  class TreeNode(var _value: Int) {
    var value: Int = _value
    var left: TreeNode = null
    var right: TreeNode = null
  }
}
