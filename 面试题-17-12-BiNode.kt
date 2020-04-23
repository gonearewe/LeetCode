// 二叉树数据结构TreeNode可用来表示单向链表（其中left置空，right为下一个链表节点）。
// 实现一个方法，把二叉搜索树转换为单向链表，要求值的顺序保持不变，转换操作应是原址的，也就是在原始的二叉搜索树上直接修改。
// 返回转换后的单向链表的头节点。
//
// 注意：本题相对原题稍作改动
//
// 示例：
// 输入： [4,2,5,1,3,null,6,0]
// 输出： [0,null,1,null,2,null,3,null,4,null,5,null,6]
//
// 提示：
// 节点数量不会超过 100000。
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
    fun convertBiNode(root: TreeNode?): TreeNode? {
        root ?: return null
        val head = TreeNode(0) // 哨兵节点，保存根节点位置
        val prev = inorder(head, root.left) ?: head
        prev.right = root
        root.left = null
        inorder(root, root.right)
        return head.right
    }

    // inorder 把以 root 为根的树转化成要求的链表并接在 prev 的右子树上，返回链表的最后一个节点
    fun inorder(prev: TreeNode, root: TreeNode?): TreeNode? {
        root ?: return null
        val p = inorder(prev, root.left) ?: prev // 把左子树变成链表
        p.right = root // 接上根节点
        root.left = null // 记得清空左子树
        return inorder(root, root.right) ?: root // 接上右子树并返回
    }

    class TreeNode(var `val`: Int) {
        var left: TreeNode? = null
        var right: TreeNode? = null
    }
}