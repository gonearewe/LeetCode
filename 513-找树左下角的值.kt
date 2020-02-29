import java.util.*

// 给定一个二叉树，在树的最后一行找到最左边的值。

// 示例 1:
// 输入:

//   2
//  / \
// 1   3

// 输出:
// 1

// 示例 2:
// 输入:

//       1
//      / \
//     2   3
//    /   / \
//   4   5   6
//  /
// 7

// 输出:
// 7

// 注意: 您可以假设树（即给定的根节点）不为 NULL。
/**
 * Example:
 * var ti = TreeNode(5)
 * var v = ti.`val`
 * Definition for a binary tree node.
 * class TreeNode(var `val`: Int) {
 *     var left: TreeNode? = null
 *     var right: TreeNode? = null
 * }
 */
class Solution {
    fun findBottomLeftValue(root: TreeNode?): Int {
        var left = 0
        val queue = LinkedList<TreeNode>()
        queue.push(root)
        while (!queue.isEmpty()) {
            val len = queue.size
            for (i in 0 until len) {
                if (i == 0) {
                    left = queue.peekLast().`val`
                }

                val e = queue.removeLast()
                if (e.left != null)
                    queue.push(e.left)
                if (e.right != null)
                    queue.push(e.right)
            }
        }

        return left
    }

    class TreeNode(var `val`: Int) {
        var left: TreeNode? = null
        var right: TreeNode? = null
    }
}