// 给定一个二叉搜索树，同时给定最小边界L 和最大边界 R。通过修剪二叉搜索树，使得所有节点的值在[L, R]中 (R>=L) 。你可能需要改变树的根节点，所以结果应当返回修剪好的二叉搜索树的新的根节点。

// 示例 1:
// 输入: 
//     1
//    / \
//   0   2

//   L = 1
//   R = 2
// 输出: 
//     1
//       \
//        2

// 示例 2:
// 输入: 
//     3
//    / \
//   0   4
//    \
//     2
//    /
//   1

//   L = 1
//   R = 3
// 输出: 
//       3
//      / 
//    2   
//   /
//  1
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
    fun trimBST(root: TreeNode?, L: Int, R: Int): TreeNode? {
        root ?: return null
        if (root.`val` < L) {
            return trimBST(root.right, L, R) // 任务交给左子树
        }
        if (root.`val` > R) {
            return trimBST(root.left, L, R) // 任务交给右子树
        }

        root.left = trimBST(root.left, L, R) // 修剪左子树
        root.right = trimBST(root.right, L, R) // 修剪右子树
        return root
    }

    class TreeNode(var `val`: Int) {
        var left: TreeNode? = null
        var right: TreeNode? = null
    }
}