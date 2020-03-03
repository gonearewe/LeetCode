// 给定一个二叉搜索树和一个目标结果，如果 BST 中存在两个元素且它们的和等于给定的目标结果，则返回 true。

// 案例 1:
// 输入: 
//     5
//    / \
//   3   6
//  / \   \
// 2   4   7

// Target = 9
// 输出: True

// 案例 2:
// 输入: 
//     5
//    / \
//   3   6
//  / \   \
// 2   4   7

// Target = 28
// 输出: False
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
    private val set = HashSet<Int>()
    fun findTarget(root: TreeNode?, k: Int): Boolean {
        root ?: return false
        if (set.contains(k - root.`val`)) {
            return true
        }

        set.add(root.`val`) // 遍历节点，把值放进 set 用于后续查询
        return findTarget(root.left, k) || findTarget(root.right, k)
    }

    class TreeNode(var `val`: Int) {
        var left: TreeNode? = null
        var right: TreeNode? = null
    }
}
